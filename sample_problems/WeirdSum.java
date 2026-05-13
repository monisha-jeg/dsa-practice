package sample_problems;

import basics.TreeNode;

public class WeirdSum {
    public static int weirdSum(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
        if ((root.left != null && root.left.value == x) || (root.right != null && root.right.value == x)) {
            return root.value + weirdSum(root.left, x) + weirdSum(root.right, x);
        }
        return weirdSum(root.left, x) + weirdSum(root.right, x);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int x = 6;
        int sum = weirdSum(root, x);
        System.out.println("Weird Sum: " + sum); // Output: 5
    }
}