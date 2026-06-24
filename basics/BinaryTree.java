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

    public int search(int value) {
        return search(root, value, 0);
    }

    private int search(TreeNode node, int value, int currentIndex) {
        if (node == null) {
            return -1;
        }
        if (node.value == value) {
            return currentIndex;
        }
        int leftResult = search(node.left, value, currentIndex + 1);
        if (leftResult != -1) {
            return leftResult;
        }
        return search(node.right, value, currentIndex + 1);
    }

    public ArrayList<Integer> preorder() {
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

    public ArrayList<Integer> postorder() {
        ArrayList<Integer> values = new ArrayList<>();
        postorder(root, values);
        return values;
    }

    void postorder(TreeNode root, ArrayList<Integer> values) {
        if (root == null) {
            return;
        }
        postorder(root.left, values);
        postorder(root.right, values);
        values.add(root.value);
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

    public ArrayList<Integer> printAtLevel(int level) {
        ArrayList<Integer> values = new ArrayList<>();
        collectValuesAtLevel(root, level, values);
        return values;
    }

    private void collectValuesAtLevel(TreeNode root, int level, ArrayList<Integer> values) {
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

    public static final int LEVEL_SEPARATOR = Integer.MIN_VALUE;

    public ArrayList<Integer> levelOrder_Queue() {
        ArrayList<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode first = queue.poll();
                values.add(first.value);
                if (first.left != null)
                    queue.offer(first.left);
                if (first.right != null)
                    queue.offer(first.right);
            }
            if (!queue.isEmpty()) {
                values.add(LEVEL_SEPARATOR);
            }
        }
        return values;
    }

    public ArrayList<Integer> levelOrder_Iterative() {
        ArrayList<Integer> values = new ArrayList<>();
        int height = height();
        for (int i = 1; i <= height; i++) {
            values.addAll(printAtLevel(i));
            if (i < height) {
                values.add(LEVEL_SEPARATOR);
            }
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

    /**
     * Complicated, I wrote it, moves value by value instead of a single move, seems
     * correct althought he oiutput tree will be diferent from what the standard
     * algorithm returns.
     */
    public void delete_byMovingValues(int value) {
        if (root.value == value) { // Special case for root.
            if (root.left != null) {
                root.value = root.left.value;
                root.left = moveValuesFromSubtreeAndDropDeepestNode(root.left);
            } else if (root.right != null) {
                root.value = root.right.value;
                root.right = moveValuesFromSubtreeAndDropDeepestNode(root.right);
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

    /** This is the standard algorithmf or binary tree deletion. AI-written. */
    public void delete_byReplacingWithDeepestValue(int value) {
        if (root == null)
            return;

        // Special-case: single node
        if (root.left == null && root.right == null) {
            if (root.value == value)
                root = null;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode nodeToDelete = null;
        TreeNode deepest = null;
        TreeNode parentOfDeepest = null;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.value == value)
                nodeToDelete = curr;
            if (curr.left != null) {
                queue.offer(curr.left);
                parentOfDeepest = curr;
                deepest = curr.left;
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                parentOfDeepest = curr;
                deepest = curr.right;
            }
        }

        if (nodeToDelete == null)
            return; // value not found

        // Replace node value with deepest node's value
        if (deepest != null) {
            nodeToDelete.value = deepest.value;
            // remove deepest node from its parent
            if (parentOfDeepest != null) {
                if (parentOfDeepest.left == deepest)
                    parentOfDeepest.left = null;
                else if (parentOfDeepest.right == deepest)
                    parentOfDeepest.right = null;
            }
        }
    }

    /**
     * Move the deepest node object into the deleted node's position (transplant
     * node). AI-written. Standard algorithm.
     */
    public void delete_byReplacingWithDeepestNode(int value) {
        if (root == null)
            return;

        // Special-case: single node
        if (root.left == null && root.right == null) {
            if (root.value == value)
                root = null;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode nodeToDelete = null;
        TreeNode parentOfNodeToDelete = null;
        TreeNode deepest = null;
        TreeNode parentOfDeepest = null;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.value == value && nodeToDelete == null) {
                nodeToDelete = curr;
                // parentOfNodeToDelete will be set when we find it as a child below
                if (curr == root)
                    parentOfNodeToDelete = null;
            }
            if (curr.left != null) {
                if (curr.left.value == value && nodeToDelete == null) {
                    nodeToDelete = curr.left;
                    parentOfNodeToDelete = curr;
                }
                queue.offer(curr.left);
                parentOfDeepest = curr;
                deepest = curr.left;
            }
            if (curr.right != null) {
                if (curr.right.value == value && nodeToDelete == null) {
                    nodeToDelete = curr.right;
                    parentOfNodeToDelete = curr;
                }
                queue.offer(curr.right);
                parentOfDeepest = curr;
                deepest = curr.right;
            }
        }

        if (nodeToDelete == null)
            return; // not found

        // If deepest is the nodeToDelete, just remove it
        if (deepest == nodeToDelete) {
            if (parentOfDeepest == null) {
                root = null;
            } else {
                if (parentOfDeepest.left == deepest)
                    parentOfDeepest.left = null;
                else if (parentOfDeepest.right == deepest)
                    parentOfDeepest.right = null;
            }
            return;
        }

        // Save children references of nodeToDelete
        TreeNode leftChild = nodeToDelete.left;
        TreeNode rightChild = nodeToDelete.right;

        // Detach deepest from its parent
        if (parentOfDeepest != null) {
            if (parentOfDeepest.left == deepest)
                parentOfDeepest.left = null;
            else if (parentOfDeepest.right == deepest)
                parentOfDeepest.right = null;
        }

        // Place deepest in nodeToDelete's position
        if (parentOfNodeToDelete == null) {
            root = deepest;
        } else {
            if (parentOfNodeToDelete.left == nodeToDelete)
                parentOfNodeToDelete.left = deepest;
            else if (parentOfNodeToDelete.right == nodeToDelete)
                parentOfNodeToDelete.right = deepest;
        }

        // Attach children to deepest, avoiding self-links when deepest was a direct
        // child
        deepest.left = (leftChild == deepest) ? null : leftChild;
        deepest.right = (rightChild == deepest) ? null : rightChild;
    }
}
