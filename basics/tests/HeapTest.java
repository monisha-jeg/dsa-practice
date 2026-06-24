package basics.tests;

import basics.*;
import static basics.tests.TestUtils.*;

public class HeapTest {

    public static void run() {
        MinHeap heap = new MinHeap();
        heap.build(new int[] { 7, 2, 10, 4, 3 });

        assertEquals(5, heap.size(), "Heap size after build");
        assertEquals(2, heap.heap.get(0).intValue(), "Min-heap root should be 2 after build");

        heap.extractMin();
        assertEquals(4, heap.size(), "Heap size after extractMin");

        heap.insert(5);
        heap.insert(8);
        assertEquals(6, heap.size(), "Heap size after two inserts");

        int i = heap.search(4);
        assertTrue(i >= 0, "Search for 4 should return a valid index");
        heap.delete(i);
        assertEquals(-1, heap.search(4), "Deleted value 4 should no longer be found");
        assertEquals(5, heap.size(), "Heap size after deleting 4");
    }

    public static void main(String[] args) {
        run();
    }
}
