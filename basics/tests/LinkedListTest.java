package basics.tests;

import basics.*;

public class LinkedListTest {
    public static void run() {
        LinkedList l = new LinkedList(1, 2, 3, 4, 5);
        l.print();
        System.out.println("\nSize:" + l.size());
        System.out.println("\nRecursive Size:" + l.recursiveSize() + "\n");
        l.append(6);
        l.print();
        System.out.println("\n");
        l.prepend(0);
        l.print();
        System.out.println("\n");
        l.deleteFirst();
        l.print();
        System.out.println("\n");
        l.insertNodeAt(4, 100);
        l.insertNodeAt(0, 200);
        l.insertNodeAt(7, 300);
        l.print();
        System.out.println("\n");
        l.deleteNodeAt(5);
        l.deleteNodeAt(0);
        l.deleteNodeAt(5);
        l.print();
        System.out.println("\n" + l.searchNode(5) + " " + l.searchNode(10) + "\n");
        l.reverse();
        l.print();
        System.out.println("\n");
        l.recursiveReverse(/* complex= */ false);
        l.print();
        System.out.println("\n");
        l.recursiveReverse(/* complex= */ true);
        l.print();
    }

    public static void main(String[] args) {
        run();
    }
}
