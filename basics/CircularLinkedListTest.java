package basics;

public class CircularLinkedListTest {
    static class Node {
        int value;
        Node next;
        
        Node(int value) {
            this.value = value;
            this.next = this;
        }
    }

    static class CircularLinkedList {
        Node head;
        
        void print() {
            if (head == null) {
                return;
            }
            System.out.println(head.value);
            Node current = head.next;
            while(current != head) {
                System.out.println(current.value);
                current = current.next;
            }
        }  
        
        int size() {
            if (head == null) {
                return 0;
            }
            int count = 1;
            Node current = head.next;
            while(current != head) {
                count++;
                current = current.next;
            }
            return count;
        }
        
        void append(int value) {
            Node last = getLastNode();
            Node newLastNode = new Node(value);
            if (last != null) {
                last.next = newLastNode;
            } else {
                head = newLastNode;
            }
            newLastNode.next = head;
        }
        
        Node getLastNode() {
            Node current = head;
            if (current == null) {
                return null;
            }
            while(current.next != head) {
                current = current.next;
            }
            return current;
        }
        
        void prepend(int value) {
            Node last = getLastNode();
            
            Node oldHead = head;
            head = new Node(value);
            head.next = oldHead == null ? head : oldHead;
            if (last != null) {
                last.next = head;
            }
        }
        
        void deleteFirst() {
            if (head == null) {
                return;
            }
            Node last = getLastNode();
            last.next = head.next;
            head = head.next;
        }
        
        void deleteLast() {
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
            while(current.next.next != head) {
                current = current.next;
            }
            current.next = head;
        }
        
        boolean isCircular() {
            if (head == null) {
                return true;
            }
            Node current = head.next;
            while(current != head && current != null) {
                current = current.next;
            }
            return current == head ? true : false;
        }
        
        void reverse() {
            if (size() <= 2) {
                return;
            }
            
            Node toReverseStartingFromNode = head.next;
            Node lastNodeOfReversedPortion = head;
            while(toReverseStartingFromNode != head) {
                Node temp = toReverseStartingFromNode.next;
                toReverseStartingFromNode.next = lastNodeOfReversedPortion;
                lastNodeOfReversedPortion = toReverseStartingFromNode;
                toReverseStartingFromNode = temp;
            }
            toReverseStartingFromNode.next = lastNodeOfReversedPortion;
            head = lastNodeOfReversedPortion;
        }
    }

    public static void run() {
        CircularLinkedList c = new CircularLinkedList();
        c.append(1);
        c.prepend(0);
        c.append(2);
        c.prepend(-1);
        c.print();
        System.out.println("\n" + "count = " + c.size());
        
        c.deleteFirst();
        c.deleteLast();
        c.print();
        System.out.println("\n" + c.isCircular());
        
        c.reverse();
        c.print();
        System.out.println("\n");
        
        c.append(-1);
        c.append(-2);
        c.reverse();
        c.print();
        System.out.println("\n");
    }
}
