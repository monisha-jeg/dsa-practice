package basics;

import java.util.Queue;
import java.util.LinkedList;

public class BinaryTreeTest {
    static class Node {
        int value;
        Node right;
        Node left;
        
        Node(int value) {
            this.value = value;
        }
    }

    static class BinaryTree {
        Node root;
        
        BinaryTree(Node root) {
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
        
        void preorder() {
	System.out.println("\nPreorder:");
            preorder(root);
        }
        void preorder(Node root) {
             if (root == null) {
                return;
            }
            System.out.print(root.value + " ");
            preorder(root.left);
            preorder(root.right);
        }
        
        void postorder() {
	System.out.println("\nPostorder:");
            postorder(root);
        }
        void postorder(Node root) {
             if (root == null) {
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.value + " ");
            
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
            
        void printAtLevel(int level) {
            printAtLevel(root, level);
        }
        void printAtLevel(Node root, int level) {
            if (root == null) {
                return;
            }
            if (level == 1) {
                System.out.print(root.value + " ");
            }
            printAtLevel(root.left, level - 1);
            printAtLevel(root.right, level - 1);
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
        
        void levelOrderIterative() {
            int height = height();
            System.out.println("\nLevel order iterative:");
            for(int i = 1; i <= height; i++)  {
                printAtLevel(i);
                System.out.println();
            }
        }
        
        void insert(int value) {
            Queue<Node> queue = new LinkedList<>(); 
            queue.offer(root);
            
            while(!queue.isEmpty()) {
                Node first = queue.poll();
                if (first.left != null)
                    queue.offer(first.left);
                else {
                    first.left = new Node(value);
                    return;
                }
                if (first.right != null)
                    queue.offer(first.right);
                else {
                    first.right = new Node(value);
                    return;
                }
            }
        }
        
        void deleteByMovingNodes(int value) {
            if (root.value == value) { // Special case for root.
                if (root.left != null) {
                    freeRightChildSpotOf(root.left);
                    Node temp = root.right;
                    root = root.left;
                    root.right = temp;
                } else {
                    root = root.right;
                }
                return;
            }
            
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            
            while(!queue.isEmpty()) {
                Node first = queue.poll();
                if (first.left != null) {
                    if (first.left.value == value) {
                        if (first.left.left != null) {
                            freeRightChildSpotOf(first.left.left);
                            Node temp = first.left.right;
                            first.left = first.left.left; // Abandon 'first.left'.
                            first.left.right = temp; // Attach right node of abandoned.
                        } else {
                            first.left = first.left.right;
                        }
                        return;
                    }
                    queue.offer(first.left);
                }
                if (first.right != null) {
                    if (first.right.value == value) {
                        if (first.right.left != null) {
                            freeRightChildSpotOf(first.right.left);
                            Node temp = first.right.right;
                            first.right =  first.right.left; // Abandon 'first.right'.
                            first.right.right = temp; // Attach right node of abandoned.
                        } else {
                            first.right = first.right.right;
                        }
                        return;
                    }
                    queue.offer(first.right);
                }
            }
        }
        void freeRightChildSpotOf(Node node) {
            if (node.right == null)
                return; // Right is already free.
            if (node.left == null)  {
                node.left = node.right; // Make right free by moving to left.
                return;
            }
            freeRightChildSpotOf(node.left);
            node.left.right = node.right; // Right child moved to grandchild.
        }
        
        void deleteByMovingValues(int value) {
            if (root.value == value) { // Special case for root.
                if (root.left != null) {
                    root.value = root.left.value;
                    moveValuesFromSubtreeAndDropDeepestNode(root.left);
                } else if (root.right != null) {
                    root.value = root.right.value;
                    moveValuesFromSubtreeAndDropDeepestNode(root.right);
                } else {
                  root = null;  
                }
                return;
            }
            
            Queue<Node> queue = new LinkedList<>(); 
            queue.offer(root);
            
            while(!queue.isEmpty()) {
                Node first = queue.poll();
                if (first.left != null) {
                    if (first.left.value == value) {
                        first.left = moveValuesFromSubtreeAndDropDeepestNode(first.left);
                        return;
                    }
                    queue.offer(first.left);
                }
                if (first.right != null) {
                    if (first.right.value == value) {
                        first.right = moveValuesFromSubtreeAndDropDeepestNode(first.right);
                        return;
                    }
                    queue.offer(first.right);
                }
            }
        }
        Node moveValuesFromSubtreeAndDropDeepestNode(Node node) {
            if (node.left != null) {
                node.value = node.left.value;
                if (isLeaf(node.left))  {
                    node.left = null;
                    return node;
                } else 
                    node.left = moveValuesFromSubtreeAndDropDeepestNode(node.left);
                    return node;
            } else if (node.right != null) {
                node.value = node.right.value;
                if (isLeaf(node.right))  {
                    node.right = null;
                    return node;
                } else 
                    node.right = moveValuesFromSubtreeAndDropDeepestNode(node.right);
                    return node;
            } else 
                return null;
        }
        boolean isLeaf(Node node) {
            return node.left == null && node.right == null;
        }
    }
    
    public static void run() {
        BinaryTree tree = new BinaryTree(new Node(0));
        tree.insert(1);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.levelOrderIterative();
        
        tree.deleteByMovingValues(1);
        tree.levelOrderIterative();
    }
}
