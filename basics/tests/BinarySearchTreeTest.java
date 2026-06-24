package basics.tests;

import basics.*;
import java.util.ArrayList;
import java.util.List;
import static basics.tests.TestUtils.*;

public class BinarySearchTreeTest {

    public static void run() {
        BinarySearchTree tree = new BinarySearchTree(new TreeNode(7));
        tree.insert(1);
        tree.insert(5);
        tree.insert(2);
        tree.insert(6);
        tree.insert(12);
        tree.insert(11);
        tree.insert(10);
        tree.insert(13);
        tree.insert(14);

        assertEquals(9, tree.size(), "Size after inserts");
        assertNotNull(tree.search(7), "Root should exist after inserts");
        assertNotNull(tree.search(14), "Inserted value 14 should exist");
        assertEquals(List.of(1, 2, 5, 6, 7, 10, 11, 13, 14), inorder(tree.root), "Inorder after inserts");

        tree.delete(14);
        assertNull(tree.search(14), "Deleted value 14 should not be found");
        assertEquals(8, tree.size(), "Size after deleting 14");

        tree.delete(7);
        assertNull(tree.search(7), "Deleted root 7 should not be found");
        assertEquals(7, tree.size(), "Size after deleting 7");
    }

    private static List<Integer> inorder(TreeNode node) {
        List<Integer> values = new ArrayList<>();
        inorder(node, values);
        return values;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        inorder(node.left, values);
        values.add(node.value);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        run();
    }
}
