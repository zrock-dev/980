package com.fake_orgasm.generator.user_generator.name_generation;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FirstNameTest {
    @Test
    void checkRepetitionsValidity() {
        int maxGenerationLimit =
                (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, UserNameGenerator.GENERATION_STACKS);
        HashMap<String, Integer> nameRepetitions = new HashMap<>();
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        User user;
        for (int i = 0; i < maxGenerationLimit; i++) {
            user = userNameGenerator.make();
            String name = user.getFirstName();

            if (nameRepetitions.containsKey(name)) {
                int repetitions = nameRepetitions.get(name);
                repetitions++;
                assertFalse(
                        repetitions > maxGenerationLimit,
                        String.format(
                                "The name %s repetitions count: %d is not under the repetitions threshold\n",
                                name, repetitions));
                nameRepetitions.put(name, repetitions);
            } else {
                nameRepetitions.put(name, 1);
            }
        }
    }
}
