package basics.tests;

import basics.*;
import static basics.tests.TestUtils.*;

public class StackTest {

    public static void run() {
        StackfromScratch stack = new StackfromScratch();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop(), "First popped value should be 3");
        assertEquals(2, stack.pop(), "Second popped value should be 2");
        assertEquals(1, stack.pop(), "Third popped value should be 1");
    }

    public static void main(String[] args) {
        run();
    }
}
