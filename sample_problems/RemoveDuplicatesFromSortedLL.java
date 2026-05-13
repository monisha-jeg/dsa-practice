package sample_problems;

import basics.LinkedList;
import basics.Node;

/** Remove duplicates from a sorted linked list */
class RemoveDuplicatesFromSortedLL {

    private static void removeDuplicates(LinkedList list) {
        Node current = list.head;
        while (current != null) {
            if (current.next != null && current.value == current.next.value) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList(3, 4, 5, 5, 5);
        list1.append(6);
        list1.print();
        System.out.println();
        removeDuplicates(list1);
        list1.print();
        System.out.println();

        LinkedList list2 = new LinkedList();
        list2.append(1);
        removeDuplicates(list2);
        list2.print();
        System.out.println();

        LinkedList list3 = new LinkedList();
        removeDuplicates(list3);
        list3.print();
        System.out.println();

        LinkedList list4 = new LinkedList();
        list4.append(1);
        list4.append(1);
        removeDuplicates(list4);
        list4.print();
        System.out.println();
    }
}