package basics.tests;

import basics.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static basics.tests.TestUtils.*;

public class BinaryTreeTest {

    public static void run() {
        BinaryTree tree = new BinaryTree(new TreeNode(0));
        tree.insert(1);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);

        assertEquals(10, tree.size(), "Size after inserts");
        assertEquals(List.of(0, 1, 2, 5, 6, 10, 11, 12, 13, 14), levelOrder(tree.root),
                "Level order after inserts");
        assertEquals(List.of(0, 1, 2, 5, 6, 10, 11, 12, 13, 14), tree.levelOrder_QueueList(),
                "Queue level-order list after inserts");
        assertEquals(List.of(0, 1, 2, 5, 6, 10, 11, 12, 13, 14), tree.levelOrder_IterativeList(),
                "Iterative level-order list after inserts");
        assertEquals(List.of(1, 2), tree.valuesAtLevel(2), "Values at tree level 2");
        assertEquals(4, tree.height(), "Tree height should be 4");
        assertEquals(List.of(0, 1, 5, 12, 13, 6, 14, 2, 10, 11), tree.preorderList(), "Preorder values");
        assertEquals(List.of(12, 5, 13, 1, 14, 6, 0, 10, 2, 11), tree.inorderList(), "Inorder values");

        tree.deleteByMovingValues(1);
        assertEquals(9, tree.size(), "Size after deleting 1");
        assertEquals(-1, search(tree.root, 1), "Deleted value 1 should not be found");
    }

    private static List<Integer> levelOrder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            values.add(node.value);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
        return values;
    }

    private static int search(TreeNode node, int value) {
        if (node == null) {
            return -1;
        }
        if (node.value == value) {
            return 0;
        }
        int leftResult = search(node.left, value);
        if (leftResult != -1) {
            return leftResult + 1;
        }
        int rightResult = search(node.right, value);
        return rightResult == -1 ? -1 : rightResult + 1;
    }

    public static void main(String[] args) {
        run();
    }
}
