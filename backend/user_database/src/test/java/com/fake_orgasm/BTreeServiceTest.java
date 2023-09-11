package com.fake_orgasm;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.UserManager;
import java.time.LocalDate;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class BTreeServiceTest {
    @Test
    public void updateUserTest() {
        UserManager bTreeService = new UserManager();
        LocalDate date = LocalDate.of(1999, 12, 12);
        User userTest = new User(123, "Jose", "Morales", date, "Bol");
        userTest.setSecondName("Fernando");
        userTest.setSecondLastName("Perez");
        User userUpdated = new User(321, "Josefina", "Morales", date, "Bol");
        userUpdated.setSecondName("VIllca");
        userUpdated.setSecondLastName("Villca");
        boolean currentResult = false;
        bTreeService.create(userTest);
        currentResult = bTreeService.update(userTest, userUpdated);

        Assert.assertTrue(currentResult);

        User userTest2 = new User(123, "Mario", "Benedeti", date, "Bol");
        userTest2.setSecondName("Sangueza");
        userTest2.setSecondLastName("Oro");

        User userUpdated2 = new User(321, "Maria", "Villca", date, "Bol");
        userUpdated2.setSecondName("Victor");
        userUpdated2.setSecondLastName("Villca");

        bTreeService.create(userTest2);
        currentResult = bTreeService.update(userTest2, userUpdated2);

        Assert.assertTrue(currentResult);

        User userTest3 = new User(123, "Fernando", "Caballero", date, "Bol");
        userTest3.setSecondName("Mauricio");
        userTest3.setSecondLastName("Navarro");

        User userUpdated3 = new User(321, "Fernanda", "Villca", date, "Bol");
        userUpdated3.setSecondName("Jonas");
        userUpdated3.setSecondLastName("Villca");

        bTreeService.create(userTest3);
        currentResult = bTreeService.update(userTest3, userUpdated3);

        Assert.assertTrue(currentResult);

        User userTest4 = new User(123, "Santiago", "Espinoza", date, "Bol");
        userTest4.setSecondName("VIllca");
        userTest4.setSecondLastName("Ohara");

        User userUpdated4 = new User(321, "Santiago", "Villca", date, "Bol");
        userUpdated4.setSecondName("Jojo");
        userUpdated4.setSecondLastName("Villca");

        bTreeService.create(userTest4);
        currentResult = bTreeService.update(userTest4, userUpdated4);

        Assert.assertTrue(currentResult);
    }
}
