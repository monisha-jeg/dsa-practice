package basics;

public class CircularLinkedList {
    public Node head;

    public void print() {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        Node current = head.next;
        while (current != head) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    public int size() {
        if (head == null) {
            return 0;
        }
        int count = 1;
        Node current = head.next;
        while (current != head) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void append(int value) {
        Node last = getLastNode();
        Node newLastNode = new Node(value);
        if (last != null) {
            last.next = newLastNode;
        } else {
            head = newLastNode;
        }
        newLastNode.next = head;
    }

    public Node getLastNode() {
        Node current = head;
        if (current == null) {
            return null;
        }
        while (current.next != head) {
            current = current.next;
        }
        return current;
    }

    public void prepend(int value) {
        Node last = getLastNode();

        Node oldHead = head;
        head = new Node(value);
        head.next = oldHead == null ? head : oldHead;
        if (last != null) {
            last.next = head;
        }
    }

    public void deleteFirst() {
        if (head == null) {
            return;
        }
        Node last = getLastNode();
        last.next = head.next;
        head = head.next;
    }

    public void deleteLast() {
        if (head == null) {
            return;
        }
        if (head.next == head) {
            head = null;
            return;
        }
        if (head.next.next == head) {
            head.next = head;
            return;
        }
        Node current = head.next;
        while (current.next.next != head) {
            current = current.next;
        }
        current.next = head;
    }

    public boolean isCircular() {
        if (head == null) {
            return true;
        }
        Node current = head.next;
        while (current != head && current != null) {
            current = current.next;
        }
        return current == head ? true : false;
    }

    public void reverse() {
        if (size() <= 2) {
            return;
        }

        Node toReverseStartingFromNode = head.next;
        Node lastNodeOfReversedPortion = head;
        while (toReverseStartingFromNode != head) {
            Node temp = toReverseStartingFromNode.next;
            toReverseStartingFromNode.next = lastNodeOfReversedPortion;
            lastNodeOfReversedPortion = toReverseStartingFromNode;
            toReverseStartingFromNode = temp;
        }
        toReverseStartingFromNode.next = lastNodeOfReversedPortion;
        head = lastNodeOfReversedPortion;
    }
}
