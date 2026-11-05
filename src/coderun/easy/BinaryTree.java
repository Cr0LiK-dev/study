package coderun.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BinaryTree {

    // Узел дерева
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }
        // дубликаты игнорируем
        return root;
    }

    private static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Node root = null;
            String line;
            StringTokenizer tokenizer = null;

            while ((line = reader.readLine()) != null) {
                tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    if (value == 0) {
                        System.out.println(getHeight(root));
                        return;
                    }
                    root = insert(root, value);
                }
            }

            // Если вдруг 0 не встретился (на всякий случай)
            System.out.println(getHeight(root));
        }
    }
}