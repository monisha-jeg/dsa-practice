package basics.tests;

import basics.*;
import java.util.ArrayList;
import java.util.List;
import static basics.tests.TestUtils.*;

public class DoublyLinkedListTest {

    public static void run() {
        DoublyLinkedList l1 = new DoublyLinkedList();
        l1.append(2);
        l1.append(3);
        assertEquals(List.of(2, 3), l1.values(), "Forward contents after append");
        assertEquals(List.of(3, 2), l1.reverseValues(), "Reverse contents after append");

        DoublyLinkedList l2 = new DoublyLinkedList();
        l2.prepend(1);
        l2.prepend(0);
        assertEquals(List.of(0, 1), l2.values(), "Forward contents after prepend");
        assertEquals(List.of(1, 0), l2.reverseValues(), "Reverse contents after prepend");

        l1.deleteFirst();
        l1.deleteLast();
        assertEquals(List.of(), l1.values(), "List should be empty after deleting first and last");
        assertEquals(List.of(), l1.reverseValues(),
                "Reverse contents should be empty after deleting first and last");

        l2.deleteFirst();
        l2.deleteLast();
        assertEquals(List.of(), l2.values(),
                "List should be empty after deleting first and last from l2");

        l1.insertNodeAt(0, 100);
        l1.insertNodeAt(0, 50);
        l1.insertNodeAt(2, 200);
        assertEquals(List.of(50, 100, 200), l1.values(), "Contents after insertNodeAt operations");
        assertEquals(List.of(200, 100, 50), l1.reverseValues(),
                "Reverse contents after insertNodeAt operations");

        l1.deleteNodeAt(1);
        l1.deleteNodeAt(1);
        l1.deleteNodeAt(0);
        assertEquals(List.of(), l1.values(), "Contents should be empty after deleteNodeAt operations");

        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.reverse();
        assertEquals(List.of(5, 4, 3, 2, 1), l1.values(), "Contents after reverse");

        l1.recursiveReverse();
        assertEquals(List.of(1, 2, 3, 4, 5), l1.values(), "Contents after recursive reverse");
    }

    public static void main(String[] args) {
        run();
    }
}
