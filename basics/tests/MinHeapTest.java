package basics.tests;

import basics.*;
import static basics.tests.TestUtils.*;

public class MinHeapTest {

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

        // Regression test for heapifyDown choosing the smaller child.
        MinHeap heapifyDownTest = new MinHeap();
        heapifyDownTest.heap.add(10);
        heapifyDownTest.heap.add(2);
        heapifyDownTest.heap.add(4);
        heapifyDownTest.heapifyDown(0);
        assertEquals(2, heapifyDownTest.heap.get(0).intValue(), "heapifyDown should swap with the smaller child");
        assertEquals(10, heapifyDownTest.heap.get(1).intValue(), "larger child should move down after heapifyDown");
        assertEquals(4, heapifyDownTest.heap.get(2).intValue(), "other child should remain unchanged");
    }

    public static void main(String[] args) {
        run();
    }
}
