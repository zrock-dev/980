package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.Random;

public class DeletionTest {


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

        return new User(hashCode(), firstName, lastName, birthDate, category, country);
    }


    @Test
    public void rep1() {
        BTree<User> bTree = new BTree<>(2, true);

        System.out.println(bTree.getRoot().getKey(0));
        System.out.println(bTree.getRoot().getId());

        for (int i = 0; i < 5; i++) {
            User user = createRandomUser();
            bTree.insert(user);
        }


        System.out.println(bTree.getRoot().getKeys());
        System.out.println(bTree.getRoot().getId());
        System.out.println("-------------------------");
        bTree.getRoot().printTree("");

    }
    /**
     * Test case for the rep2 method.
     *
     * This method tests the functionality of the rep2 method in the class.
     * It creates a BTree object, inserts 5 random user objects into the BTree,
     * and then prints the inserted user objects. It also prints the BTree
     * structure before and after removing the inserted user objects.
     */
    @Test
    public void rep2() {

        BTree<User> bTree = new BTree<>(2, true);

        // Agregar 5 usuarios
        User user1 = createRandomUser();
        User user2 = createRandomUser();
        User user3 = createRandomUser();
        User user4 = createRandomUser();
        User user5 = createRandomUser();

        bTree.insert(user1);
        bTree.insert(user2);
        bTree.insert(user3);
        bTree.insert(user4);
        bTree.insert(user5);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);

        System.out.println("Árbol después de agregar usuarios:");
        bTree.getRoot().printTree("");

        // Eliminar un usuario
        bTree.remove(user4);
        bTree.remove(user1);
        bTree.remove(user5);
        bTree.remove(user3);
        bTree.remove(user2);
        System.out.println("Árbol después de eliminar un usuario:");
        bTree.getRoot().printTree("");
    }


    @Test
    public void rep3() {

        BTree<User> bTree = new BTree<>(2, true);

       User user1 = new User(1328718765, "Charlie", "Taylor", new Date(), Category.VIP, "Japan");

        bTree.remove(user1);

        bTree.getRoot().printTree("");
    }
}
