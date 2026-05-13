package sample_problems;

import basics.TreeNode;
import basics.Node;
import java.util.LinkedList;
import basics.BinaryTree;
import java.util.Queue;

/**
 * Construct tree from linked list. The order of elements in the list must match
 * the level-order traversal of the tree.
 */
public class ConstructTreeFromLinkedList {
    public static BinaryTree constructTreeFromLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        TreeNode root = new TreeNode(head.value);
        BinaryTree tree = new BinaryTree(root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Node current = head.next;

        while (current != null) {
            TreeNode parent = queue.poll();
            TreeNode leftChild = new TreeNode(current.value);
            parent.left = leftChild;
            queue.offer(leftChild);
            current = current.next;

            if (current != null) {
                TreeNode rightChild = new TreeNode(current.value);
                parent.right = rightChild;
                queue.offer(rightChild);
                current = current.next;
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        BinaryTree tree = constructTreeFromLinkedList(head);
        tree.inorder(); // Output: 4 2 5 1 3
        // The tree will be:
        // 1
        // / \
        // 2 3
        // / \
        // 4 5
    }

}
