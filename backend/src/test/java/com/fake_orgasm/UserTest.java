package com.fake_orgasm;

import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    /**
     * This method test the compare to function.
     */
    @Test
    public void compareToTest() {
        User user1 = new User(123, "Jorge", "Heredia", new Date(), Category.VIP, "Bolivia");
        User user2 = new User(189, "Abraham", "Heredia", new Date(), Category.VIP, "Bolivia");
        User user3 = new User(173, "Jorge", "Arano", new Date(), Category.VIP, "Bolivia");
        User user4 = new User(321, "Jorge", "Heredia", new Date(), Category.VIP, "Bolivia");
        User user5 = new User(123, "Simon", "Lopez", new Date(), Category.VIP, "Bolivia");

        int currentResult = user1.compareTo(user2);
        Assertions.assertEquals(-1, currentResult);

        currentResult = user1.compareTo(user3);
        Assertions.assertEquals(-1, currentResult);

        currentResult = user3.compareTo(user1);
        Assertions.assertEquals(1, currentResult);

        currentResult = user1.compareTo(user4);
        Assertions.assertEquals(-2, currentResult);

        currentResult = user1.compareTo(user5);
        Assertions.assertEquals(1, currentResult);
    }
}
