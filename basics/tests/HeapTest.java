package basics.tests;

import basics.*;

public class HeapTest {

    public static void run() {
        MinHeap heap = new MinHeap();
        heap.build(new int[] { 7, 2, 10, 4, 3 });
        heap.print();

        heap.extractMin();
        heap.print();

        heap.insert(5);
        heap.insert(8);
        heap.print();

        int i = heap.search(4);
        System.out.println("\n4 at: " + i);
        heap.delete(i);
        heap.print();
    }

    public static void main(String[] args) {
        run();
    }
}
