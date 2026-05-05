package basics;

public class LinkedListTest {
    public static class Node {
        public int value;
        public Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static class LinkedList {
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

        int recursiveSize() {
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

        Node getLastNode() {
            Node current = head;
            if (current == null) {
                return null;
            }
            while (current.next != null) {
                current = current.next;
            }
            return current;
        }

        void prepend(int value) {
            head = new Node(value, head);
        }

        void deleteFirst() {
            if (head == null) {
                return;
            }
            head = head.next;
        }

        int removeFirst() {
            if (head == null) {
                throw new IllegalStateException();
            }
            Node interested = head;
            head = head.next;
            return interested.value;
        }

        void deleteLast() {
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

        void insertNodeAt(int p, int value) {
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

        void deleteNodeAt(int p) {
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

        int searchNode(int searchValue) {
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

        int recursiveSearch(int searchValue) {
            return recursiveSearch(head, searchValue, 0);
        };

        int recursiveSearch(Node current, int searchValue, int currentIndex) {
            if (current == null) {
                return -1;
            }
            if (current.value == searchValue) {
                return currentIndex;
            }
            return recursiveSearch(current.next, searchValue, currentIndex + 1);
        }

        void reverse() {
            if (head == null) {
                return;
            }
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

        void recursiveReverse(boolean complex) {
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

        void complexRecursiveReverse(Node current) {
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

    public static void run() {
        LinkedList l = new LinkedList(1, 2, 3, 4, 5);
        l.print();
        System.out.println("\nSize:" + l.size());
        System.out.println("\nRecursive Size:" + l.recursiveSize() + "\n");
        l.append(6);
        l.print();
        System.out.println("\n");
        l.prepend(0);
        l.print();
        System.out.println("\n");
        l.deleteFirst();
        l.print();
        System.out.println("\n");
        l.insertNodeAt(4, 100);
        l.insertNodeAt(0, 200);
        l.insertNodeAt(7, 300);
        // l.insertNodeAt(10, 500);
        l.print();
        System.out.println("\n");
        l.deleteNodeAt(5);
        l.deleteNodeAt(0);
        l.deleteNodeAt(5);
        l.print();
        System.out.println("\n" + l.searchNode(5) + " " + l.searchNode(10) + "\n");
        l.reverse();
        l.print();
        System.out.println("\n");
        l.recursiveReverse(/* complex= */ false);
        l.print();
        System.out.println("\n");
        l.recursiveReverse(/* complex= */ true);
        l.print();
    }
}
