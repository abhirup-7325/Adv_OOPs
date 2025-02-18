package Assignment_2;

/*
 * Given the root of a binary tree, return all root-to-leaf paths in any order. A leaf is a node with no
children. Treat a node as an inner class. Write appropriate code representing the node containing
an integer (1>n>100) or a string (6 letter names). You may use ArrayList (List<String> example = new
ArrayList<String/Integer>();).
*/

import java.util.*;

class Node {
    int data;
    Node left, right;
    
    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    Node root;
    Node[] nodes;
    
    public void buildTree() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter number of nodes:");
        int n = scanner.nextInt();
        
        nodes = new Node[n];
        
        System.out.println("Enter values for all " + n + " nodes:");
        for(int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            nodes[i] = new Node(value);
        }
        
        System.out.println("Enter index of left and right child for each node (-1 for null):");
        for(int i = 0; i < n; i++) {
            System.out.println("Node " + nodes[i].data + " -> Enter left and right child indices:");
            int leftIndex = scanner.nextInt();
            int rightIndex = scanner.nextInt();
            
            if (leftIndex != -1) nodes[i].left = nodes[leftIndex];
            if (rightIndex != -1) nodes[i].right = nodes[rightIndex];
        }
        
        root = nodes[0];
        scanner.close();
    }
    
    public void printInorder(Node node) {
        if(node == null) return;
        
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }
    
    public void printTree(Node node, String indent, String marker) {
        if(node == null) return;
        
        System.out.println(indent + marker + node.data);
        printTree(node.left, indent + "  ", "L:");
        printTree(node.right, indent + "  ", "R:");
    }
    
    public void printRootToLeaves() {
        List<Integer> path = new ArrayList<>();
        System.out.println("\nRoot-to-leaf paths:");
        printPaths(root, path);
    }
    
    private void printPaths(Node node, List<Integer> path) {
        if (node == null) return;
        
        path.add(node.data);
        
        if (node.left == null && node.right == null) {
            System.out.println(path);
        } else {
            printPaths(node.left, new ArrayList<>(path));
            printPaths(node.right, new ArrayList<>(path));
        }
    }
    
    public void printTreePattern(Node root, int space) {
        if (root == null) return;
        
        space += 10;
        
        printTreePattern(root.right, space);
        
        System.out.println();
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.println(root.data);
        
        printTreePattern(root.left, space);
    }
}

public class p3 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.buildTree();
        
        System.out.println("\nTree structure:");
        tree.printTree(tree.root, "", "");
        
        System.out.println("\nInorder traversal:");
        tree.printInorder(tree.root);
        System.out.println();
        
        tree.printRootToLeaves();
        
        System.out.println("\nTree Pattern:");
        tree.printTreePattern(tree.root, 0);
    }
}
