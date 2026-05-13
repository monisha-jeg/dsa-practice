package basics.tests;

import basics.*;

public class DoublyLinkedListTest {

    public static void run() {
        DoublyLinkedList l1 = new DoublyLinkedList();
        l1.append(2);
        l1.append(3);
        l1.print();
        l1.printReverse();
        System.out.println("\n");

        DoublyLinkedList l2 = new DoublyLinkedList();
        l2.prepend(1);
        l2.prepend(0);
        l2.print();
        l2.printReverse();
        System.out.println("\n");

        l1.deleteFirst();
        l1.deleteLast();
        l1.print();
        l1.printReverse();
        System.out.println("\n");

        l2.deleteFirst();
        l2.deleteLast();
        l2.print();
        l2.printReverse();
        System.out.println("\n");

        l1.insertNodeAt(0, 100);
        l1.insertNodeAt(0, 50);
        l1.insertNodeAt(2, 200);
        l1.print();
        l1.printReverse();
        System.out.println("\n");

        l1.deleteNodeAt(1);
        l1.deleteNodeAt(1);
        l1.deleteNodeAt(0);
        l1.print();
        l1.printReverse();
        System.out.println("\n");

        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.reverse();
        l1.print();
        l1.printReverse();
        System.out.println("\n");

        l1.recursiveReverse();
        l1.print();
        l1.printReverse();
        System.out.println("\n");

    }

    public static void main(String[] args) {
        run();
    }
}
