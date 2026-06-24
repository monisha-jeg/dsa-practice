package basics.tests;

import basics.*;
import java.util.ArrayList;
import java.util.List;
import static basics.tests.TestUtils.*;

public class CircularLinkedListTest {

    public static void run() {
        CircularLinkedList c = new CircularLinkedList();
        c.append(1);
        c.prepend(0);
        c.append(2);
        c.prepend(-1);

        assertEquals(4, c.size(), "Size after append/prepend operations");
        assertTrue(c.isCircular(), "List should be circular after inserts");
        assertEquals(List.of(-1, 0, 1, 2), values(c), "List contents after initial operations");

        c.deleteFirst();
        c.deleteLast();
        assertEquals(2, c.size(), "Size after deleting first and last");
        assertTrue(c.isCircular(), "List should remain circular after deletions");
        assertEquals(List.of(0, 1), values(c), "Contents after deleting first and last");

        c.reverse();
        assertEquals(List.of(0, 1), values(c), "Reverse should leave a 2-node circular list unchanged");

        c.append(-1);
        c.append(-2);
        c.reverse();
        assertEquals(List.of(-2, -1, 1, 0), values(c), "Contents after final reverse");
    }

    private static List<Integer> values(CircularLinkedList c) {
        List<Integer> values = new ArrayList<>();
        if (c.head == null) {
            return values;
        }
        values.add(c.head.value);
        basics.Node current = c.head.next;
        while (current != c.head) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    public static void main(String[] args) {
        run();
    }
}
