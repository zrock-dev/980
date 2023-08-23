package com.fake_orgasm0.btree;


import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import com.fake_orgasm0.usersmanagement.libs.btree.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BTreeDeleteTest {

    private BTree<Integer> bTree;

    @Before
    public void setUp() {
        bTree = new BTree<>(5);
    }

    @Test
    public void simpleDeleteCase() {
        List<Integer> list = Arrays.asList(10, 20, 40, 50, 60, 70, 80, 90, 100);
        list.forEach(key -> bTree.insert(key));

        //|__ [40, 70]
        //|   |__ [10, 20]
        //|   |__ [50, 60]
        //    |__ [80, 90, 100]

        bTree.remove(10);
        //|__ [70]
        //|   |__ [20, 40, 50, 60]
        //    |__ [80, 90, 100]

        Node<Integer> root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(70), root.getKey(0));

        assertEquals(2, root.getSize()+1);

        Node<Integer> leftChild = root.getChild(0);
        Node<Integer> rightChild = root.getChild(1);

        assertEquals(4, leftChild.getSize());
        assertEquals(Integer.valueOf(20), leftChild.getKey(0));
        assertEquals(Integer.valueOf(40), leftChild.getKey(1));
        assertEquals(Integer.valueOf(50), leftChild.getKey(2));
        assertEquals(Integer.valueOf(60), leftChild.getKey(3));

        assertEquals(3, rightChild.getSize());
        assertEquals(Integer.valueOf(80), rightChild.getKey(0));
        assertEquals(Integer.valueOf(90), rightChild.getKey(1));
        assertEquals(Integer.valueOf(100), rightChild.getKey(2));


        bTree.remove(50);
        bTree.remove(20);
        bTree.insert(75);


        //|__ [70]
        //|   |__ [40, 60]
        //    |__ [75, 80, 90, 100]

        root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(70), root.getKey(0));

        assertEquals(2, root.getSize()+1);

        leftChild = root.getChild(0);
        rightChild = root.getChild(1);

        assertEquals(2, leftChild.getSize());
        assertEquals(Integer.valueOf(40), leftChild.getKey(0));
        assertEquals(Integer.valueOf(60), leftChild.getKey(1));

        assertEquals(4, rightChild.getSize());
        assertEquals(Integer.valueOf(75), rightChild.getKey(0));
        assertEquals(Integer.valueOf(80), rightChild.getKey(1));
        assertEquals(Integer.valueOf(90), rightChild.getKey(2));
        assertEquals(Integer.valueOf(100), rightChild.getKey(3));

        bTree.remove(100);
        bTree.remove(40);
        bTree.insert(65);
        bTree.remove(80);


        //|__ [70]
        //|   |__ [60, 65]
        //    |__ [75, 90]

        root = bTree.getRoot();
        assertEquals(1, root.getSize());
        assertEquals(Integer.valueOf(70), root.getKey(0));

        assertEquals(2, root.getSize()+1);

        leftChild = root.getChild(0);
        rightChild = root.getChild(1);

        assertEquals(2, leftChild.getSize());
        assertEquals(Integer.valueOf(60), leftChild.getKey(0));
        assertEquals(Integer.valueOf(65), leftChild.getKey(1));

        assertEquals(2, rightChild.getSize());
        assertEquals(Integer.valueOf(75), rightChild.getKey(0));
        assertEquals(Integer.valueOf(90), rightChild.getKey(1));

        bTree.remove(60);
        //|__ [65, 70, 75, 90]


        root = bTree.getRoot();
        assertEquals(4, root.getSize());
        assertEquals(Integer.valueOf(65), root.getKey(0));
        assertEquals(Integer.valueOf(70), root.getKey(1));
        assertEquals(Integer.valueOf(75), root.getKey(2));
        assertEquals(Integer.valueOf(90), root.getKey(3));

        assertTrue(root.isLeaf());
    }

    @Test
    public void largeSplitCase() {
        List<Integer> list = Arrays.asList(1, 9, 32, 3, 53, 43, 44, 57, 67, 7, 45, 34, 23, 12, 23, 56, 73, 65, 49, 85, 89, 64, 54, 75, 77, 49);
        list.forEach(key -> bTree.insert(key));

        //|__ [44]
        //|   |__ [9, 32]
        //|   |   |__ [1, 3, 7]
        //|   |   |__ [12, 23, 23]
        //|       |__ [34, 43]
        //    |__ [49, 56, 67, 77]
        //    |   |__ [45, 49]
        //    |   |__ [53, 54]
        //    |   |__ [57, 64, 65]
        //    |   |__ [73, 75]
        //        |__ [85, 89]

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


        //|__ [7, 43, 49, 65]
        //|   |__ [1, 3]
        //|   |__ [12, 23, 34]
        //|   |__ [45, 49]
        //|   |__ [53, 54, 64]
        //    |__ [67, 73]
        Node<Integer> root = bTree.getRoot();
        assertEquals(4, root.getSize());
        assertEquals(Integer.valueOf(7), root.getKey(0));
        assertEquals(Integer.valueOf(43), root.getKey(1));
        assertEquals(Integer.valueOf(49), root.getKey(2));
        assertEquals(Integer.valueOf(65), root.getKey(3));

        assertEquals(5, root.getSize()+1);

        Node<Integer> firstChild = root.getChild(0);
        Node<Integer> secondChild = root.getChild(1);
        Node<Integer> thirdChild = root.getChild(2);
        Node<Integer> fourthChild = root.getChild(3);
        Node<Integer> fifthChild = root.getChild(4);

        assertEquals(2, firstChild.getSize());
        assertEquals(Integer.valueOf(1), firstChild.getKey(0));
        assertEquals(Integer.valueOf(3), firstChild.getKey(1));

        assertEquals(3, secondChild.getSize());
        assertEquals(Integer.valueOf(12), secondChild.getKey(0));
        assertEquals(Integer.valueOf(23), secondChild.getKey(1));
        assertEquals(Integer.valueOf(34), secondChild.getKey(2));

        assertEquals(2, thirdChild.getSize());
        assertEquals(Integer.valueOf(45), thirdChild.getKey(0));
        assertEquals(Integer.valueOf(49), thirdChild.getKey(1));

        assertEquals(3, fourthChild.getSize());
        assertEquals(Integer.valueOf(53), fourthChild.getKey(0));
        assertEquals(Integer.valueOf(54), fourthChild.getKey(1));
        assertEquals(Integer.valueOf(64), fourthChild.getKey(2));

        assertEquals(2, fifthChild.getSize());
        assertEquals(Integer.valueOf(67), fifthChild.getKey(0));
        assertEquals(Integer.valueOf(73), fifthChild.getKey(1));

    }
}
