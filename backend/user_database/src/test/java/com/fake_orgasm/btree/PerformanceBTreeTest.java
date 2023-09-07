package com.fake_orgasm.btree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.Node;
import org.junit.jupiter.api.Test;

/**
 * Performance test class for the BTree operations.
 */
public class PerformanceBTreeTest {
    /**
     * Btree data structure.
     */
    private BTree<Integer> bTree = new BTree<>(10);

    /**
     * Test method to measure the execution time of inserting 'n'
     * elements into the BTree.
     *
     * @param n the number of elements to insert.
     */
    public void testInsertSupportT(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            bTree.insert(i);
        }
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;
        double executionTimeSeconds = executionTimeMillis / 1000.0;
        System.out.println("Insert Time : " + executionTimeSeconds + "secs");
    }

    /**
     * Test method to measure the execution time of deleting
     * 'n' elements from the BTree,
     * excluding the element with value 50.
     *
     * @param n the number of elements to delete.
     */
    public void testDeleteSupportT(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if (i != 50) {
                bTree.remove(i);
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;
        double executionTimeSeconds = executionTimeMillis / 1000.0;
        System.out.println("Delete Time : " + executionTimeSeconds + "secs");
    }

    /**
     * Performance test method for insert and delete methods.
     */
    @Test
    public void performanceTest() {
        System.out.println("insertion execution");
        testInsertSupportT(1_000_000);
        testDeleteSupportT(999_999);
        bTree.printTree();
        Node<Integer> root = bTree.getRoot();
        int value1 = root.getKey(0);
        int value2 = root.getKey(1);
        assertEquals(50, value1);
        assertEquals(999999, value2);
    }

    /**
     * Performance test method for insert and delete methods.
     */
    @Test
    public void perTest() {
        System.out.println("insertion execution");
        testInsertSupportT(5);
        testDeleteSupportT(4);
        bTree.printTree();
        Node<Integer> root = bTree.getRoot();
        int value1 = root.getKey(0);
        assertEquals(4, value1);
    }
}
