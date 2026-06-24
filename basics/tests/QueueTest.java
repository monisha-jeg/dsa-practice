package basics.tests;

import basics.*;
import static basics.tests.TestUtils.*;

public class QueueTest {

    public static void run() {
        QueueFromScratch queue = new QueueFromScratch();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue(), "First dequeued value should be 1");
        assertEquals(2, queue.dequeue(), "Second dequeued value should be 2");
        assertEquals(3, queue.dequeue(), "Third dequeued value should be 3");
    }

    public static void main(String[] args) {
        run();
    }
}
