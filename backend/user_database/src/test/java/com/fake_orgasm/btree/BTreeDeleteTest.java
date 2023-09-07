package com.fake_orgasm.btree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.Node;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing deletion functionality of the BTree.
 */
public class BTreeDeleteTest {

    /**
     * bTree ds.
     */
    private BTree<Integer> bTree;

    /**
     * Initializes the BTree instance before each test method.
     */
    @BeforeEach
    public void setUp() {
        bTree = new BTree<>(5);
    }

    /**
     * Test case for simple deletion of keys from the BTree.
     */
    @Test
    public void simpleDeleteCase() {
        List<Integer> list = Arrays.asList(10, 20, 40, 50, 60, 70, 80, 90, 100);
        list.forEach(key -> bTree.insert(key));
        bTree.remove(10);
        bTree.remove(50);
        bTree.remove(20);
        bTree.insert(75);
        bTree.remove(100);
        bTree.remove(40);
        bTree.insert(65);
        bTree.remove(80);
        bTree.remove(60);

        Node<Integer> root = bTree.getRoot();
        assertEquals(4, root.getSize());
        assertTrue(bTree.getRoot().isLeaf());
        int firstKey = root.getKey(0);
        int secondKey = root.getKey(1);
        int thirdKey = root.getKey(2);
        int fourthKey = root.getKey(3);

        assertEquals(65, firstKey);
        assertEquals(70, secondKey);
        assertEquals(75, thirdKey);
        assertEquals(90, fourthKey);
    }
}
