package com.fake_orgasm;

import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.BTreeService;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BTreeServiceTest {
    @Test
    public void updateUserTest(){
        BTreeService bTreeService = new BTreeService();
        LocalDate date = LocalDate.of(1999, 12, 12);
        User userTest = new User(123, "Jose", "Morales", date, Category.VIP, "Bol");
        userTest.setSecondName("Fernando");
        userTest.setSecondLastName("Perez");
        User userUpdated = new User(321, "Josefina", "Morales", date, Category.VIP, "Bol");
        userUpdated.setSecondName("VIllca");
        userUpdated.setSecondLastName("Villca");
        boolean currentResult = false;
        try {
            bTreeService.create(userTest);
            currentResult = bTreeService.update(userTest, userUpdated);
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(currentResult);

        User userTest2 = new User(123, "Mario", "Benedeti", date, Category.VIP, "Bol");
        userTest2.setSecondName("Sangueza");
        userTest2.setSecondLastName("Oro");

        User userUpdated2 = new User(321, "Maria", "Villca", date, Category.VIP, "Bol");
        userUpdated2.setSecondName("Victor");
        userUpdated2.setSecondLastName("Villca");

        try {
            bTreeService.create(userTest2);
            currentResult = bTreeService.update(userTest2, userUpdated2);
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(currentResult);

        User userTest3 = new User(123, "Fernando", "Caballero", date, Category.VIP, "Bol");
        userTest3.setSecondName("Mauricio");
        userTest3.setSecondLastName("Navarro");

        User userUpdated3 = new User(321, "Fernanda", "Villca", date, Category.VIP, "Bol");
        userUpdated3.setSecondName("Victoria");
        userUpdated3.setSecondLastName("Villca");

        try {
            bTreeService.create(userTest3);
            currentResult = bTreeService.update(userTest3, userUpdated3);
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(currentResult);

        User userTest4 = new User(123, "Santiago", "Espinoza", date, Category.VIP, "Bol");
        userTest4.setSecondName("Miguel");
        userTest4.setSecondLastName("Ohara");

        User userUpdated4 = new User(321, "Santiago", "Villca", date, Category.VIP, "Bol");
        userUpdated4.setSecondName("Vicente");
        userUpdated4.setSecondLastName("Villca");

        try {
            bTreeService.create(userTest4);
            currentResult = bTreeService.update(userTest4, userUpdated4);
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(currentResult);
    }
}
