package org.jrw;

import org.jrw.SinglyLinkedList.Node;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SinglyLinkedListTest {
    @Test
    public void zipEmptyList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node[] originalList = list.toArray();
        list.zipON2();
        Node[] zippedList = list.toArray();
        assertEquals(0, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void zipSingleNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node = new Node();
        list.append(node);
        Node[] originalList = list.toArray();
        list.zipON2();
        Node[] zippedList = list.toArray();

        assertEquals(1, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void zipTwoNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        list.append(nodeOne).append(nodeTwo);
        Node[] originalList = list.toArray();
        list.zipON2();
        Node[] zippedList = list.toArray();

        assertEquals(2, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void zipFiveNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        Node nodeThree = new Node();
        Node nodeFour = new Node();
        Node nodeFive = new Node();
        list.append(nodeOne).append(nodeTwo).append(nodeThree).append(nodeFour).append(nodeFive);
        list.zipON2();
        Node[] zippedList = list.toArray();

        Node[] expectedList = new Node[] {
            nodeOne, nodeFive, nodeTwo, nodeFour, nodeThree
        };

        assertEquals(5, zippedList.length);
        assertArrayEquals(expectedList, zippedList);
    }

    @Test
    public void zipONEmptyList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node[] originalList = list.toArray();
        list.zipON();
        Node[] zippedList = list.toArray();
        assertEquals(0, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void zipONSingleNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node node = new Node();
        list.append(node);
        Node[] originalList = list.toArray();
        list.zipON();
        Node[] zippedList = list.toArray();

        assertEquals(1, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void zipONTwoNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        list.append(nodeOne).append(nodeTwo);
        Node[] originalList = list.toArray();
        list.zipON();
        Node[] zippedList = list.toArray();

        assertEquals(2, zippedList.length);
        assertArrayEquals(originalList, zippedList);
    }

    @Test
    public void zipONSixNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        Node nodeThree = new Node();
        Node nodeFour = new Node();
        Node nodeFive = new Node();
        Node nodeSix = new Node();
        list.append(nodeOne).append(nodeTwo).append(nodeThree).append(nodeFour).append(nodeFive)
                .append(nodeSix);
        list.zipON();
        Node[] zippedList = list.toArray();

        Node[] expectedList = new Node[] {
                nodeOne, nodeSix, nodeTwo, nodeFive, nodeThree, nodeFour
        };

        assertEquals(6, zippedList.length);
        assertArrayEquals(expectedList, zippedList);
    }

    @Test
    public void zipONFiveNodeList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        Node nodeThree = new Node();
        Node nodeFour = new Node();
        Node nodeFive = new Node();
        list.append(nodeOne).append(nodeTwo).append(nodeThree).append(nodeFour).append(nodeFive);
        list.zipON();
        Node[] zippedList = list.toArray();

        Node[] expectedList = new Node[] {
                nodeOne, nodeFive, nodeTwo, nodeFour, nodeThree
        };

        assertEquals(5, zippedList.length);
        assertArrayEquals(expectedList, zippedList);
    }

    @Test
    public void reverse_emptyList() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node[] originalList = list.toArray();

        list.reverse();
        Node[] reversedList = list.toArray();

        assertEquals(0, reversedList.length);
        assertArrayEquals(originalList, reversedList);
    }

    @Test
    public void reverse_oneNode() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        list.append(nodeOne);
        Node[] originalList = list.toArray();

        list.reverse();
        Node[] reversedList = list.toArray();

        assertArrayEquals(originalList, reversedList);
    }

    @Test
    public void reverse_manyNodes() {
        SinglyLinkedList list = new SinglyLinkedList();
        Node nodeOne = new Node();
        Node nodeTwo = new Node();
        Node nodeThree = new Node();
        Node nodeFour = new Node();
        list.append(nodeOne).append(nodeTwo).append(nodeThree).append(nodeFour);
        list.reverse();
        Node[] reversedList = list.toArray();

        Node[] expectedList = new Node[] {
                nodeFour, nodeThree, nodeTwo, nodeOne
        };

        assertArrayEquals(expectedList, reversedList);
        assertNull(nodeOne.next);
    }
}
