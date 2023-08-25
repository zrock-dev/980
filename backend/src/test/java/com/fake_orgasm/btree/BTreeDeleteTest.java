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

    /**
     * Test case for testing deletion involving
     * a large split of nodes in the BTree.
     */
    @Test
    public void largeSplitCase() {

        bTree.insert(1);
        bTree.insert(9);
        bTree.insert(32);
        bTree.insert(3);
        bTree.insert(53);
        bTree.insert(43);
        bTree.insert(44);
        bTree.insert(57);
        bTree.insert(67);
        bTree.insert(7);
        bTree.insert(45);
        bTree.insert(34);
        bTree.insert(23);
        bTree.insert(12);
        bTree.insert(23);
        bTree.insert(56);
        bTree.insert(73);
        bTree.insert(65);
        bTree.insert(49);
        bTree.insert(85);
        bTree.insert(89);
        bTree.insert(64);
        bTree.insert(54);
        bTree.insert(75);
        bTree.insert(77);
        bTree.insert(49);

        bTree.remove(9);
        bTree.remove(32);
        bTree.remove(44);
        bTree.remove(57);
        bTree.remove(23);
        bTree.remove(56);
        bTree.remove(85);
        bTree.remove(89);
        bTree.remove(75);
        bTree.remove(77);

        Node<Integer> root = bTree.getRoot();
        assertEquals(2, root.getSize());
        assertEquals(Integer.valueOf(43), root.getKey(0));
        assertEquals(Integer.valueOf(54), root.getKey(1));

        assertEquals(3, root.getSize() + 1);

        Node<Integer> firstChild = root.getChild(0);
        Node<Integer> secondChild = root.getChild(1);
        Node<Integer> thirdChild = root.getChild(2);

        assertEquals(6, firstChild.getSize());
        assertTrue(firstChild.isLeaf());
        assertEquals(Integer.valueOf(1), firstChild.getKey(0));
        assertEquals(Integer.valueOf(3), firstChild.getKey(1));
        assertEquals(Integer.valueOf(7), firstChild.getKey(2));
        assertEquals(Integer.valueOf(12), firstChild.getKey(3));
        assertEquals(Integer.valueOf(23), firstChild.getKey(4));
        assertEquals(Integer.valueOf(34), firstChild.getKey(5));

        assertEquals(4, secondChild.getSize());
        assertTrue(secondChild.isLeaf());

        assertEquals(Integer.valueOf(45), secondChild.getKey(0));
        assertEquals(Integer.valueOf(49), secondChild.getKey(1));
        assertEquals(Integer.valueOf(49), secondChild.getKey(2));
        assertEquals(Integer.valueOf(53), secondChild.getKey(3));

        assertEquals(4, thirdChild.getSize());
        assertTrue(thirdChild.isLeaf());
        assertEquals(Integer.valueOf(64), thirdChild.getKey(0));
        assertEquals(Integer.valueOf(65), thirdChild.getKey(1));
        assertEquals(Integer.valueOf(67), thirdChild.getKey(2));
        assertEquals(Integer.valueOf(73), thirdChild.getKey(3));
    }
}
