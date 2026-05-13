package basics.tests;

import basics.*;

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
        tree.levelOrderQueue();
        System.out.println();
        tree.inorder();

        tree.delete(14);
        tree.levelOrderQueue();
        tree.inorder();

        tree.delete(7);
        tree.levelOrderQueue();
        tree.inorder();
    }

    public static void main(String[] args) {
        run();
    }
}
