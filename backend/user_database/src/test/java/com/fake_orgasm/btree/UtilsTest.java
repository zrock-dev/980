package com.fake_orgasm.btree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.libs.btree.Utils;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Utils class which contains helper methods for
 * B-tree operations.
 */
public class UtilsTest {
    /**
     * Test the {@link Utils#findPositionToInsert(Node, Comparable)}
     * method for inserting elements in a non-empty node.
     */
    @Test
    public void testGetIndexToInsert() {
        Node<Integer> node = new Node<>(10);
        node.setKey(0, 1);
        node.setKey(1, 3);
        node.setKey(2, 5);
        node.setKey(3, 7);
        node.setKey(4, 9);
        node.setSize(5);

        int indexToInsert1 = Utils.findPositionToInsert(node, 4);
        int indexToInsert2 = Utils.findPositionToInsert(node, 8);
        int indexToInsert3 = Utils.findPositionToInsert(node, 10);

        assertEquals(2, indexToInsert1);
        assertEquals(4, indexToInsert2);
        assertEquals(5, indexToInsert3);
    }

    /**
     * Test the {@link Utils#findPositionToInsert(Node, Comparable)}
     * method for inserting elements in an empty node.
     */
    @Test
    public void testGetIndexToInsertEmptyList() {
        Node<Integer> node = new Node<>(10);

        int indexToInsert = Utils.findPositionToInsert(node, 5);

        assertEquals(0, indexToInsert);
    }

    /**
     * Test the {@link Utils#findPositionToInsert(Node, Comparable)}
     * method for inserting duplicate elements.
     */
    @Test
    public void testGetIndexToInsertDuplicateElements() {
        Node<Integer> node = new Node<>(10);
        node.setKey(0, 1);
        node.setKey(1, 3);
        node.setKey(2, 5);
        node.setKey(3, 5);
        node.setKey(4, 7);
        node.setKey(5, 9);
        node.setSize(6);
        int indexToInsert1 = Utils.findPositionToInsert(node, 5);
        int indexToInsert2 = Utils.findPositionToInsert(node, 6);

        assertEquals(2, indexToInsert1);
        assertEquals(4, indexToInsert2);
    }

    /**
     * Test the {@link Utils#binarySearch(Node, Comparable)}
     * method for binary searching elements in a node.
     */
    @Test
    public void testBinarySearch() {
        Node<Integer> node = new Node<>(10);
        node.setKey(0, 10);
        node.setKey(1, 20);
        node.setKey(2, 30);
        node.setKey(3, 40);
        node.setKey(4, 50);
        node.setSize(5);

        // Test cases
        assertEquals(2, Utils.binarySearch(node, 30));
        assertEquals(4, Utils.binarySearch(node, 50));
        assertEquals(-1, Utils.binarySearch(node, 25));
        assertEquals(0, Utils.binarySearch(node, 10));
        assertEquals(-1, Utils.binarySearch(node, 5));
        assertEquals(-1, Utils.binarySearch(node, 55));
    }
}
