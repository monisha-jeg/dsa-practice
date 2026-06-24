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
        assertEquals(List.of(0, BinaryTree.LEVEL_SEPARATOR, 1, 2, BinaryTree.LEVEL_SEPARATOR, 5, 6, 10, 11,
                BinaryTree.LEVEL_SEPARATOR, 12, 13, 14), tree.levelOrder_Queue(),
                "Queue level-order list after inserts with separators");
        assertEquals(List.of(0, BinaryTree.LEVEL_SEPARATOR, 1, 2, BinaryTree.LEVEL_SEPARATOR, 5, 6, 10, 11,
                BinaryTree.LEVEL_SEPARATOR, 12, 13, 14), tree.levelOrder_Iterative(),
                "Iterative level-order list after inserts with separators");
        assertEquals(List.of(1, 2), tree.printAtLevel(2), "Values at tree level 2");
        assertEquals(4, tree.height(), "Tree height should be 4");
        assertEquals(List.of(0, 1, 5, 12, 13, 6, 14, 2, 10, 11), tree.preorder(), "Preorder values");
        assertEquals(List.of(12, 5, 13, 1, 14, 6, 0, 10, 2, 11), tree.inorder(), "Inorder values");

        tree.delete_byMovingValues(1);
        assertEquals(9, tree.size(), "Size after deleting 1");
        assertEquals(-1, tree.search(1), "Deleted value 1 should not be found");
    }

    public static void main(String[] args) {
        run();
    }
}
