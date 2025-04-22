package Assignment_3;

/*
 * 
 * Given preorder and inorder traversal outcomes of a family tree, construct and return the
corresponding binary tree. If two nodes in the tree has the same depth then they are
cousins. So, print the names who are cousins in generations of the family history. Try to use
java collections classes and functions as much as you can.
 */

import java.util.*;

class TreeNode {
    String name;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(String name) {
        this.name = name;
        this.left = null;
        this.right = null;
    }
}

public class FamilyTree {
    // Global map to store inorder positions for O(1) lookup
    private static Map<String, Integer> inOrderMap;
    
    public static void main(String[] args) {
        // Example family tree
        String[] preOrder = {"Parent", "ChildA", "GrandchildA1", "GrandchildA2", "ChildB", "GrandchildB1", "GrandchildB2"};
        String[] inOrder = {"GrandchildA1", "ChildA", "GrandchildA2", "Parent", "GrandchildB1", "ChildB", "GrandchildB2"};
        
        // Build the family tree
        TreeNode root = buildTree(preOrder, inOrder);
        
        // Find and print cousins
        findAndPrintCousins(root);
    }
    
    // Function to build the tree from preorder and inorder traversals
    public static TreeNode buildTree(String[] preOrder, String[] inOrder) {
        // Initialize the inorder map for O(1) lookup
        inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }
        
        // Call the recursive helper function
        return buildTreeHelper(preOrder, 0, preOrder.length - 1, 0);
    }
    
    private static TreeNode buildTreeHelper(String[] preOrder, int preStart, int preEnd, int inStart) {
        if (preStart > preEnd) {
            return null;
        }
        
        // First element in preorder is the root
        TreeNode root = new TreeNode(preOrder[preStart]);
        
        // Find root position in inorder
        int rootPos = inOrderMap.get(root.name);
        
        // Calculate left subtree size
        int leftSubtreeSize = rootPos - inStart;
        
        // Recursively build left and right subtrees
        root.left = buildTreeHelper(preOrder, preStart + 1, preStart + leftSubtreeSize, inStart);
        root.right = buildTreeHelper(preOrder, preStart + leftSubtreeSize + 1, preEnd, rootPos + 1);
        
        return root;
    }
    
    // Function to find and print cousins
    public static void findAndPrintCousins(TreeNode root) {
        if (root == null) return;
        
        // Use level order traversal to find nodes at the same depth
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int level = 0;
        
        System.out.println("Family Tree Cousins by Generation:");
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<String> nodesAtLevel = new ArrayList<>();
            
            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                nodesAtLevel.add(current.name);
                
                // Add children to the queue
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            
            // Print cousins at this level if there are more than one
            if (nodesAtLevel.size() > 1) {
                System.out.println("Generation " + level + " cousins: " + String.join(", ", nodesAtLevel));
            }
            
            level++;
        }
    }
    
    // Function to print the tree for verification (optional)
    public static void printTree(TreeNode root) {
        if (root == null) return;
        
        System.out.println("Preorder traversal:");
        printPreOrder(root);
        System.out.println();
        
        System.out.println("Inorder traversal:");
        printInOrder(root);
        System.out.println();
    }
    
    private static void printPreOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.name + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }
    
    private static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.name + " ");
        printInOrder(node.right);
    }
}