package com.fake_orgasm.btree;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for testing insertion functionality of the BTree.
 */
public class BTreeInsertTest {
    /**
     * Btree ds.
     */
    private BTree<Integer> bTree;

    /**
     * Initializes the BTree instance before each test method.
     */
    @BeforeEach
    public void setUp() {
        bTree = new BTree<>(3);
    }

    /**
     * Test case for inserting a single node into the BTree.
     */
    @Test
    public void testInsertSingleNode() {
        bTree.insert(5);

        Node<Integer> root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(5), root.getKey(0));
    }

    /**
     * Test case for inserting multiple nodes into the BTree.
     */
    @Test
    public void testInsertMultipleNodes() {
        bTree.insert(5);
        bTree.insert(3);
        bTree.insert(7);
        bTree.insert(1);
        bTree.insert(4);
        bTree.insert(6);
        bTree.insert(8);

        Node<Integer> root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(4), root.getKey(0));

        Node<Integer> leftChild = root.getChild(0);
        Node<Integer> rightChild = root.getChild(1);

        assertEquals(2, leftChild.getSize());
        assertTrue(leftChild.isLeaf());
        assertEquals(Integer.valueOf(1), leftChild.getKey(0));
        assertEquals(Integer.valueOf(3), leftChild.getKey(1));

        assertEquals(4, rightChild.getSize());
        assertTrue(rightChild.isLeaf());

        assertEquals(Integer.valueOf(5), rightChild.getKey(0));
        assertEquals(Integer.valueOf(6), rightChild.getKey(1));
        assertEquals(Integer.valueOf(7), rightChild.getKey(2));
        assertEquals(Integer.valueOf(8), rightChild.getKey(3));
    }

    /**
     * Test case for basic insertion and split scenario in the BTree.
     */
    @Test
    public void basicSplitInsertCase() {
        bTree = new BTree<>(5);
        bTree.insert(190);
        bTree.insert(57);
        bTree.insert(89);
        bTree.insert(90);
        bTree.insert(121);
        bTree.insert(170);
        bTree.insert(35);
        bTree.insert(48);
        bTree.insert(91);
        bTree.insert(22);
        bTree.insert(126);
        bTree.insert(132);
        bTree.insert(80);

        Node<Integer> root = bTree.getRoot();
        assertEquals(1, root.getSize());

        assertEquals(Integer.valueOf(90), root.getKey(0));

        Node<Integer> firstChild = root.getChild(0);
        Node<Integer> secondChild = root.getChild(1);

        assertEquals(6, firstChild.getSize());
        assertTrue(firstChild.isLeaf());

        assertEquals(Integer.valueOf(22), firstChild.getKey(0));
        assertEquals(Integer.valueOf(35), firstChild.getKey(1));
        assertEquals(Integer.valueOf(48), firstChild.getKey(2));
        assertEquals(Integer.valueOf(57), firstChild.getKey(3));
        assertEquals(Integer.valueOf(80), firstChild.getKey(4));
        assertEquals(Integer.valueOf(89), firstChild.getKey(5));

        assertEquals(6, secondChild.getSize());
        assertTrue(secondChild.isLeaf());

        assertEquals(Integer.valueOf(91), secondChild.getKey(0));
        assertEquals(Integer.valueOf(121), secondChild.getKey(1));
        assertEquals(Integer.valueOf(126), secondChild.getKey(2));
        assertEquals(Integer.valueOf(132), secondChild.getKey(3));
        assertEquals(Integer.valueOf(170), secondChild.getKey(4));
        assertEquals(Integer.valueOf(190), secondChild.getKey(5));
    }
}
