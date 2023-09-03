package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    }


    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void deleteUserTests() {
        BTree<User> bTree = new BTree(2, new BTreeRepository());
        List<User> users = new ArrayList<>();
        User user;
        for (int i = 0; i < 10; i++) {
            user = makeUser();
            user.setId(i);
            user.setFirstName(i + "a");
            users.add(user);
        }
        bTree.getRoot().printTree("");
        for (int i = 0; i < 9; i++) {
            if (i != 3) {
                bTree.remove(users.get(i));
            }
        }
        bTree.getRoot().printTree("");
    }


    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void insertTopRootUserTests() {
        BTree<User> bTree = new BTree(10, new BTreeRepository());
        List<User> users = new ArrayList<>();
        int keys = 100_000;
        User user;
        for (int i = 0; i < keys; i++) {
            user = makeUser();
            user.setId(i);
            user.setFirstName(i + "a");
            users.add(user);
        }
        long start = System.nanoTime();
        for (int i = 0; i < keys; i++) {
            bTree.insert(users.get(i));
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);
    }


    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void removeRandomUsersTests() {
        BTree<User> bTree = new BTree(10, new BTreeRepository());
        List<User> users = new ArrayList<>();
        int keys = 100_000;
        User user;
        Random random = new Random();
        for (int i = 0; i < keys; i++) {
            int j = random.nextInt(100000) + 1;
            user = makeUser();
            user.setId(i);
            user.setFirstName(i + "a");
            users.add(user);
        }
        long start = System.nanoTime();
        for (int i = 0; i < keys - 10; i++) {
            bTree.remove(users.get(i));
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);
        bTree.getRoot().printTree("");
    }


    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void deleteASingleUserTest() {
        BTree<User> bTree = new BTree(10, new BTreeRepository());
        User user = makeUser();
        int n = 99996;
        user.setId(n);
        user.setFirstName(n + "a");

        bTree.remove(user);
        bTree.getRoot().printTree("");
    }

    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void insertASingleUserTest() {
        BTree<User> bTree = new BTree(2, new BTreeRepository());
        User user = makeUser();
        user.setId(3);
        user.setFirstName(3 + "a");

        bTree.remove(user);
        bTree.getRoot().printTree("");
    }
}
