package com.fake_orgasm.btree;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fake_orgasm.users_management.libs.btree.BTree;
import org.junit.jupiter.api.Test;

/**
 * Test class for general methods and behavior of the BTree class.
 */
public class BTreeExceptions {
    /**
     * Test case for verifying that creating a BTree
     * with an invalid order throws an exception.
     */
    @Test
    public void testInvalidOrderThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BTree<>(-1));
    }

    /**
     * Test case for verifying that inserting a null key
     * into the BTree throws an exception.
     */
    @Test
    public void testInsertNullKeyThrowsException() {
        BTree<Integer> bTree = new BTree<>(3);
        assertThrows(IllegalArgumentException.class, () -> bTree.insert(null));
    }

    /**
     * Test case for verifying that attempting to delete
     * a null key from the BTree throws an exception.
     */
    @Test
    public void testDeleteNullKeyThrowsException() {
        BTree<Integer> bTree = new BTree<>(3);
        assertThrows(IllegalArgumentException.class, () -> bTree.remove(null));
    }
}
