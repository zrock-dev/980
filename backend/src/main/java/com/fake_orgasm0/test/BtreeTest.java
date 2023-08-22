package com.fake_orgasm0.test;


import com.fake_orgasm0.usersmanagement.libs.btree.BTree;


public class BtreeTest {
    public static BTree bTree = new BTree(20);


    public static void testInsertSupportT() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            bTree.insert(i);
        }
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;
        double executionTimeSeconds = executionTimeMillis / 1000.0;
        System.out.println("Insert Execution Time: " + executionTimeSeconds + " seconds");
    }



    public static void testDeleteSupportT() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            bTree.delete(i);
        }
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;
        double executionTimeSeconds = executionTimeMillis / 1000.0;
        System.out.println("Insert Execution Time: " + executionTimeSeconds + " seconds");
    }



    public static void testPrintSupportT() {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;
        double executionTimeSeconds = executionTimeMillis / 1000.0;
        System.out.println("Insert Execution Time: " + executionTimeSeconds + " seconds");
    }

    public static void main(String[] args) {

        testInsertSupportT();
        testDeleteSupportT();

    }
}
