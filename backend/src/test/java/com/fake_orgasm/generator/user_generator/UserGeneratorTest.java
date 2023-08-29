package com.fake_orgasm.generator.user_generator;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import org.junit.jupiter.api.Test;

public class UserGeneratorTest {

    @Test
    void simpleGeneration(){
        UserGenerator userGenerator = new UserGenerator();
        int requiredUsersAmount = 1000000;

        for (int i = 0; i < requiredUsersAmount; i++) {
           userGenerator.make();
        }
    }
}
