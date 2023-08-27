package com.fake_orgasm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBTreeRepository {
    @Test
    public void test(){
        String userHome = System.getProperty("user.home");
        Assertions.assertEquals(" ", userHome);
        System.out.println(userHome);
    }
}
