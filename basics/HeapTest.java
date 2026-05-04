package basics;

import java.util.ArrayList;

public class HeapTest {
    static class MinHeap {
        ArrayList<Integer> heap = new ArrayList<>();
        
        int size() {
            return heap.size();
        }
        
        int parent(int index) {
            return (index - 1)/2;
        }
        
        int left(int index) {
            return 2 * index + 1;
        }
        
        int right(int index) {
            return 2 * index + 2;
        }
        
        void print() {
           System.out.println("\nHeap: " + heap);
        }
        
        void build(int[] values) {
            for(int i = 0; i < values.length; i++) {
                heap.add(values[i]);
            }
            for (int i = parent(size() - 1); i >= 0; i--) {
                heapifyDown(i);
                print();
            }
        }
        
        void extractMin() {
            int size = size();
            if (size == 0)
                throw new IllegalStateException();
            System.out.print("\nMin: " + heap.get(0));
            heap.set(0, heap.get(size - 1));
            heap.remove(size - 1);
            heapifyDown(0);
        }
        
        int search(int value) {
            return search(0, value);
        }
        int search(int index, int value) {
            if (heap.get(index) == value)
                return index;
            if (left(index) < size() && heap.get(left(index)) <= value)  {
                int searchResult = search(left(index), value);
                if (searchResult != -1)
                    return searchResult;
            }
            if (right(index) < size() && heap.get(right(index)) <= value)  {
                 return search(right(index), value);
            }
            return -1;
        }
    
        void insert(int value) {
            heap.add(value);
            heapifyUp(size() - 1);
        }
        
        void delete(int index) {
            heap.set(index, heap.get(size() - 1));
            heap.remove(size() - 1);
            heapifyDown(index);
        }
        
        void heapifyUp(int index) {
            for (int i = index; heap.get(i) < heap.get(parent(i)); i = parent(i))
                swap(i, parent(i));
        }
        
        void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
        
        void heapifyDown(int index) {
            int minIndex = index;
            if (left(index) < size() && heap.get(left(index)) < heap.get(index)) 
                minIndex = left(index);
            if (right(index) < size() && heap.get(right(index)) < heap.get(index)) 
               minIndex = right(index);
            if (minIndex != index) {
                swap(minIndex, index);
                heapifyDown(minIndex);
            }
        }
    }
       
    public static void run() {
        MinHeap heap = new MinHeap();
        heap.build(new int[]{7, 2, 10, 4, 3});
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
}