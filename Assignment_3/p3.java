package Assignment_3;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int x) {
        data = x;
        left = null;
        right = null;
    }
}

class p3 {

    static void printLevelOrder(Node root) {
        if (root == null) {
            System.out.println("Depth 0 -> []");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<String> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node curr = queue.poll();
                if (curr == null) {
                    levelNodes.add("N");
                    queue.add(null);
                    queue.add(null);
                } else {
                    levelNodes.add(String.valueOf(curr.data));
                    queue.add(curr.left);
                    queue.add(curr.right);
                }
            }

            boolean allNulls = true;
            for (String s : levelNodes) {
                if (!s.equals("N")) {
                    allNulls = false;
                    break;
                }
            }

            if (allNulls) break;

            System.out.println("Depth " + depth + " -> " + levelNodes);
            depth++;
        }
    }

    static int search(int[] inorder, int value, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == value) return i;
        }
        return -1;
    }

    static Node rec(int[] inorder, int[] preorder, int[] preIndex, int left, int right) {
        if (left > right) return null;
        int rootVal = preorder[preIndex[0]++];
        Node root = new Node(rootVal);
        int index = search(inorder, rootVal, left, right);
        root.left = rec(inorder, preorder, preIndex, left, index - 1);
        root.right = rec(inorder, preorder, preIndex, index + 1, right);
        return root;
    }

    static Node buildTree(int[] inorder, int[] preorder) {
        int[] preIndex = {0};
        return rec(inorder, preorder, preIndex, 0, preorder.length - 1);
    }

    public static void main(String[] args) {
        int[] inorder = {3, 1, 4, 0, 5, 2};
        int[] preorder = {0, 1, 3, 4, 2, 5};
        Node root = buildTree(inorder, preorder);
        printLevelOrder(root);
    }
}
