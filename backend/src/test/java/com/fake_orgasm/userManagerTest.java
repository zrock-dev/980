package com.fake_orgasm;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.Page;
import com.fake_orgasm.users_management.services.UserManager;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class userManagerTest {
    @Test
    void createTest() {
        UserManager userManager = new UserManager();
        User userTest = new User("Jhonatan", "Jorge", "Rodriguez", "Esprella");
        userTest.setId(123);
        userTest.setFlights(List.of("asd", "asd", "asd"));
        userTest.setDateBirth(LocalDate.now());
        boolean result = userManager.create(userTest);

        Assertions.assertTrue(result);

        User userReturned = userManager.getUser(userTest);
        Assertions.assertEquals(userTest, userReturned);

        User userTest2 = new User("Flor", "Rossio", "Meneces", "Esprella");
        userTest2.setId(321);
        userTest2.setFlights(List.of("asd"));
        userTest2.setDateBirth(LocalDate.now());

        result = userManager.create(userTest2);

        Assertions.assertTrue(result);

        userReturned = userManager.getUser(userTest2);
        Assertions.assertEquals(userTest2, userReturned);
    }

    @Test
    void deleteTest() {
        UserManager userManager = new UserManager();
        User userTest = new User("Luis", "Mauricio", "Rodriguez", "Esprella");
        userTest.setId(111);
        userTest.setFlights(List.of("asd", "asd", "asd"));
        userTest.setDateBirth(LocalDate.now());
        userManager.create(userTest);
        userManager.delete(userTest);
        User result = UserManager.getBTree().searchKey(userTest);
        Assertions.assertNull(result);

        User userTest2 = new User("Flor", "Maribel", "Caballero", "Esprella");
        userTest2.setId(222);
        userTest2.setFlights(List.of("asd"));
        userTest2.setDateBirth(LocalDate.now());

        userManager.create(userTest2);
        userManager.delete(userTest2);
        result = UserManager.getBTree().searchKey(userTest2);
        Assertions.assertNull(result);
    }

    @Test
    void updateUserTest() {
        UserManager userManager = new UserManager();
        User userTest = new User("Eduard", "Jhon", "Ruiz", "Esprella");
        userTest.setId(555);
        userTest.setFlights(List.of("asd", "asd", "asd"));
        userTest.setDateBirth(LocalDate.now());
        userManager.create(userTest);

        User userUpdated = new User("Eduardo", "Jhonatan", "Ruiz", "Esprella");
        userUpdated.setId(555);
        userUpdated.setFlights(List.of("555"));
        userUpdated.setDateBirth(LocalDate.now());
        boolean result = userManager.update(userTest, userUpdated);
        Assertions.assertTrue(result);

        User userResult = UserManager.getBTree().searchKey(userUpdated);
        Assertions.assertEquals(userUpdated, userResult);
    }

    @Test
    void getUserByPageTest() {
        UserManager userManager = new UserManager();
        userManager.generateUsers(100);
        Page page = userManager.getUsersByPage(2);
        Assertions.assertEquals(20, page.elements().size());

        page = userManager.getUsersByPage(3);
        Assertions.assertEquals(20, page.elements().size());
    }

    @Test
    void searchTest() {
        UserManager userManager = new UserManager();
        userManager.generateUsers(100);
        User userSearched = new User("Simon", "Jorge", "Petrikov", "Esprella");
        userSearched.setId(999);
        userSearched.setFlights(List.of("asd", "asd", "asd"));
        userSearched.setDateBirth(LocalDate.now());
        userManager.create(userSearched);
        Page usersFound = userManager.search("simon Jorge pe", 0);
        Assertions.assertEquals(1, usersFound.elements().size());
    }
}
