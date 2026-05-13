package basics.tests;

import basics.*;

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
        tree.levelOrderIterative();

        tree.deleteByMovingValues(1);
        tree.levelOrderIterative();
    }

    public static void main(String[] args) {
        run();
    }
}
