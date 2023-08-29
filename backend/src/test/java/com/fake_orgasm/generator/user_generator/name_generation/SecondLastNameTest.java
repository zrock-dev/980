package com.fake_orgasm.generator.user_generator.name_generation;

import static com.fake_orgasm.generator.user_generator.name_generation.BasicTest.checkPattern;
import static org.junit.jupiter.api.Assertions.*;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import com.fake_orgasm.users_management.models.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class SecondLastNameTest {

    @Test
    void patternValidity() {
        Set<String> pattern = new HashSet<>();
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        for (int i = 0; i < UserNameGenerator.GENERATION_CHUNK_SIZE; i++) {
            String name = userNameGenerator.make().getSecondLastName();
            assertTrue(pattern.add(name), String.format("Name: %s is repeated\n", name));
        }

        assertEquals(UserNameGenerator.GENERATION_CHUNK_SIZE, pattern.size());
    }

    @Test
    void secondLastNamePop() {
        int maxGenerationLimit = (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, 3);
        List<String> names = new ArrayList<>();
        User user;
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userNameGenerator.make();
            String secondLastName = user.getSecondLastName();
            assertNotNull(secondLastName);
            names.add(secondLastName);
        }

        List<String> pattern = new ArrayList<>(UserNameGenerator.GENERATION_CHUNK_SIZE);
        for (int i = 0; i < UserNameGenerator.GENERATION_CHUNK_SIZE; i++) {
            String name = names.get(i);
            assertFalse(pattern.contains(name), String.format("Name: %s is repeated\n", name));
            pattern.add(name);
        }

        checkPattern(pattern, names.iterator());
    }
}
