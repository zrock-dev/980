package com.fake_orgasm.generator;

import com.fake_orgasm.generator.utils.FileReader;
import com.fake_orgasm.usersmanagement.model.User;

import java.io.FileNotFoundException;

public class UserGenerator {
    private FileReader firstNamesPool;
    private FileReader firstLastNamesPool;
    private FileReader secondLastNamesPool;

    public UserGenerator() {
        try {
            firstNamesPool = new FileReader(null);
            firstLastNamesPool = new FileReader(null);
            secondLastNamesPool = new FileReader(null);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }
    }

    public User make(){
       return new User(firstNamesPool.nextLine(), firstLastNamesPool.nextLine(), secondLastNamesPool.nextLine());
    }
}
