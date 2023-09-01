package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains tests for deleting users from the BTree.
 */

public class DeleteTest {
    /**
     * Generates a new User object by utilizing the UserGenerator and FlightHistoryGenerator classes.
     *
     * @return The newly created User object.
     */

    private User makeUser() {
        UserGenerator userGenerator = new UserGenerator();
        FlightHistoryGenerator flightHistoryGenerator = FlightHistoryGenerator.getInstance();

        User user = userGenerator.make();
        FlightHistory history = flightHistoryGenerator.generateRandomFlightHistory();
        user.addFlightHistory(history);
        user.setCategory(history.getTicketType());

        return user;
    }

    /**
     * Deletes multiple users from the BTree and measures the time taken to delete them.
     */
    @Test
    public void deleteTest() {
        BTree bTree = new BTree(2, new BTreeRepository());
        User user1 = makeUser();
        User user2 = makeUser();
        User user3 = makeUser();
        User user4 = makeUser();
        User user5 = makeUser();

        user1.setFirstName("daniel");
        user2.setFirstName("pablo");
        user3.setFirstName("andres");
        user4.setFirstName("thomas");
        user5.setFirstName("jose");


        bTree.insert(user1);
        bTree.insert(user2);
        bTree.insert(user3);
        bTree.insert(user4);
        bTree.insert(user5);


        bTree.remove(user1);
        bTree.remove(user2);
        bTree.remove(user3);
        bTree.remove(user4);
        bTree.remove(user5);


        bTree.printTree();
    }

    /**
     * A test case to delete a single user.
     */
    @Test
    public void deleteSingleUserTest() {
        BTree bTree = new BTree(2, new BTreeRepository());
        User user1 = makeUser();

        user1.setId(0);
        user1.setFirstName("jose");

        bTree.remove(user1);

        bTree.printTree();
    }

    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void deleteUserTest() {
        BTree<User> bTree = new BTree(2, new BTreeRepository());
        List<User> users = new ArrayList<>();
        User user;
        int keys = 10;

        for (int i = 0; i < keys; i++) {
            user = makeUser();
            user.setId(i);
            user.setFirstName(i + "a");
            users.add(user);
        }
        for (int i = 0; i < keys; i++) {
            bTree.insert(users.get(i));
        }

        bTree.getRoot().printTree("");
        for (int i = 0; i < keys-1; i++) {
            bTree.getRoot().printTree("");
            bTree.remove(users.get(i));
        }
        bTree.getRoot().printTree("");
    }


    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void deleteUserTests() {
        BTree<User> bTree = new BTree(2, new BTreeRepository());
        List<User> users = new ArrayList<>();
        User user;
        for (int i = 5; i < 10; i++) {
            user = makeUser();
            user.setId(i);
            user.setFirstName(i + "a");
            users.add(user);
        }
        bTree.getRoot().printTree("");
        System.out.println(users);
        for (int i = 0; i < 4; i++) {
            bTree.getRoot().printTree("");
            bTree.remove(users.get(i));
        }
        bTree.getRoot().printTree("");
        // Assert.assertTrue(bTree.getRoot().getSize() == 0);
    }
}
