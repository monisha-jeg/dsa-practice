package basics.tests;

import basics.*;

public class StackTest {

    public static void run() {
        StackfromScratch stack = new StackfromScratch();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack after pushes: " + stack.pop() + " " + stack.pop() + " " + stack.pop());
    }

    public static void main(String[] args) {
        run();
    }
}
