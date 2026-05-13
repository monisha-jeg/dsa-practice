package basics;

public class StackfromScratch {
    LinkedList stackhead = new LinkedList();

    public void push(int value) {
        stackhead.prepend(value);
    }

    public int pop() {
        return stackhead.removeFirst();
    }

    public static void run() {
        StackfromScratch stack = new StackfromScratch();
        stack.stackhead = new LinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack after pushes: " + stack.pop() + " " + stack.pop() + " " + stack.pop());
    }
}
