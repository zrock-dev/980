package com.fake_orgasm.generator.user_generator.name_generation;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTest {

    @Test
    void verifyNameIsComplete() {
        UserGenerator userGenerator = new UserGenerator();
        User user = userGenerator.make();
        String fullName = user.getFullName();
        String[] words = fullName.split("\\s");

        assertNotNull(user);
        assertNotEquals("", fullName);
        assertEquals(UserGenerator.GENERATION_STACKS, words.length);
    }

    public static void checkPattern(List<String> pattern, Iterator<String> items){
        Iterator<String> patternIterator = pattern.iterator();
        while (items.hasNext()){
            if (!patternIterator.hasNext()){
                patternIterator = pattern.iterator();
            }
            String name = items.next();
            assertEquals(patternIterator.next(), name, String.format("The name '%s' breaks the pattern\n", name));
        }
    }
}
