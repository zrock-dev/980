package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * The `InsertionTest` class is responsible for testing the insertion functionality.
 * It provides test cases and methods to verify the correct behavior of insertion operations.
 */
public class InsertionTest {

    /**
     * Test case to measure the performance of insertion in a BTree.
     * <p>
     * This method creates a BTree object and inserts a specified number of random user objects into it.
     * The method measures the time taken for insertion and prints the elapsed time in seconds.
     * The expected time complexity of insertion in a BTree is O(log n).
     */
    @Test
    public void insertTest() {
        BTree<User> bTree = new BTree<>(10, new BTreeRepository());
        UserGenerator userGenerator = new UserGenerator();
        User user;
        long start = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            user = userGenerator.make();
            user.setFlights(new ArrayList<>());
            bTree.insert(user);
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);

        // 100k users = 12s
        // 1M users = 120s = 2 min
        // 10M users = 1200s = 20 min
    }
}
