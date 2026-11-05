package yandexContest.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class IP {

    public static long convertIP(String ip){
        String[] parts = ip.split("\\.");
        return (Long.parseLong(parts[0]) << 24)
         | (Long.parseLong(parts[1]) << 16)
         | (Long.parseLong(parts[2]) << 8)
         | Long.parseLong(parts[3]);
    }

    public static boolean checkCidr(long ip, long network, int prefix) {
    if (prefix == 0) return true;
    long mask = 0xFFFFFFFFL << (32 - prefix);
    return (ip & mask) == (network & mask);
    }

    public static class Rule{
        long ip;
        int mask;
        int guests;
        int used = 0;

        public Rule(long ip, int mask, int guests){
            this.ip = ip;
            this.mask = mask;
            this.guests = guests;
        }

    }

    public static class User{
        long ip;
        int start;
        int end;
        boolean slotReleased = false;
        boolean connected = false;   // <-- подключён ли
        int ruleIndex = -1;        // <-- на каком правиле сидит (для освобождения слота)
        String originalIp;

        public User(long ip, int start, int end, String originalIP){
            this.ip = ip;
            this.start = start;
            this.end = end;
            this.originalIp = originalIP;
        }

    }

     public static void main(String[] args){

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line = reader.readLine();
            if (line.trim() == null || line.trim().isEmpty()){
                System.out.println("Empty reader");
                return;
            }

            int rulesCount = Integer.parseInt(line.trim());
            int usersCount = Integer.parseInt(reader.readLine());
            List<Rule> rules = new ArrayList<>();
            List<User> users = new ArrayList<>();

            for(int i = 0; i < rulesCount; i++){
                line = reader.readLine();
                String partNum[] = line.split(",");
                int rulesMaxUsers = Integer.parseInt(partNum[1].trim());
                String partMask[] = partNum[0].split("/");
                String rulesIP = partMask[0];
                int rulesMask = Integer.parseInt(partMask[1].trim());
                rules.add(new Rule(convertIP(rulesIP), rulesMask, rulesMaxUsers));

            }

            for(int i = 0; i < usersCount; i++){
                line = reader.readLine();
                String parts[] = line.split(",");
                String userIP = parts[0];
                int userTimeStart = Integer.parseInt(parts[1].trim());
                int userTimeEnd = Integer.parseInt(parts[2].trim());
                users.add(new User(convertIP(userIP), userTimeStart, userTimeEnd, userIP));

            }


            rules.sort(Comparator.comparingInt(r -> r.mask));

            int maxTime = 0;
            for (User u : users) {
                maxTime = Math.max(maxTime, u.end);
            }

            for (int t = 1; t <= maxTime; t++) {

    // 1. Освобождаем слоты
            for (User u : users) {
                if (u.connected && !u.slotReleased && u.end < t) {
                    rules.get(u.ruleIndex).used--;
                    u.slotReleased = true;
                }
            }

            // 2. Собираем активных (не подключённых, в интервале)
            List<User> active = new ArrayList<>();
            for (User u : users) {
                if (!u.connected && u.start <= t && t <= u.end) {
                    active.add(u);
                }
            }

            // 3. Сортируем по IP
            active.sort(Comparator.comparingLong(u -> u.ip));

            // 4. Подключаем
            for (User u : active) {
                for (int j = 0; j < rules.size(); j++) {
                    Rule r = rules.get(j);
                    if (checkCidr(u.ip, r.ip, r.mask) && r.used < r.guests) {
                        r.used++;
                        u.connected = true;
                        u.ruleIndex = j;
                        break;
                    }
                }
            }
        }

            for (User u : users) {
                System.out.println(u.originalIp + " " + (u.connected ? "SUCCESS" : "REJECT"));
            }
        }catch (IOException e){
            System.out.println(e);
            return;
        }
    }
}
