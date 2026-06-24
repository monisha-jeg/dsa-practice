package basics;

public class LinkedList {
    public Node head;

    public LinkedList() {
    }

    public LinkedList(int a, int b, int c, int d, int e) {
        Node enode = new Node(e, null);
        Node dnode = new Node(d, enode);
        Node cnode = new Node(c, dnode);
        Node bnode = new Node(b, cnode);
        head = new Node(a, bnode);
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    public int size() {
        Node current = head;
        int size = 0;
        while (current != null) {
            size += 1;
            current = current.next;
        }
        return size;
    }

    public int recursiveSize() {
        return recursiveSize(head);
    }

    int recursiveSize(Node node) {
        return node == null ? 0 : 1 + recursiveSize(node.next);
    }

    public void append(int value) {
        Node last = getLastNode();
        if (last == null) {
            head = new Node(value, null);
        } else {
            last.next = new Node(value, null);
        }
    }

    public Node getLastNode() {
        Node current = head;
        if (current == null) {
            return null;
        }
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public void prepend(int value) {
        head = new Node(value, head);
    }

    public void deleteFirst() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    public int removeFirst() {
        if (head == null) {
            throw new IllegalStateException();
        }
        Node interested = head;
        head = head.next;
        return interested.value;
    }

    public void deleteLast() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    public void insertNodeAt(int p, int value) {
        if (head == null) {
            if (p != 0) {
                throw new IllegalArgumentException();
            }
            head = new Node(value, null);
        }
        if (p == 0) {
            head = new Node(value, head);
            return;
        }
        Node current = head;
        for (int i = 0; i < p - 1; i++) {
            current = current.next;
            if (current == null) {
                throw new IllegalArgumentException();
            }
        }
        current.next = new Node(value, current.next);

    }

    public void deleteNodeAt(int p) {
        if (head == null) {
            return;
        }
        if (p == 0) {
            head = head.next;
            return;
        }
        Node current = head;
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
    }

    public int searchNode(int searchValue) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.value == searchValue) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    public int recursiveSearch(int searchValue) {
        return recursiveSearch(head, searchValue, 0);
    }

    int recursiveSearch(Node current, int searchValue, int currentIndex) {
        if (current == null) {
            return -1;
        }
        if (current.value == searchValue) {
            return currentIndex;
        }
        return recursiveSearch(current.next, searchValue, currentIndex + 1);
    }

    public void reverse() {
        Node toReverseStartingFromNode = head;
        Node lastNodeOfReversedPortion = null;
        while (toReverseStartingFromNode != null) {
            Node temp = toReverseStartingFromNode.next;
            toReverseStartingFromNode.next = lastNodeOfReversedPortion;
            lastNodeOfReversedPortion = toReverseStartingFromNode;
            toReverseStartingFromNode = temp;
        }
        head = lastNodeOfReversedPortion;
    }

    public void recursiveReverse(boolean complex) {
        if (complex == false) {
            head = recursiveReverse(null, head);
        } else if (head != null) {
            complexRecursiveReverse(head);
        }
    }

    Node recursiveReverse(Node lastNodeOfReversedPortion, Node toReverseStartingFromNode) {
        if (toReverseStartingFromNode == null) {
            return lastNodeOfReversedPortion;
        }
        Node temp = toReverseStartingFromNode.next;
        toReverseStartingFromNode.next = lastNodeOfReversedPortion;
        lastNodeOfReversedPortion = toReverseStartingFromNode;
        toReverseStartingFromNode = temp;
        return recursiveReverse(lastNodeOfReversedPortion, toReverseStartingFromNode);
    }

    public void complexRecursiveReverse(Node current) {
        if (current.next == null) {
            head = current;
            return;
        }
        Node temp = current.next;
        complexRecursiveReverse(current.next);
        current.next = null;
        temp.next = current;
    }
}
