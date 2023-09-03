package com.fake_orgasm.generator.user_generator.name_generation;

import static org.junit.jupiter.api.Assertions.*;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import com.fake_orgasm.users_management.models.User;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BasicTest {
    /**
     * Verify that the name is complete.
     */
    @Test
    void verifyNameIsComplete() {
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        User user = userNameGenerator.make();
        String fullName = user.getFullName();
        String[] words = fullName.split("\\s");

        assertNotNull(user);
        assertNotEquals("", fullName);
        assertEquals(UserNameGenerator.GENERATION_STACKS, words.length);
    }

    /**
     * Checks if the given pattern matches the items.
     *
     * @param pattern the pattern to be checked
     * @param items   the items to be matched against the pattern
     */
    public static void checkPattern(List<String> pattern, Iterator<String> items) {
        Iterator<String> patternIterator = pattern.iterator();
        while (items.hasNext()) {
            if (!patternIterator.hasNext()) {
                patternIterator = pattern.iterator();
            }
            String name = items.next();
            assertEquals(patternIterator.next(), name, String.format("The name '%s' breaks the pattern\n", name));
        }
    }
}
