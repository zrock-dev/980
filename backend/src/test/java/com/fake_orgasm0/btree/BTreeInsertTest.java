package com.fake_orgasm0.btree;

import static org.junit.Assert.assertEquals;

import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import com.fake_orgasm0.usersmanagement.libs.btree.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BTreeInsertTest {

    private BTree<Integer> bTree;

    @Before
    public void setUp() {
        bTree = new BTree<>(3);
    }


    @Test
    public void testInsertSingleNode() {
        bTree.insert(5);

        Node<Integer> root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(5), root.getKey(0));
    }

    @Test
    public void testInsertMultipleNodes() {
        bTree.insert(5);
        bTree.insert(3);
        bTree.insert(7);
        bTree.insert(1);
        bTree.insert(4);
        bTree.insert(6);
        bTree.insert(8);
        bTree.printTree();

        //|__ [5]
        //|   |__ [3]
        //|       |__ [1]
        //|       |__ [4]
        //    |__ [7]
        //        |__ [6]
        //        |__ [8]

        Node<Integer> root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(5), root.getKey(0));

        Node<Integer> leftChild = root.getChild(0);
        Node<Integer> rightChild = root.getChild(1);

        assertEquals(1, leftChild.getSize());
        assertEquals(Integer.valueOf(3), leftChild.getKey(0));

        assertEquals(1, rightChild.getSize());
        assertEquals(Integer.valueOf(7), rightChild.getKey(0));

        assertEquals(2, leftChild.getSize()+1);
        assertEquals(Integer.valueOf(1), leftChild.getChild(0).getKey(0));
        assertEquals(Integer.valueOf(4), leftChild.getChild(1).getKey(0));

        assertEquals(2, rightChild.getSize()+1);
        assertEquals(Integer.valueOf(6), rightChild.getChild(0).getKey(0));
        assertEquals(Integer.valueOf(8), rightChild.getChild(1).getKey(0));
    }

    @Test
    public void basicSplitInsertCase() {
        List<Integer> list = Arrays.asList(190, 57, 89, 90, 121, 170, 35, 48, 91, 22, 126, 132, 80);
        bTree = new BTree<>(5);
        list.forEach(key -> bTree.insert(key));
        bTree.printTree();

        //|__ [48, 90, 126]
        //|   |__ [22, 35]
        //|   |__ [57, 80, 89]
        //|   |__ [91, 121]
        //    |__ [132, 170, 190]

        Node<Integer> root = bTree.getRoot();
        assertEquals(3, root.getSize());
        assertEquals(Integer.valueOf(48), root.getKey(0));
        assertEquals(Integer.valueOf(90), root.getKey(1));
        assertEquals(Integer.valueOf(126), root.getKey(2));

        assertEquals(4, root.getSize()+1);

        Node<Integer> firstChild = root.getChild(0);
        Node<Integer> second = root.getChild(1);
        Node<Integer> thirdChild = root.getChild(2);
        Node<Integer> fourthChild = root.getChild(3);

        assertEquals(2, firstChild.getSize());
        assertEquals(Integer.valueOf(22), firstChild.getKey(0));
        assertEquals(Integer.valueOf(35), firstChild.getKey(1));

        assertEquals(3, second.getSize());
        assertEquals(Integer.valueOf(57), second.getKey(0));
        assertEquals(Integer.valueOf(80), second.getKey(1));
        assertEquals(Integer.valueOf(89), second.getKey(2));

        assertEquals(2, thirdChild.getSize());
        assertEquals(Integer.valueOf(91), thirdChild.getKey(0));
        assertEquals(Integer.valueOf(121), thirdChild.getKey(1));

        assertEquals(3, fourthChild.getSize());
        assertEquals(Integer.valueOf(132), fourthChild.getKey(0));
        assertEquals(Integer.valueOf(170), fourthChild.getKey(1));
        assertEquals(Integer.valueOf(190), fourthChild.getKey(2));

    }
}
