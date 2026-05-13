package basics.tests;

import basics.*;

public class QueueTest {

    public static void run() {
        QueueFromScratch queue = new QueueFromScratch();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Queue after enqueues: " + queue.dequeue() + " " + queue.dequeue() + " " + queue.dequeue());
    }

    public static void main(String[] args) {
        run();
    }
}
