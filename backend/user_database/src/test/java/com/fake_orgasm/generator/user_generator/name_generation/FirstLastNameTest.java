package com.fake_orgasm.generator.user_generator.name_generation;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.*;


import static com.fake_orgasm.generator.user_generator.name_generation.BasicTest.checkPattern;
import static org.junit.jupiter.api.Assertions.*;

public class FirstLastNameTest {

    @Test
    void checkRepetitionValidity() {
        int maxGenerationLimit = (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, 2);
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        HashMap<String, Integer> hashMap = new HashMap<>(UserNameGenerator.GENERATION_CHUNK_SIZE);

        User user;
        List<User> tmp = new ArrayList<>();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userNameGenerator.make();
            tmp.add(user);
            String firstLastName = user.getFirstLastName();
            assertNotNull(firstLastName);
            if (hashMap.containsKey(firstLastName)) {
                Integer repetitionsCount = hashMap.get(firstLastName);
                repetitionsCount++;
                hashMap.put(firstLastName, repetitionsCount);
                assertFalse(
                        repetitionsCount > UserNameGenerator.GENERATION_CHUNK_SIZE,
                        String.format(
                                "The first last name repetitions count: %d is not under the repetitions threshold\n",
                                repetitionsCount));
            } else {
                hashMap.put(firstLastName, 1);
            }
        }

        assertEquals(UserNameGenerator.GENERATION_CHUNK_SIZE, hashMap.size());

        int anchor = 0;
        for (Integer value : hashMap.values()) {
            anchor += value;
        }
        assertEquals(maxGenerationLimit, anchor);
    }

    @Test
    void patternValidity() {
        int maxGenerationLimit = (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, 2);
        List<Integer> availableJumps = new ArrayList<>(maxGenerationLimit);
        for (int i = 1; i <= maxGenerationLimit; i++) {
            availableJumps.add((UserNameGenerator.GENERATION_CHUNK_SIZE * i) - 1);
        }

        UserNameGenerator userNameGenerator = new UserNameGenerator();
        Set<String> pattern = new HashSet<>();
        User user;
        List<User> tmp = new ArrayList<>();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userNameGenerator.make();
            String firstLastName = user.getFirstLastName();
            assertNotNull(firstLastName);
            tmp.add(user);
            if (availableJumps.contains(i)) {
                assertTrue(pattern.add(firstLastName), String.format("Name: %s is repeated\n", firstLastName));
            }
        }
        assertEquals(UserNameGenerator.GENERATION_CHUNK_SIZE, pattern.size());
    }

    @Test
    void firstLastNamePop() {
        int maxGenerationLimit = (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, 3);
        List<Integer> availableJumps = new ArrayList<>(maxGenerationLimit);
        for (int i = 1; i <= maxGenerationLimit; i++) {
            availableJumps.add((UserNameGenerator.GENERATION_CHUNK_SIZE * i) - 1);
        }

        UserNameGenerator userNameGenerator = new UserNameGenerator();
        List<String> names = new ArrayList<>();
        User user;
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userNameGenerator.make();
            String firstLastName = user.getFirstLastName();
            assertNotNull(firstLastName);
            if (availableJumps.contains(i)) {
                names.add(firstLastName);
            }
        }

        int availableNames = (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, 2);
        assertEquals(availableNames, names.size());

        List<String> pattern = new ArrayList<>();
        for (int i = 0; i < UserNameGenerator.GENERATION_CHUNK_SIZE; i++) {
            String name = names.get(i);
            assertFalse(pattern.contains(name), String.format("Name: %s is repeated\n", name));
            pattern.add(name);
        }

        checkPattern(pattern, names.iterator());
    }
}
