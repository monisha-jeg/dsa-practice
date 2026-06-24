package basics;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BinarySearchTree {
    public TreeNode root;

    public BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public int size() {
        return size(root);
    }

    int size(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.right) + size(root.left);
    }

    public ArrayList<Integer> inorder() {
        ArrayList<Integer> values = new ArrayList<>();
        inorder(root, values);
        return values;
    }

    void inorder(TreeNode root, ArrayList<Integer> values) {
        if (root == null) {
            return;
        }
        inorder(root.left, values);
        values.add(root.value);
        inorder(root.right, values);
    }

    public int height() {
        return height(root);
    }

    int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public void levelOrderQueue() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        System.out.println("\nLevel order:");
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            System.out.print(first.value + " ");
            if (first.left != null)
                queue.offer(first.left);
            if (first.right != null)
                queue.offer(first.right);
        }
    }

    public TreeNode search(int value) {
        return search(root, value);
    }

    TreeNode search(TreeNode node, int value) {
        if (node == null || node.value == value)
            return node;
        return node.value < value
                ? search(node.left, value)
                : search(node.right, value);
    }

    public int minValue() {
        return root == null ? -1 : minValue(root);
    }

    int minValue(TreeNode node) {
        return node.left == null
                ? node.value
                : minValue(node.left);
    }

    public void insert(int value) {
        if (root == null)
            root = new TreeNode(value);
        insert(root, value);
    }

    void insert(TreeNode node, int value) {
        if (node.value == value)
            return;
        if (node.value > value) {
            if (node.left == null)
                node.left = new TreeNode(value);
            else
                insert(node.left, value);
        } else {
            if (node.right == null)
                node.right = new TreeNode(value);
            else
                insert(node.right, value);
        }
    }

    public void delete(int value) {
        root = deleteAndReturnNewRoot(root, value);
    }

    TreeNode deleteAndReturnNewRoot(TreeNode node, int value) {
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
            return node;
        }
        if (node.value > value)
            node.left = deleteAndReturnNewRoot(node.left, value);
        else
            node.right = deleteAndReturnNewRoot(node.right, value);
        return node;

    }
}
