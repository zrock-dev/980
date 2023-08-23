package com.fake_orgasm0.bTree;

import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for testing search functionality of the BTree.
 */
public class BTreeSearchTest {

    private BTree<Integer> bTree;

    @Before
    public void setUp() {
        bTree = new BTree<>(4);
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150);
        list.forEach(key -> bTree.insert(key));
    }

    /**
     * Test case for searching an existing key in the BTree.
     */
    @Test
    public void testSearchExistingKey() {
        int actualResult = bTree.searchKey(20);
        Assert.assertEquals(20, actualResult);
    }

    /**
     * Test case for searching a non-existing key in the BTree.
     */
    @Test
    public void testSearchNonExistingKey() {
        Integer actualResult = bTree.searchKey(25);
        Assert.assertNull(actualResult);
    }

    /**
     * Test case for searching a key in an empty BTree.
     */
    @Test
    public void testSearchKeyInEmptyTree() {
        bTree = new BTree<>(4);
        Integer actualResult = bTree.searchKey(20);
        Assert.assertNull(actualResult);
    }

    /**
     * Test case for searching a key after inserting it into the BTree.
     */
    @Test
    public void testSearchKeyAfterInsert() {
        bTree.insert(25);
        int actualResult = bTree.searchKey(25);
        Assert.assertEquals(25, actualResult);
    }
}