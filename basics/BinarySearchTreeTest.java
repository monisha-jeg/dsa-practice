package basics;

import java.util.Queue;
import java.util.LinkedList;

public class BinarySearchTreeTest {
    static class Node {
        int value;
        Node right;
        Node left;
        
        Node(int value) {
            this.value = value;
        }
    }

    static class BinarySearchTree {
        Node root;
        
        BinarySearchTree(Node root) {
            this.root = root;
        }
        
        int size() {
            return size(root);
        }
        int size(Node root) {
            if (root == null) {
                return 0;
            }
            return 1 + size(root.right) + size(root.left);
        }
        
        void inorder() {
	System.out.println("\nInorder:");
            inorder(root);
        }
        void inorder(Node root) {
             if (root == null) {
                return;
            }
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
        
        int height() {
            return height(root);
        }
        int height(Node root) {
            if (root == null) {
                return 0;
            }
            return Math.max(height(root.left), height(root.right)) + 1;
        }
        
        void levelOrderQueue() {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            
            System.out.println("\nLevel order:");
            while(!queue.isEmpty()) {
                Node first = queue.poll();
                System.out.print(first.value + " ");
                if (first.left != null)
                    queue.offer(first.left);
                if (first.right != null)
                    queue.offer(first.right);
            }
        }
        
        Node search(int value) {
            return search(root, value);
        }
        Node search(Node node, int value) {
            if (node == null || node.value == value) 
                return node;
            return node.value < value
                ? search(node.left, value)
                : search(node.right, value);
        }
        
        int minValue() {
            return root == null ? -1 : minValue(root);
        }
        int minValue(Node node) {
            return node.left == null
                    ? node.value
                    : minValue(node.left);
        }
        
        void insert(int value) {
            if (root == null)
                root = new Node(value);
            insert(root, value);
        }
        void insert(Node node, int value) {
            if (node.value == value)
                return;
            if (node.value > value) {
                if (node.left == null)
                    node.left = new Node(value);
                else
                    insert(node.left, value);
            } else {
                if (node.right == null)
                    node.right = new Node(value);
                else
                    insert(node.right, value);
            }
        }
        
        void delete(int value) {
            root = deleteAndReturnNewRoot(root, value);
        }
        Node deleteAndReturnNewRoot(Node node, int value) {
            if (node == null)
                return null;
            if (node.value == value) {
                if (node.left == null) 
                    return node.right;
                else if (node.right == null)
                    return node.left;
                else {
                    int nextVal = minValue(node.right);
                    node.value = nextVal;
                    node.right = deleteAndReturnNewRoot(node.right, nextVal);
                }
            }
            if (node.value > value)
                node.left = deleteAndReturnNewRoot(node.left, value);
            else    
                node.right = deleteAndReturnNewRoot(node.right, value);
            return node;
            
        }
    }
    
    public static void run() {
        BinarySearchTree tree = new BinarySearchTree(new Node(7));
        tree.insert(1);
        tree.insert(5);
        tree.insert(2);
        tree.insert(6);
        tree.insert(12);
        tree.insert(11);
        tree.insert(10);
        tree.insert(13);
        tree.insert(14);
        tree.levelOrderQueue();
        System.out.println();
        tree.inorder();
        
        tree.delete(14);
        tree.levelOrderQueue();
        tree.inorder();
        
        tree.delete(7);
        tree.levelOrderQueue();
        tree.inorder();
    }
}