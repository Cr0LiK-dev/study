package yandexContest.tests;

import java.io.*;
import java.util.*;

public class IPoptimize {
    static long convertIP(String ip) {
        String[] p = ip.split("\\.");
        return (Long.parseLong(p[0]) << 24) | (Long.parseLong(p[1]) << 16)
             | (Long.parseLong(p[2]) << 8) | Long.parseLong(p[3]);
    }

    static long maskOf(int prefix) {
        return prefix == 0 ? 0L : (0xFFFFFFFFL << (32 - prefix)) & 0xFFFFFFFFL;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line).append(' ');
        StringTokenizer st = new StringTokenizer(sb.toString(), " ,/\t\r\n");

        int p = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());

        // группы правил: ключ = prefix * 2^32 + сеть
        HashMap<Long, Integer> groupId = new HashMap<>();
        int[] cap = new int[p];
        int[] used = new int[p];
        int groups = 0;
        for (int i = 0; i < p; i++) {
            long net = convertIP(st.nextToken());
            int prefix = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            long key = ((long) prefix << 32) | (net & maskOf(prefix));
            Integer g = groupId.get(key);
            if (g == null) {
                g = groups++;
                groupId.put(key, g);
            }
            cap[g] += n;
        }

        final int MAXT = 10000;
        String[] ipStr = new String[u];
        long[] ip = new long[u];
        int[] start = new int[u], end = new int[u];
        int[][] match = new int[u][];


        ArrayList<Integer>[] startsAt = new ArrayList[MAXT + 2];

        for (int i = 0; i < u; i++) {
            ipStr[i] = st.nextToken();
            ip[i] = convertIP(ipStr[i]);
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());

            int[] tmp = new int[33];
            int c = 0;
            for (int pr = 0; pr <= 32; pr++) {
                Integer g = groupId.get(((long) pr << 32) | (ip[i] & maskOf(pr)));
                if (g != null) tmp[c++] = g;
            }
            match[i] = Arrays.copyOf(tmp, c);

            if (startsAt[start[i]] == null) startsAt[start[i]] = new ArrayList<>();
            startsAt[start[i]].add(i);
        }

        // ранги по IP (при равенстве - по порядку ввода)
        Integer[] ord = new Integer[u];
        for (int i = 0; i < u; i++) ord[i] = i;
        Arrays.sort(ord, (a, b) -> Long.compare(ip[a], ip[b]));
        int[] rank = new int[u];
        int[] byRank = new int[u];
        for (int r = 0; r < u; r++) {
            rank[ord[r]] = r;
            byRank[r] = ord[r];
        }

  
        ArrayList<Integer>[] releases = new ArrayList[MAXT + 3];
    
        ArrayList<Integer>[] waiters = new ArrayList[Math.max(groups, 1)];
        int[] lastScan = new int[Math.max(groups, 1)];

        boolean[] connected = new boolean[u];
        boolean[] inCand = new boolean[u];
        int[] cand = new int[u];

        for (int t = 1; t <= MAXT; t++) {
            int cc = 0;

            // 1. освобождение слотов
            ArrayList<Integer> rel = releases[t];
            if (rel != null) {
                for (int g : rel) used[g]--;
                for (int g : rel) {
                    if (lastScan[g] == t) continue;
                    lastScan[g] = t;
                    ArrayList<Integer> w = waiters[g];
                    if (w == null) continue;
                    int keep = 0;
                    for (int idx = 0; idx < w.size(); idx++) {
                        int usr = w.get(idx);
                        if (connected[usr] || end[usr] < t) continue; // ленивая очистка
                        w.set(keep++, usr);
                        if (!inCand[usr]) {


inCand[usr] = true;
                            cand[cc++] = rank[usr];
                        }
                    }
                    while (w.size() > keep) w.remove(w.size() - 1);
                }
            }

            // 2. новые пользователи
            if (startsAt[t] != null) {
                for (int usr : startsAt[t]) {
                    if (!inCand[usr]) {
                        inCand[usr] = true;
                        cand[cc++] = rank[usr];
                    }
                }
            }

            if (cc == 0) continue;
            Arrays.sort(cand, 0, cc);

            // 3. подключение в порядке возрастания IP
            for (int k = 0; k < cc; k++) {
                int usr = byRank[cand[k]];
                inCand[usr] = false;
                boolean ok = false;
                for (int g : match[usr]) {
                    if (used[g] < cap[g]) {
                        used[g]++;
                        connected[usr] = true;
                        int rt = end[usr] + 1;
                        if (releases[rt] == null) releases[rt] = new ArrayList<>();
                        releases[rt].add(g);
                        ok = true;
                        break;
                    }
                }
                if (!ok && start[usr] == t) {
                    for (int g : match[usr]) {
                        if (waiters[g] == null) waiters[g] = new ArrayList<>();
                        waiters[g].add(usr);
                    }
                }
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < u; i++) {
            out.append(ipStr[i]).append(' ').append(connected[i] ? "SUCCESS" : "REJECT").append('\n');
        }
        System.out.print(out);
    }
}