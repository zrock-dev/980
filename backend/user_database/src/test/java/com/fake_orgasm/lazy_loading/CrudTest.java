package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.NameIndexer;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for deleting users from the BTree.
 */
public class CrudTest {
    UserGenerator userGenerator;

    /**
     * Sets up the test environment before each test case is run.
     */
    @BeforeEach
    public void setUp() {
        userGenerator = new UserGenerator();
    }

    /**
     * Generates a new User object by utilizing the UserGenerator and FlightHistoryGenerator classes.
     *
     * @return The newly created User object.
     */
    private User makeUser() {
        User user = userGenerator.make();
        user.setFlights(new ArrayList<>());
        return user;
    }

    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void insertUsersTest() {
        BTree<User> bTree = new BTree(10, new BTreeRepository(), new NameIndexer());
        List<User> users = new ArrayList<>();
        User user;
        int keys = 1_000;

        for (int i = 0; i < keys; i++) {
            user = makeUser();
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
    public void deleteUsersTest() {
        BTree<User> bTree = new BTree(10, new BTreeRepository(), new NameIndexer());
        List<User> users = new ArrayList<>();
        User user;
        int keys = 1_000;
        for (int i = 0; i < keys; i++) {
            user = makeUser();
            users.add(user);
        }
        long start = System.nanoTime();
        for (int i = 0; i < keys - 1; i++) {
            if (i != 3) {
                bTree.remove(users.get(i));
            }
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);
        bTree.getRoot().printTree("");
    }

    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void removeFromRootTests() {
        BTree<User> bTree = new BTree(10, new BTreeRepository(), new NameIndexer());
        int keys = 1000; // 100_000;
        long start = System.nanoTime();
        User user;
        for (int i = 0; i < keys - 1; i++) {
            user = bTree.getRoot().getKey(0);
            if (user != null) bTree.remove(user);
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);
        bTree.getRoot().printTree("");
    }

    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void insertASingleUserTest() {
        BTree<User> bTree = new BTree(10, new BTreeRepository(), new NameIndexer());
        User user = makeUser();

        bTree.remove(user);
        bTree.getRoot().printTree("");
    }

    /**
     * Deletes a user from the BTree and verifies that the tree is empty after deletion.
     */
    @Test
    public void deleteASingleUserTest() {
        BTree<User> bTree = new BTree(10, new BTreeRepository(), new NameIndexer());
        User user = makeUser();

        bTree.remove(user);
        bTree.getRoot().printTree("");
    }
}
