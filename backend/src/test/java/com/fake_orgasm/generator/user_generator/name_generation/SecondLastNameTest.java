package com.fake_orgasm.generator.user_generator.name_generation;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.fake_orgasm.generator.user_generator.name_generation.BasicTest.checkPattern;
import static org.junit.jupiter.api.Assertions.*;

public class SecondLastNameTest {

    @Test
    void patternValidity(){
        Set<String> pattern = new HashSet<>();
        UserGenerator userGenerator = new UserGenerator();
        for (int i = 0; i < UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            String name = userGenerator.make().getSecondLastName();
            assertTrue(pattern.add(name), String.format("Name: %s is repeated\n", name));
        }

        assertEquals(UserGenerator.GENERATION_CHUNK_SIZE, pattern.size());
    }

    @Test
    void secondLastNamePop() {
        int maxGenerationLimit = (int) Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, 3);
        List<String> names = new ArrayList<>();
        User user;
        UserGenerator userGenerator = new UserGenerator();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userGenerator.make();
            String secondLastName = user.getSecondLastName();
            assertNotNull(secondLastName);
            names.add(secondLastName);
        }

        List<String> pattern = new ArrayList<>(UserGenerator.GENERATION_CHUNK_SIZE);
        for (int i = 0; i < UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            String name = names.get(i);
            assertFalse(pattern.contains(name), String.format("Name: %s is repeated\n", name));
            pattern.add(name);
        }

        checkPattern(pattern, names.iterator());
    }
}
