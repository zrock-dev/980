package com.fake_orgasm;

import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    /**
     * This method test the compare to function.
     */
    @Test
    public void compareToTest() {
        User user1 = new User(123, "Jorge", "Heredia", "", Category.VIP, "Bolivia");
        User user2 = new User(189, "Abraham", "Heredia", "", Category.VIP, "Bolivia");
        User user3 = new User(173, "Jorge", "Arano", "", Category.VIP, "Bolivia");
        User user4 = new User(321, "Jorge", "Heredia", "", Category.VIP, "Bolivia");
        User user5 = new User(123, "Simon", "Lopez", "", Category.VIP, "Bolivia");

        boolean currentResult = user1.compareTo(user2) < 0;
        Assertions.assertTrue(currentResult);

        currentResult = user1.compareTo(user3) < 0;
        Assertions.assertTrue(currentResult);

        currentResult = user3.compareTo(user1) > 0;
        Assertions.assertTrue(currentResult);

        currentResult = user1.compareTo(user4) < 0;
        Assertions.assertTrue(currentResult);

        currentResult = user1.compareTo(user5) > 0;
        Assertions.assertTrue(currentResult);
    }
}
