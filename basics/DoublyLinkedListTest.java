package basics;

public class DoublyLinkedListTest {
    static class Node {
        int value;
        Node next;
        Node prev;
        
        Node(int value) {
            this.value = value;
        }
    }

    static class DoublyLinkedList {
        Node head;
        
        void print() {
            Node current = head;
            while(current != null) {
                System.out.println(current.value);
                current = current.next;
            }
        }    
        
        void printReverse() {
            Node current = getLastNode();
            while(current != null) {
                System.out.println(current.value);
                current = current.prev;
            } 
        }
        
        void append(int value) {
            Node last = getLastNode();
            Node newLastNode = new Node(value);
            newLastNode.prev = last;
            if (last != null) {
                last.next = newLastNode;
            } else {
                head = newLastNode;
            }
        }
        
        Node getLastNode() {
            Node current = head;
            if (current == null) {
                return null;
            }
            while(current.next != null) {
                current = current.next;
            }
            return current;
        }
        
        void prepend(int value) {
            Node oldHead = head;
            head = new Node(value);
            head.next = oldHead;
            if (oldHead != null) {
                oldHead.prev = head;
            }
        }
        
        void deleteFirst() {
            if (head == null) {
                return;
            }
            head = head.next;
            head.prev = null;
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
            while(current.next.next != null) {
                current = current.next;
            }
            current.next.prev = null;
            current.next = null;
        }
        
        void insertNodeAt(int p, int value) {
            Node newNode = new Node(value);
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
            Node current = head;
            for (int i = 0; i < p - 1; i++) {
                current = current.next;
                if (current == null) {
                    throw new IllegalArgumentException();
                }
            }
            newNode.next = current.next;
            newNode.prev= current;
            current.next = newNode;
            if (newNode.next != null) {
             newNode.next.prev = newNode;
            }
        }
        
        void deleteNodeAt(int p) {
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
            if (current.next != null) {
                current.next.prev = current;
            }
        }
        
        void reverse() {
            if (head == null) {
                return;
            }
            Node toReverseStartingFromNode = head;
            Node lastNodeOfReversedPortion = null;
            while(toReverseStartingFromNode != null) {
                Node temp = toReverseStartingFromNode.next;
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
        
        void recursiveReverse() {
            head = recursiveReverse(null, head);
        }
        
        Node recursiveReverse(Node lastNodeOfReversedPortion, Node toReverseStartingFromNode) { 
            if(toReverseStartingFromNode == null) {
                return lastNodeOfReversedPortion;
            }
            Node temp = toReverseStartingFromNode.next;
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

    public static void run() {
        DoublyLinkedList l1 = new DoublyLinkedList();
        l1.append(2);
        l1.append(3);
        l1.print();
        l1.printReverse();
        System.out.println("\n");
    
        DoublyLinkedList l2 = new DoublyLinkedList();
        l2.prepend(1);
        l2.prepend(0);
        l2.print();
        l2.printReverse();
        System.out.println("\n");
        
        l1.deleteFirst();
        l1.deleteLast();
        l1.print();
        l1.printReverse();
        System.out.println("\n");
        
        l2.deleteFirst();
        l2.deleteLast();
        l2.print();
        l2.printReverse();
        System.out.println("\n");
        
        l1.insertNodeAt(0, 100);
        l1.insertNodeAt(0, 50);
        l1.insertNodeAt(2, 200);
        l1.print();
        l1.printReverse();
        System.out.println("\n");
        
        l1.deleteNodeAt(1);
        l1.deleteNodeAt(1);
        l1.deleteNodeAt(0);
        l1.print();
        l1.printReverse();
        System.out.println("\n");
        
        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.reverse();
        l1.print();
        l1.printReverse();
        System.out.println("\n");
        
        l1.recursiveReverse();
        l1.print();
        l1.printReverse();
        System.out.println("\n");
        
    }
}
