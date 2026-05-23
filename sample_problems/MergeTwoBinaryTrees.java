package sample_problems;

import basics.TreeNode;

/** https://leetcode.com/problems/merge-two-binary-trees/ */
class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        } else if (root1 == null) {
            return root2;
        } else if (root2 == null) {
            return root1;
        }

        root1.value += root2.value;
        mergeTreesUtil(root1, root2);
        return root1;
    }

    public void mergeTreesUtil(TreeNode root1, TreeNode root2) {
        if (root1.left != null && root2.left != null) {
            root1.left.value += root2.left.value;
            mergeTreesUtil(root1.left, root2.left);
        } else if (root1.left == null && root2.left != null) {
            root1.left = new TreeNode(root2.left.value);
            mergeTreesUtil(root1.left, root2.left);
        }

        if (root1.right != null && root2.right != null) {
            root1.right.value += root2.right.value;
            mergeTreesUtil(root1.right, root2.right);
        } else if (root1.right == null && root2.right != null) {
            root1.right = new TreeNode(root2.right.value);
            mergeTreesUtil(root1.right, root2.right);
        }
    }
}