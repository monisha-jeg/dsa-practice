package basics;

public static class StackfromScratch {
    LinkedList stackhead;
    
    void push(int value) {
        stackhead.prepend(value);
    }
    
    void pop() {
        return stackhead.removeFirst();
    }
}
