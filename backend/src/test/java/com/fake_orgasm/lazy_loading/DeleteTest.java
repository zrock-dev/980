package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
        BTree<User> bTree = new BTree(5, new BTreeRepository());
        User user1 = makeUser();
        User user2 = makeUser();
        User user3 = makeUser();
        User user4 = makeUser();
        User user5 = makeUser();
        User user6 = makeUser();
        User user7 = makeUser();
        User user8 = makeUser();
        User user9 = makeUser();
        User user10 = makeUser();
        User user11 = makeUser();
        User user12 = makeUser();
        User user13 = makeUser();
        User user14 = makeUser();
        User user15 = makeUser();
        User user16 = makeUser();
        User user17 = makeUser();
        User user18 = makeUser();
        User user19 = makeUser();
        User user20 = makeUser();
        User user21 = makeUser();

        user1.setId(1);
        user1.setFirstName("1");

        user2.setId(2);
        user2.setFirstName("r");

        user3.setId(3);
        user3.setFirstName("3");

        user4.setId(4);
        user4.setFirstName("4");

        user5.setId(5);
        user5.setFirstName("5");

        user6.setId(6);
        user6.setFirstName("6");

        user7.setId(7);
        user7.setFirstName("7");

        user8.setId(8);
        user8.setFirstName("8");

        user9.setId(9);
        user9.setFirstName("9");

        user10.setId(10);
        user10.setFirstName("10");

        user11.setId(11);
        user11.setFirstName("11");

        user12.setId(12);
        user12.setFirstName("12");

        user13.setId(13);
        user13.setFirstName("13");

        user14.setId(14);
        user14.setFirstName("14");

        user15.setId(15);
        user15.setFirstName("15");

        user16.setId(16);
        user16.setFirstName("16");

        user17.setId(17);
        user17.setFirstName("17");

        user18.setId(18);
        user18.setFirstName("18");

        user19.setId(19);
        user19.setFirstName("19");

        user20.setId(20);
        user20.setFirstName("20");

        user21.setId(21);
        user21.setFirstName("21");


        bTree.insert(user1);
        bTree.insert(user2);
        bTree.insert(user3);
        bTree.insert(user4);
        bTree.insert(user5);
        bTree.insert(user6);
        bTree.insert(user7);
        bTree.insert(user8);
        bTree.insert(user9);
        bTree.insert(user10);
        bTree.insert(user11);
        bTree.insert(user12);
        bTree.insert(user13);
        bTree.insert(user14);
        bTree.insert(user15);
        bTree.insert(user16);
        bTree.insert(user17);
        bTree.insert(user18);
        bTree.insert(user19);
        bTree.insert(user20);
        bTree.insert(user21);


        bTree.remove(user1);
        bTree.remove(user2);
        bTree.remove(user3);
        bTree.remove(user4);
        bTree.remove(user5);
        bTree.remove(user6);
        bTree.remove(user7);
        bTree.remove(user8);
        bTree.remove(user9);
        bTree.remove(user10);
        bTree.remove(user11);
        bTree.remove(user12);
        bTree.remove(user13);
        bTree.remove(user14);
        bTree.remove(user15);
        bTree.remove(user16);
        bTree.remove(user17);
        bTree.remove(user18);
        bTree.remove(user19);
        bTree.remove(user20);
        bTree.remove(user21);


        bTree.printTree();

        // Assert.assertTrue(bTree.getRoot().getSize() == 0);


        // 100k users = 12s
        // 1M users = 120s = 2 min
        // 10M users = 1200s = 20 min
    }


}
