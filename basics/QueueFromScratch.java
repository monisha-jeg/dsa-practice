package basics;

public static class QueueFromScratch {
    LinkedList stackhead;
    
    void enqueue(int value) {
        stackhead.append(value);
    }
    
    void dequeue() {
        return stackhead.removeFirst();
    }
}
