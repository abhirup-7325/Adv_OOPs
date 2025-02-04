package Assignment_2;

/*
 * Given the root of a binary tree, return all root-to-leaf paths in any order. A leaf is a node with no
children. Treat a node as an inner class. Write appropriate code representing the node containing
an integer (1>n>100) or a string (6 letter names). You may use ArrayList (List<String> example = new
ArrayList<String/Integer>();).
*/

import java.util.*;

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
        
        // Create array to store all nodes
        nodes = new Node[n];
        
        // First create all nodes
        System.out.println("Enter values for all " + n + " nodes:");
        for(int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            nodes[i] = new Node(value);
        }
        
        // Now connect nodes based on parent array
        System.out.println("Enter parent for each node (Enter -1 for root):");
        for(int i = 0; i < n; i++) {
            int parent = scanner.nextInt();
            if(parent == -1) {
                root = nodes[i];  // This is the root node
            } else {
                // If parent's left child is empty, make this the left child
                if(nodes[parent].left == null) {
                    nodes[parent].left = nodes[i];
                }
                // Otherwise, make it the right child
                else {
                    nodes[parent].right = nodes[i];
                }
            }
        }
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
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.buildTree();
        
        System.out.println("\nTree structure:");
        tree.printTree(tree.root, "", "");
        
        System.out.println("\nInorder traversal:");
        tree.printInorder(tree.root);
        System.out.println();
    }

    public void printRootToLeaves() {
        
    }
}

public class p3 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.buildTree();

        tree.printRootToLeaves();
    }
}
