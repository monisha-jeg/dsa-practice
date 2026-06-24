package basics.tests;

import basics.*;
import java.util.ArrayList;
import java.util.List;
import static basics.tests.TestUtils.*;

public class LinkedListTest {
    public static void run() {
        LinkedList l = new LinkedList(1, 2, 3, 4, 5);
        assertEquals(List.of(1, 2, 3, 4, 5), values(l), "Initial list should contain 1..5");
        assertEquals(5, l.size(), "Size after initialization");
        assertEquals(5, l.recursiveSize(), "Recursive size after initialization");

        l.append(6);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), values(l), "Contents after append 6");

        l.prepend(0);
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6), values(l), "Contents after prepend 0");

        l.deleteFirst();
        assertEquals(List.of(1, 2, 3, 4, 5, 6), values(l), "Contents after deleteFirst");
        assertEquals(1, l.removeFirst(), "removeFirst should return the first value");
        assertEquals(List.of(2, 3, 4, 5, 6), values(l), "Contents after removeFirst");

        l.insertNodeAt(4, 100);
        l.insertNodeAt(0, 200);
        l.insertNodeAt(7, 300);
        assertEquals(List.of(200, 1, 2, 3, 4, 100, 5, 300, 6), values(l), "Contents after insertNodeAt operations");

        l.deleteNodeAt(5);
        l.deleteNodeAt(0);
        l.deleteNodeAt(5);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), values(l), "Contents after deleteNodeAt operations");

        assertEquals(4, l.searchNode(5), "searchNode should find value 5 at index 4");
        assertEquals(-1, l.searchNode(10), "searchNode should return -1 for missing value");

        l.reverse();
        assertEquals(List.of(6, 5, 4, 3, 2, 1), values(l), "Contents after reverse");

        l.recursiveReverse(/* complex= */ false);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), values(l), "Contents after recursiveReverse(false)");

        l.recursiveReverse(/* complex= */ true);
        assertEquals(List.of(6, 5, 4, 3, 2, 1), values(l), "Contents after recursiveReverse(true)");
    }

    private static List<Integer> values(LinkedList list) {
        List<Integer> values = new ArrayList<>();
        Node current = list.head;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    public static void main(String[] args) {
        run();
    }
}
