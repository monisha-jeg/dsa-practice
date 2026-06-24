package basics.tests;

import basics.*;
import java.util.ArrayList;
import java.util.List;
import static basics.tests.TestUtils.*;

public class LinkedListTest {
    public static void run() {
        LinkedList l = new LinkedList(1, 2, 3, 4, 5);
        assertEquals(List.of(1, 2, 3, 4, 5), l.values(), "Initial list should contain 1..5");
        assertEquals(5, l.size(), "Size after initialization");
        assertEquals(5, l.recursiveSize(), "Recursive size after initialization");

        l.append(6);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), l.values(), "Contents after append 6");

        l.prepend(0);
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6), l.values(), "Contents after prepend 0");

        l.deleteFirst();
        assertEquals(List.of(1, 2, 3, 4, 5, 6), l.values(), "Contents after deleteFirst");
        assertEquals(1, l.removeFirst(), "removeFirst should return the first value");
        assertEquals(List.of(2, 3, 4, 5, 6), l.values(), "Contents after removeFirst");

        l.insertNodeAt(4, 100);
        l.insertNodeAt(0, 200);
        l.insertNodeAt(7, 300);
        assertEquals(List.of(200, 2, 3, 4, 5, 100, 6, 300), l.values(), "Contents after insertNodeAt operations");

        l.deleteNodeAt(5);
        l.deleteNodeAt(0);
        l.deleteNodeAt(5);
        assertEquals(List.of(2, 3, 4, 5, 6), l.values(), "Contents after deleteNodeAt operations");

        assertEquals(3, l.searchNode(5), "searchNode should find value 5 at index 3");
        assertEquals(-1, l.searchNode(10), "searchNode should return -1 for missing value");

        l.reverse();
        assertEquals(List.of(6, 5, 4, 3, 2), l.values(), "Contents after reverse");

        l.recursiveReverse(/* complex= */ false);
        assertEquals(List.of(2, 3, 4, 5, 6), l.values(), "Contents after recursiveReverse(false)");

        l.recursiveReverse(/* complex= */ true);
        assertEquals(List.of(6, 5, 4, 3, 2), l.values(), "Contents after recursiveReverse(true)");
    }

    public static void main(String[] args) {
        run();
    }
}
