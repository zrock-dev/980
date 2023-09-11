package com.fake_orgasm;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import com.fake_orgasm.users_management.repository.IBTreeRepository;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BTreeRepositoryTest {
    /**
     * This method tests the insertion of a node in the secondary memory.
     * It creates a node and saves it in the secondary memory.
     */
    @Test
    public void insertNodeTest() {
        IBTreeRepository<User> bTreeRepository = new BTreeRepository();
        List<Node<User>> nodeToInsert = getRandomNode(3);
        boolean result = false;
        nodeToInsert.get(0).setId("TestNode1");
        result = bTreeRepository.save(nodeToInsert.get(0));
        Assertions.assertTrue(result);

        File currentFile = new File(String.format("%s/TestNode1.json", BTreeRepository.PATH_USER_DATA_BASE));
        result = currentFile.exists();
        Assertions.assertTrue(result);

        nodeToInsert.get(1).setId("TestNode2");
        result = bTreeRepository.save(nodeToInsert.get(1));
        Assertions.assertTrue(result);

        currentFile = new File(String.format("%s/TestNode1.json", BTreeRepository.PATH_USER_DATA_BASE));
        result = currentFile.exists();
        Assertions.assertTrue(result);

        nodeToInsert.get(2).setId("TestNode3");
        result = bTreeRepository.save(nodeToInsert.get(2));
        Assertions.assertTrue(result);

        currentFile = new File(String.format("%s/TestNode1.json", BTreeRepository.PATH_USER_DATA_BASE));
        result = currentFile.exists();
        Assertions.assertTrue(result);
    }

    /**
     * This method tests the reading of a node in the secondary memory.
     */
    @Test
    public void getNodeByIdTest() {
        IBTreeRepository<User> userIBTreeRepository = new BTreeRepository();
        long start = System.currentTimeMillis();
        Node<User> node = userIBTreeRepository.readNodeById("TestNode1");
        String currentResult = node.getId();
        String expectedResult = "TestNode1";
        Assertions.assertEquals(expectedResult, currentResult);

        node = userIBTreeRepository.readNodeById("TestNode2");
        currentResult = node.getId();
        expectedResult = "TestNode2";
        Assertions.assertEquals(expectedResult, currentResult);

        node = userIBTreeRepository.readNodeById("TestNode3");
        currentResult = node.getId();
        expectedResult = "TestNode3";
        Assertions.assertEquals(expectedResult, currentResult);
    }

    /**
     * This method test the deletion of a node in the secondary memory.
     */
    @Test
    public void deleteNodeTest() {
        IBTreeRepository<User> userIBTreeRepository = new BTreeRepository();
        List<Node<User>> nodeToInsert = getRandomNode(3);
        nodeToInsert.get(0).setId("TestNode4");
        nodeToInsert.get(1).setId("TestNode5");
        nodeToInsert.get(2).setId("TestNode6");
        userIBTreeRepository.save(nodeToInsert.get(0));
        userIBTreeRepository.save(nodeToInsert.get(1));
        userIBTreeRepository.save(nodeToInsert.get(2));

        boolean result = userIBTreeRepository.delete(nodeToInsert.get(0));
        Assertions.assertTrue(result);

        result = userIBTreeRepository.delete(nodeToInsert.get(1));
        Assertions.assertTrue(result);

        result = userIBTreeRepository.delete(nodeToInsert.get(2));
        Assertions.assertTrue(result);
    }

    /**
     * This method generate a list of nodes.
     *
     * @param number number of nodes to generate.
     * @return List of nodes.
     */
    public List<Node<User>> getRandomNode(int number) {
        List<Node<User>> users = new ArrayList<>();
        LocalDate localDate = LocalDate.of(2004, 04, 25);
        for (int i = 0; i < number; i++) {
            Node<User> nodeTest = new Node<>(4);
            User user = new User(12, "Jorge", "Oropeza", localDate, "Bolivia");
            User user2 = new User(31, "Jorge", "Zambrana", localDate, "Bolivia");
            User user3 = new User(54, "Jorge", "Muris", localDate, "Bolivia");

            nodeTest.setSize(3);
            nodeTest.setKey(0, user);
            nodeTest.setKey(1, user2);
            nodeTest.setKey(2, user3);
            users.add(nodeTest);
        }
        return users;
    }

    /**
     * This method tests the save of the size of the btree.
     */
    @Test
    public void saveSizeBTreeTest() {
        IBTreeRepository<User> userIBTreeRepository = new BTreeRepository();
        userIBTreeRepository.setSizeBTree(10);
        File file = new File(BTreeRepository.PATH_USER_DATA_BASE + "/BTreeInformation.json");
        boolean result = file.exists();
        Assertions.assertTrue(result);
    }

    @Test
    public void getSizeBTreeTest() {
        IBTreeRepository<User> userIBTreeRepository = new BTreeRepository();
        userIBTreeRepository.setSizeBTree(10);
        int currentResult = userIBTreeRepository.getSizeBTree();
        int expectedResult = 10;
        Assertions.assertEquals(expectedResult, currentResult);
    }
}
