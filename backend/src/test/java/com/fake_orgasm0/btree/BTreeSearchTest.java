package com.fake_orgasm0.btree;

import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import com.fake_orgasm0.usersmanagement.libs.btree.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class BTreeSearchTest {

    private BTree<Integer> bTree;

    @Before
    public void setUp() {
        bTree = new BTree<>(4);
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150);
        list.forEach(key -> bTree.insert(key));
        bTree.printTree();


        //|__ [40, 80]
        //|   |__ [20]
        //|   |   |__ [10]
        //|       |__ [30]
        //|   |__ [60]
        //|   |   |__ [50]
        //|       |__ [70]
        //    |__ [100, 120]
        //    |   |__ [90]
        //    |   |__ [110]
        //        |__ [130, 140, 150]
    }

}
