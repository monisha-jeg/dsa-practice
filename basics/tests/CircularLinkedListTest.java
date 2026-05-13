package basics.tests;

import basics.*;

public class CircularLinkedListTest {

    public static void run() {
        CircularLinkedList c = new CircularLinkedList();
        c.append(1);
        c.prepend(0);
        c.append(2);
        c.prepend(-1);
        c.print();
        System.out.println("\n" + "count = " + c.size());

        c.deleteFirst();
        c.deleteLast();
        c.print();
        System.out.println("\n" + c.isCircular());

        c.reverse();
        c.print();
        System.out.println("\n");

        c.append(-1);
        c.append(-2);
        c.reverse();
        c.print();
        System.out.println("\n");
    }

    public static void main(String[] args) {
        run();
    }
}
