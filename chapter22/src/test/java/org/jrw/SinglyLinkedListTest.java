package org.jrw;

import org.jrw.SinglyLinkedList.Node;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SinglyLinkedListTest {
    @Test
    public void emptyList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node[] originalList = list.toArray();
        list.zip();
        Node[] zippedList = list.toArray();
        assertEquals(0, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void singleNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node = new Node();
        list.append(node);
        Node[] originalList = list.toArray();
        list.zip();
        Node[] zippedList = list.toArray();

        assertEquals(1, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void twoNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        list.append(nodeOne).append(nodeTwo);
        Node[] originalList = list.toArray();
        list.zip();
        Node[] zippedList = list.toArray();

        assertEquals(2, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void manyNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        Node nodeThree = new Node();
        Node nodeFour = new Node();
        Node nodeFive = new Node();
        list.append(nodeOne).append(nodeTwo).append(nodeThree).append(nodeFour).append(nodeFive);
        list.zip();
        Node[] zippedList = list.toArray();

        Node[] expectedList = new Node[] {
            nodeOne, nodeFive, nodeTwo, nodeFour, nodeThree
        };

        assertEquals(5, zippedList.length);
        assertArrayEquals(expectedList, zippedList);
    }
}
