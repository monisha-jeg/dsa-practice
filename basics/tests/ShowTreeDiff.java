package basics.tests;

import basics.*;
import java.util.List;

public class ShowTreeDiff {
    public static void main(String[] args) {
        int[] vals = { 0, 1, 2, 5, 6, 10, 11, 12, 13, 14 };
        BinaryTree a = buildTree(vals);
        BinaryTree b = buildTree(vals);

        System.out.println("=== BEFORE DELETE ===");
        System.out.println("A levelOrder: " + a.levelOrder_Queue());
        System.out.println("A inorder: " + a.inorder());
        System.out.println("B levelOrder: " + b.levelOrder_Queue());
        System.out.println("B inorder: " + b.inorder());

        System.out.println("\nDeleting value 1\n");
        a.delete_byMovingValues(1);
        b.delete_byReplacingWithDeepestValue(1);

        System.out.println("=== AFTER DELETE ===");
        System.out.println("A levelOrder: " + a.levelOrder_Queue());
        System.out.println("A inorder: " + a.inorder());
        System.out.println("B levelOrder: " + b.levelOrder_Queue());
        System.out.println("B inorder: " + b.inorder());

        System.out.println("\nPer-level A:");
        printLevels(a);
        System.out.println("\nPer-level B:");
        printLevels(b);
    }

    static BinaryTree buildTree(int[] vals) {
        BinaryTree t = new BinaryTree(new TreeNode(vals[0]));
        for (int i = 1; i < vals.length; i++)
            t.insert(vals[i]);
        return t;
    }

    static void printLevels(BinaryTree t) {
        int h = t.height();
        for (int i = 1; i <= h; i++) {
            List<Integer> lvl = t.printAtLevel(i);
            System.out.println("Level " + i + ": " + lvl);
        }
    }
}
