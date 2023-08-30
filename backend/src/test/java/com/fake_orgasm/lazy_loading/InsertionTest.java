package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.Random;

public class InsertionTest {


    @Test
    private User createRandomUser() {
        Random random = new Random();

        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Ella", "Frank", "Grace", "Henry", "Isabel", "Jack"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        Category[] categories = {Category.REGULAR_PASSENGER, Category.VIP, Category.FREQUENT_PASSENGER};
        String[] countries = {"USA", "Canada", "UK", "Australia", "France", "Germany", "Japan", "Brazil", "China", "India"};

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        Date birthDate = new Date();
        Category category = categories[random.nextInt(categories.length)];
        String country = countries[random.nextInt(countries.length)];

        return new User( hashCode(), firstName, lastName, birthDate, category, country);
    }


    @Test
    public void rep1() throws InterruptedException {
        BTree<User> bTree = new BTree<>(3, true);


        //long start = System.nanoTime();
        //for (int i = 0; i <= 15; i++) {
            bTree.insert(new User(hashCode(), "Alice", "Davis", new Date(), Category.REGULAR_PASSENGER, "USA"));
        //}   "lastName": "Davis",
        //      "country": "USA",
        //      "name": "Alice",
        //      "id": 1704491411,
        //      "dateBirth": 1693331658174,
        //      "category": "REGULAR_PASSENGER"
        //    }
        long end = System.nanoTime();
        //System.out.println((end-start)/1e+9);

        // 100k users = 12s
        // 1M users = 120s = 2 min
        // 10M users = 1200s = 20 min
    }

    @Test
    public void rep2() {
        BTree<User> bTree = new BTree<>(10, true);
        bTree.getRoot().printTree("");

        for(int i = 0 ; i< 1000; i ++){
            bTree.searchKey(createRandomUser());
        }
        System.out.println("*******************************************************************************");
        bTree.getRoot().printTree("");
    }

}
