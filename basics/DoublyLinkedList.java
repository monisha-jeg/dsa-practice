package basics;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList {
    public DoublyNode head;

    public void print() {
        DoublyNode current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    public void printReverse() {
        DoublyNode current = getLastNode();
        while (current != null) {
            System.out.println(current.value);
            current = current.prev;
        }
    }

    public void append(int value) {
        DoublyNode last = getLastNode();
        DoublyNode newLastNode = new DoublyNode(value);
        newLastNode.prev = last;
        if (last != null) {
            last.next = newLastNode;
        } else {
            head = newLastNode;
        }
    }

    public DoublyNode getLastNode() {
        DoublyNode current = head;
        if (current == null) {
            return null;
        }
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public List<Integer> values() {
        List<Integer> values = new ArrayList<>();
        DoublyNode current = head;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    public List<Integer> reverseValues() {
        List<Integer> values = new ArrayList<>();
        DoublyNode current = getLastNode();
        while (current != null) {
            values.add(current.value);
            current = current.prev;
        }
        return values;
    }

    public void prepend(int value) {
        DoublyNode oldHead = head;
        head = new DoublyNode(value);
        head.next = oldHead;
        if (oldHead != null) {
            oldHead.prev = head;
        }
    }

    public void deleteFirst() {
        if (head == null) {
            return;
        }
        head = head.next;
        head.prev = null;
    }

    public void deleteLast() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        DoublyNode current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next.prev = null;
        current.next = null;
    }

    public void insertNodeAt(int p, int value) {
        DoublyNode newNode = new DoublyNode(value);
        if (head == null) {
            if (p != 0) {
                throw new IllegalArgumentException();
            }
            head = newNode;
            return;
        }
        if (p == 0) {
            head.prev = newNode;
            newNode.next = head;
            newNode.prev = null;
            head = newNode;
            return;
        }
        DoublyNode current = head;
        for (int i = 0; i < p - 1; i++) {
            current = current.next;
            if (current == null) {
                throw new IllegalArgumentException();
            }
        }
        newNode.next = current.next;
        newNode.prev = current;
        current.next = newNode;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
    }

    public void deleteNodeAt(int p) {
        if (head == null) {
            return;
        }
        if (p == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }
        DoublyNode current = head;
        for (int i = 0; i < p - 1; i++) {
            current = current.next;
            if (current.next == null) {
                throw new IllegalArgumentException();
            }
        }
        if (current.next == null) {
            throw new IllegalArgumentException();
        }
        current.next = current.next.next;
        if (current.next != null) {
            current.next.prev = current;
        }
    }

    public void reverse() {
        if (head == null) {
            return;
        }
        DoublyNode toReverseStartingFromNode = head;
        DoublyNode lastNodeOfReversedPortion = null;
        while (toReverseStartingFromNode != null) {
            DoublyNode temp = toReverseStartingFromNode.next;
            toReverseStartingFromNode.next = lastNodeOfReversedPortion;
            toReverseStartingFromNode.prev = null;
            if (lastNodeOfReversedPortion != null) {
                lastNodeOfReversedPortion.prev = toReverseStartingFromNode;
            }
            lastNodeOfReversedPortion = toReverseStartingFromNode;
            toReverseStartingFromNode = temp;
        }
        head = lastNodeOfReversedPortion;
    }

    public void recursiveReverse() {
        head = recursiveReverse(null, head);
    }

    public DoublyNode recursiveReverse(DoublyNode lastNodeOfReversedPortion, DoublyNode toReverseStartingFromNode) {
        if (toReverseStartingFromNode == null) {
            return lastNodeOfReversedPortion;
        }
        DoublyNode temp = toReverseStartingFromNode.next;
        toReverseStartingFromNode.next = lastNodeOfReversedPortion;
        toReverseStartingFromNode.prev = null;
        if (lastNodeOfReversedPortion != null) {
            lastNodeOfReversedPortion.prev = toReverseStartingFromNode;
        }
        lastNodeOfReversedPortion = toReverseStartingFromNode;
        toReverseStartingFromNode = temp;
        return recursiveReverse(lastNodeOfReversedPortion, toReverseStartingFromNode);
    }
}
