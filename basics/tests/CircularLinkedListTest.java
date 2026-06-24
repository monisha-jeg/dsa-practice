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
        assertEquals(List.of(-1, 0, 1, 2), c.values(), "List contents after initial operations");

        c.deleteFirst();
        c.deleteLast();
        assertEquals(2, c.size(), "Size after deleting first and last");
        assertTrue(c.isCircular(), "List should remain circular after deletions");
        assertEquals(List.of(0, 1), c.values(), "Contents after deleting first and last");

        c.reverse();
        assertEquals(List.of(0, 1), c.values(), "Reverse should leave a 2-node circular list unchanged");

        c.append(-1);
        c.append(-2);
        c.reverse();
        assertEquals(List.of(-2, -1, 1, 0), c.values(), "Contents after final reverse");
    }

    public static void main(String[] args) {
        run();
    }
}
