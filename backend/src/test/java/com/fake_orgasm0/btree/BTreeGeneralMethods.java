package com.fake_orgasm0.btree;


import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class BTreeGeneralMethods {

    @Test
    public void testInvalidOrderThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BTree<>(-1));
    }

    @Test
    public void testInsertNullKeyThrowsException() {
        BTree<Integer> bTree = new BTree<>(3);
        assertThrows(IllegalArgumentException.class, () -> bTree.insert(null));
    }

    @Test
    public void testDeleteNullKeyThrowsException() {
        BTree<Integer> bTree = new BTree<>(3);
        assertThrows(IllegalArgumentException.class, () -> bTree.remove(null));
    }
}
