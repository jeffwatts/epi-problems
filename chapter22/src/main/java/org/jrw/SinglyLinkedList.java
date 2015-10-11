package org.jrw;

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    public static class Node {
        static int val;

        Node next;
        int value;

        public Node() {
            val++;
            value = val;
        }

        @Override
        public String toString() {
            return String.format("Node(%d)", value);
        }
    }

    public SinglyLinkedList() {}

    public SinglyLinkedList(Node head) {
        this.head = head;
        length = 0;
        Node current = head;
        Node last = current;
        while (current != null) {
            last = current;
            current = current.next;
            length++;
        }
        tail = last;
    }

    public SinglyLinkedList append(Node node) {
        if (head == null) {
            head = node;
            tail = node;
            length = 1;
        } else {
            tail.next = node;
            tail = node;
            length++;
        }
        return this;
    }

    /**
     * Solves problem 22.10
     * This solution is O(n^2); an O(n) solution seems possible
     */
    public void zipON2() {
        if (length < 3) {
            return;
        }

        Node currentNode = head;
        Node previousTail = tail;
        Node next = head.next;
        while (next != null) {
            moveBackTail();
            currentNode.next = previousTail;
            previousTail.next = next;
            currentNode = next;
            next = next.next;
            previousTail = tail;
        }
    }

    /**
     * Solves problem 22.10 in O(n) time complexity
     */
    public void zipON() {
        if (length < 3) {
            return;
        }

        // partition this list into two halves
        Node lead = head;
        Node trailing = lead;
        while (lead != null && lead.next != null) {
            trailing = trailing.next;
            lead = lead.next.next;
        }
        Node partitionTwoHead = trailing.next;
        trailing.next = null;

        partitionTwoHead = reverseAtNode(partitionTwoHead);

        Node curPartitionOne = head;
        Node curPartitionTwo = partitionTwoHead;

        while (curPartitionTwo != null) {
            Node temp = curPartitionTwo.next;
            curPartitionTwo.next = curPartitionOne.next;
            curPartitionOne.next = curPartitionTwo;
            curPartitionOne = curPartitionOne.next.next;
            curPartitionTwo = temp;
        }
    }

    /**
     * Reverses this linked list
     */
    public void reverse() {
        reverseAtNode(head);
        Node temp = head;
        head = tail;
        tail = temp;
    }

    /**
     * Returns the node at the head of the given sub-list
     */
    private Node reverseAtNode(Node current) {
        Node newHead = current;
        if (current == null) {
            return null;
        }

        Node next = current.next;
        current.next = null;
        Node temp = next;
        while (temp != null) {
            newHead = temp;
            temp = next.next;
            next.next = current;
            current = next;
            next = temp;
        }

        return newHead;
    }

    private void moveBackTail() {
        Node potentialTail = head;
        while (potentialTail.next != tail && potentialTail.next != null) {
            potentialTail = potentialTail.next;
        }
        tail = potentialTail;
        tail.next = null;
    }

    public int length() {
        return length;
    }

    public Node[] toArray() {
        Node[] array = new Node[length];
        if (length == 0) {
            return array;
        }

        Node currentNode = head;
        array[0] = currentNode;
        for (int i = 1; i < array.length; i++) {
            currentNode = currentNode.next;
            array[i] = currentNode;
        }
        return array;
    }
}
