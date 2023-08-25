package com.fake_orgasm.btree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fake_orgasm.users_management.libs.btree.BTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing search functionality of the BTree.
 */
public class BTreeSearchTest {

    /**
     * BTree ds.
     */
    private BTree<Integer> bTree;

    /**
     * Initializes the BTree instance before each test method.
     */
    @BeforeEach
    public void setUp() {
        bTree = new BTree<>(4);

        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);
        bTree.insert(60);
        bTree.insert(70);
        bTree.insert(80);
        bTree.insert(90);
        bTree.insert(100);
        bTree.insert(110);
        bTree.insert(120);
        bTree.insert(130);
        bTree.insert(140);
        bTree.insert(150);
    }

    /**
     * Test case for searching an existing key in the BTree.
     */
    @Test
    public void testSearchExistingKey() {
        int actualResult = bTree.searchKey(20);
        assertEquals(20, actualResult);
    }

    /**
     * Test case for searching a non-existing key in the BTree.
     */
    @Test
    public void testSearchNonExistingKey() {
        Integer actualResult = bTree.searchKey(25);
        assertNull(actualResult);
    }

    /**
     * Test case for searching a key in an empty BTree.
     */
    @Test
    public void testSearchKeyInEmptyTree() {
        bTree = new BTree<>(4);
        Integer actualResult = bTree.searchKey(20);
        assertNull(actualResult);
    }

    /**
     * Test case for searching a key after inserting it into the BTree.
     */
    @Test
    public void testSearchKeyAfterInsert() {
        bTree.insert(25);
        int actualResult = bTree.searchKey(25);
        assertEquals(25, actualResult);
    }
}
