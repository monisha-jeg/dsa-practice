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
        assertEquals(List.of(2, 3), forwardValues(l1), "Forward contents after append");
        assertEquals(List.of(3, 2), reverseValues(l1), "Reverse contents after append");

        DoublyLinkedList l2 = new DoublyLinkedList();
        l2.prepend(1);
        l2.prepend(0);
        assertEquals(List.of(0, 1), forwardValues(l2), "Forward contents after prepend");
        assertEquals(List.of(1, 0), reverseValues(l2), "Reverse contents after prepend");

        l1.deleteFirst();
        l1.deleteLast();
        assertEquals(List.of(), forwardValues(l1), "List should be empty after deleting first and last");
        assertEquals(List.of(), reverseValues(l1),
                "Reverse contents should be empty after deleting first and last");

        l2.deleteFirst();
        l2.deleteLast();
        assertEquals(List.of(), forwardValues(l2),
                "List should be empty after deleting first and last from l2");

        l1.insertNodeAt(0, 100);
        l1.insertNodeAt(0, 50);
        l1.insertNodeAt(2, 200);
        assertEquals(List.of(50, 100, 200), forwardValues(l1), "Contents after insertNodeAt operations");
        assertEquals(List.of(200, 100, 50), reverseValues(l1),
                "Reverse contents after insertNodeAt operations");

        l1.deleteNodeAt(1);
        l1.deleteNodeAt(1);
        l1.deleteNodeAt(0);
        assertEquals(List.of(), forwardValues(l1), "Contents should be empty after deleteNodeAt operations");

        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.reverse();
        assertEquals(List.of(5, 4, 3, 2, 1), forwardValues(l1), "Contents after reverse");

        l1.recursiveReverse();
        assertEquals(List.of(1, 2, 3, 4, 5), forwardValues(l1), "Contents after recursive reverse");
    }

    private static List<Integer> forwardValues(DoublyLinkedList list) {
        List<Integer> values = new ArrayList<>();
        DoublyNode current = list.head;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    private static List<Integer> reverseValues(DoublyLinkedList list) {
        List<Integer> values = new ArrayList<>();
        DoublyNode current = list.getLastNode();
        while (current != null) {
            values.add(current.value);
            current = current.prev;
        }
        return values;
    }

    public static void main(String[] args) {
        run();
    }
}
