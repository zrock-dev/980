package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * The `InsertionTest` class is responsible for testing the insertion functionality.
 * It provides test cases and methods to verify the correct behavior of insertion operations.
 */
public class InsertionTest {

    /**
     * Generates a random User object.
     *
     * @return The randomly generated User object.
     */
    @Test
    private User createRandomUser() {
        Random random = new Random();

        String[] names = {"Alice", "Bob", "Char", "David", "Ella", "Frank", "Grace", "Henry", "Isa", "Jack"};
        String[] lastNames = {"Smith", "Xi", "Wo", "Jones", "Brown", "Davis", "Mi", "Willa", "Moore", "Tay"};
        Category[] categories = {Category.REGULAR_PASSENGER, Category.VIP, Category.FREQUENT_PASSENGER};
        String[] countries = {"USA", "Canada", "UK", "Australia", "France", "Germany", "Japan", "Brazil"};

        String firstName = names[random.nextInt(names.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        LocalDate localDate = LocalDate.of(2004, 04, 25);
        Category category = categories[random.nextInt(categories.length)];
        String country = countries[random.nextInt(countries.length)];

        return new User(hashCode(), firstName, lastName, localDate, category, country);
    }

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

        long start = System.nanoTime();
        for (int i = 0; i <= 100_000; i++) {
            bTree.insert(createRandomUser());
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);

        // 100k users = 12s
        // 1M users = 120s = 2 min
        // 10M users = 1200s = 20 min
    }

    /**
     * This function is a test case for the search functionality.
     */
    @Test
    public void searchTest() {
        BTree<User> bTree = new BTree<>(40, new BTreeRepository());
        for (int i = 0; i < 1000; i++) {
            bTree.searchKey(createRandomUser());
        }
        bTree.getRoot().printTree("");
    }
}
