package com.fake_orgasm.generator;

import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserGeneratorTest {

    @Test
    void verifyNameIsComplete() {
        UserGenerator userGenerator = new UserGenerator();
        User user = userGenerator.make();
        String fullName = user.getFullName();
        String[] words = fullName.split("\\s");

        assertNotNull(user);
        assertNotEquals("", fullName);
        assertEquals(3, words.length);
    }

    @Test
    void verifyGeneratedNameIsUnique() {
        UserGenerator userGenerator = new UserGenerator();
        Set<String> userNames = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            String fullName = userGenerator.make().getFullName();
            if (!userNames.add(fullName)) {
                fail();
            }
        }
    }

    @Test
    void secondLastNamePop() {
        UserGenerator userGenerator = new UserGenerator();
        User user;
        List<String> names = new ArrayList<>();
        for (int i = 0; i < UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            user = userGenerator.make();
            String secondLastName = user.getSecondLastName();
            names.add(secondLastName);
        }

        List<String> pattern = new ArrayList<>();
        for (int i = 0; i < UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            String name = names.get(i);
            assertFalse(pattern.contains(name), String.format("Name: %s is repeated", name));
            assertTrue(pattern.add(name));
        }

        int jumpIndex = UserGenerator.GENERATION_CHUNK_SIZE;
        List<Integer> availableJumps = new ArrayList<>(UserGenerator.GENERATION_CHUNK_SIZE);
        for (int i = 1; i <= UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            availableJumps.add((jumpIndex * i) - 1);
        }

        double maxGenerationLimit = Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, 3) - UserGenerator.GENERATION_CHUNK_SIZE;
        names.clear();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userGenerator.make();
            names.add(user.getSecondLastName());

            if (availableJumps.contains(i)){
                assertEquals(names.size(), pattern.size());
                Iterator<String> namesIterator = names.iterator();
                Iterator<String> patternIterator = pattern.iterator();
                while (namesIterator.hasNext()) {
                    String patternName = patternIterator.next();
                    String listName = namesIterator.next();
                    assertEquals(patternName, listName);
                }
                names.clear();
            }
        }
    }

    @Test
    void firstLastNamePop() {
        int baseIndex = UserGenerator.GENERATION_CHUNK_SIZE;
        List<Integer> availableJumps = new ArrayList<>(UserGenerator.GENERATION_CHUNK_SIZE);
        for (int i = 1; i <= UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            availableJumps.add((baseIndex * i) - 1);
        }

        UserGenerator userGenerator = new UserGenerator();
        User user;
        double maxGenerationLimit = Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, 2);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userGenerator.make();
            if (availableJumps.contains(i)){
                String name = user.getFirstLastName();
                assertFalse(names.contains(name), String.format("Name: %s is repeated", name));
                assertTrue(names.add(name));
            }
        }

        assertEquals(UserGenerator.GENERATION_CHUNK_SIZE, names.size());
    }

    @Test
    void firstNamePop() {
        List<Integer> availableJumps = new ArrayList<>(UserGenerator.GENERATION_CHUNK_SIZE);
        for (int i = 1; i <= UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            availableJumps.add((int) Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, i));
        }

        UserGenerator userGenerator = new UserGenerator();
        User user;
        double maxGenerationLimit = Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, 3);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userGenerator.make();
            if (availableJumps.contains(i)){
                String name = user.getName();
                assertFalse(names.contains(name), String.format("Name: %s is repeated", name));
                assertTrue(names.add(name));
            }
        }

        assertEquals(UserGenerator.GENERATION_CHUNK_SIZE, names.size());
    }

    @Test
    void replenish() {
        UserGenerator userGenerator = new UserGenerator();
        String[] cycleStartName = userGenerator.make().getFullName().split("\\s");

        double maxGenerationLimit = Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, 3);
        for (int i = 1; i < maxGenerationLimit; i++) {
            userGenerator.make();
        }

        String[] cycleEndName = userGenerator.make().getFullName().split("\\s");
        assertNotEquals(cycleStartName[0], cycleEndName[0]);
        assertNotEquals(cycleStartName[1], cycleEndName[1]);
        assertNotEquals(cycleStartName[2], cycleEndName[2]);
    }
}
