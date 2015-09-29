package org.jrw;

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    static class Node {
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
    public void zip() {
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
