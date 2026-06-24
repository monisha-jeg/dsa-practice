package basics;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {
    public TreeNode root;

    public BinaryTree(TreeNode root) {
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

    public void preorder() {
        System.out.println("\nPreorder:");
        preorder(root);
    }

    void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public ArrayList<Integer> preorderList() {
        ArrayList<Integer> values = new ArrayList<>();
        preorder(root, values);
        return values;
    }

    void preorder(TreeNode root, ArrayList<Integer> values) {
        if (root == null) {
            return;
        }
        values.add(root.value);
        preorder(root.left, values);
        preorder(root.right, values);
    }

    public void postorder() {
        System.out.println("\nPostorder:");
        postorder(root);
    }

    void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.value + " ");

    }

    public void inorder() {
        System.out.println("\nInorder:");
        inorder(root);
    }

    void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value + " ");
        inorder(root.right);
    }

    public ArrayList<Integer> inorderList() {
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

    public void printAtLevel(int level) {
        printAtLevel(root, level);
    }

    void printAtLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.value + " ");
        }
        printAtLevel(root.left, level - 1);
        printAtLevel(root.right, level - 1);
    }

    public ArrayList<Integer> valuesAtLevel(int level) {
        ArrayList<Integer> values = new ArrayList<>();
        collectValuesAtLevel(root, level, values);
        return values;
    }

    void collectValuesAtLevel(TreeNode root, int level, ArrayList<Integer> values) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            values.add(root.value);
            return;
        }
        collectValuesAtLevel(root.left, level - 1, values);
        collectValuesAtLevel(root.right, level - 1, values);
    }

    public void levelOrder_Queue() {
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

    public ArrayList<Integer> levelOrder_QueueList() {
        ArrayList<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            values.add(first.value);
            if (first.left != null)
                queue.offer(first.left);
            if (first.right != null)
                queue.offer(first.right);
        }
        return values;
    }

    public void levelOrder_Iterative() {
        int height = height();
        System.out.println("\nLevel order iterative:");
        for (int i = 1; i <= height; i++) {
            printAtLevel(i);
            System.out.println();
        }
    }

    public ArrayList<Integer> levelOrder_IterativeList() {
        ArrayList<Integer> values = new ArrayList<>();
        int height = height();
        for (int i = 1; i <= height; i++) {
            values.addAll(valuesAtLevel(i));
        }
        return values;
    }

    public void insert(int value) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            if (first.left != null)
                queue.offer(first.left);
            else {
                first.left = new TreeNode(value);
                return;
            }
            if (first.right != null)
                queue.offer(first.right);
            else {
                first.right = new TreeNode(value);
                return;
            }
        }
    }

    public void delete_ByMovingNodes(int value) {
        if (root.value == value) { // Special case for root.
            if (root.left != null) {
                freeRightChildSpotOf(root.left);
                TreeNode temp = root.right;
                root = root.left;
                root.right = temp;
            } else {
                root = root.right;
            }
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            if (first.left != null) {
                if (first.left.value == value) {
                    if (first.left.left != null) {
                        freeRightChildSpotOf(first.left.left);
                        TreeNode temp = first.left.right;
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
                        TreeNode temp = first.right.right;
                        first.right = first.right.left; // Abandon 'first.right'.
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

    public void freeRightChildSpotOf(TreeNode node) {
        if (node.right == null)
            return; // Right is already free.
        if (node.left == null) {
            node.left = node.right; // Make right free by moving to left.
            return;
        }
        freeRightChildSpotOf(node.left);
        node.left.right = node.right; // Right child moved to grandchild.
    }

    public void deleteByMovingValues(int value) {
        delete_ByMovingValues(value);
    }

    public void delete_ByMovingValues(int value) {
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

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
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

    TreeNode moveValuesFromSubtreeAndDropDeepestNode(TreeNode node) {
        if (node.left != null) {
            node.value = node.left.value;
            if (isLeaf(node.left)) {
                node.left = null;
                return node;
            } else
                node.left = moveValuesFromSubtreeAndDropDeepestNode(node.left);
            return node;
        } else if (node.right != null) {
            node.value = node.right.value;
            if (isLeaf(node.right)) {
                node.right = null;
                return node;
            } else
                node.right = moveValuesFromSubtreeAndDropDeepestNode(node.right);
            return node;
        } else
            return null;
    }

    boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
