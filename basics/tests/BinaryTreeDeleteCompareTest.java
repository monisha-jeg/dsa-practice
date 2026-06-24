package basics.tests;

import basics.*;
import java.util.HashSet;
import java.util.Set;
import static basics.tests.TestUtils.*;

public class BinaryTreeDeleteCompareTest {

    public static void run() {
        // Case 1: delete a leaf
        int[] vals = { 0, 1, 2, 5, 6, 10, 11, 12, 13, 14 };
        BinaryTree a = buildTree(vals);
        BinaryTree b = buildTree(vals);
        a.delete_byMovingValues(14);
        b.delete_byReplacingWithDeepestValue(14);
        assertEquals(a.size(), b.size(), "Size after deleting leaf should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deleting leaf should match");

        // Case 2: delete root
        int[] vals2 = { 1, 2, 3 };
        a = buildTree(vals2);
        b = buildTree(vals2);
        a.delete_byMovingValues(1);
        b.delete_byReplacingWithDeepestValue(1);
        assertEquals(a.size(), b.size(), "Size after deleting root should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deleting root should match");

        // Case 3: delete internal node with children
        a = buildTree(vals);
        b = buildTree(vals);
        a.delete_byMovingValues(1);
        b.delete_byReplacingWithDeepestValue(1);
        assertEquals(a.size(), b.size(), "Size after deleting internal node should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deleting internal node should match");

        // Case 4: unbalanced-ish tree
        int[] vals3 = { 0, 1, 2, 3 }; // will make left-to-right filling
        a = buildTree(vals3);
        b = buildTree(vals3);
        a.delete_byMovingValues(2);
        b.delete_byReplacingWithDeepestValue(2);
        assertEquals(a.size(), b.size(), "Size after deleting node in small tree should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deleting node in small tree should match");

        // Case 5: delete non-existent value (no-op)
        a = buildTree(vals);
        b = buildTree(vals);
        a.delete_byMovingValues(999);
        b.delete_byReplacingWithDeepestValue(999);
        assertEquals(a.size(), b.size(), "Size after deleting non-existent should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deleting non-existent should match");

        // Case 6: sequential deletes
        a = buildTree(vals);
        b = buildTree(vals);
        int[] seq = { 5, 10, 0, 13 };
        for (int v : seq) {
            a.delete_byMovingValues(v);
            b.delete_byReplacingWithDeepestValue(v);
        }
        assertEquals(a.size(), b.size(), "Size after sequential deletes should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after sequential deletes should match");

        // Case 7: larger tree
        int[] large = new int[21];
        for (int i = 0; i < large.length; i++)
            large[i] = i;
        a = buildTree(large);
        b = buildTree(large);
        int[] deletes = { 0, 7, 14, 20, 10 };
        for (int v : deletes) {
            a.delete_byMovingValues(v);
            b.delete_byReplacingWithDeepestValue(v);
        }
        assertEquals(a.size(), b.size(), "Size after deletes in large tree should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deletes in large tree should match");

        // Case 8: random insertion order (deterministic seed)
        int[] perm = new int[15];
        for (int i = 0; i < perm.length; i++)
            perm[i] = i + 100;
        // simple deterministic shuffle
        for (int i = 0; i < perm.length; i++) {
            int j = (i * 7 + 3) % perm.length;
            int tmp = perm[i];
            perm[i] = perm[j];
            perm[j] = tmp;
        }
        a = buildTree(perm);
        b = buildTree(perm);
        int[] toDel = { 102, 107, 114 };
        for (int v : toDel) {
            a.delete_byMovingValues(v);
            b.delete_byReplacingWithDeepestValue(v);
        }
        assertEquals(a.size(), b.size(), "Size after deletes in shuffled tree should match");
        assertSameValueSet(a.inorder(), b.inorder(), "Values after deletes in shuffled tree should match");
    }

    private static BinaryTree buildTree(int[] vals) {
        BinaryTree t = new BinaryTree(new TreeNode(vals[0]));
        for (int i = 1; i < vals.length; i++)
            t.insert(vals[i]);
        return t;
    }

    private static void assertSameValueSet(java.util.List<Integer> a, java.util.List<Integer> b, String message) {
        Set<Integer> setA = new HashSet<>(a);
        Set<Integer> setB = new HashSet<>(b);
        if (!setA.equals(setB)) {
            throw new AssertionError(message + " expected=" + setA + " actual=" + setB);
        }
    }

    public static void main(String[] args) {
        run();
    }
}
