package com.fake_orgasm.generator.user_generator.name_generation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import com.fake_orgasm.users_management.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class ShiftingBehaviourTest {

    private void populate(UserNameGenerator userNameGenerator, int limit, List<User> list, Stack<User> stack) {
        for (int i = 0; i < limit; i++) {
            User user = userNameGenerator.make();
            list.add(user);
            stack.push(user);
        }
    }

    @Test
    void checkGenerationGoal() {
        UserNameGenerator userNameGenerator = new UserNameGenerator();
        int maxGenerationLimit =
                (int) Math.pow(UserNameGenerator.GENERATION_CHUNK_SIZE, UserNameGenerator.GENERATION_STACKS);
        int chunksAmount = 1;

        List<User> users = new ArrayList<>();
        Stack<User> userStack = new Stack<>();
        for (int i = 1; i <= chunksAmount; i++) {
            populate(userNameGenerator, maxGenerationLimit, users, userStack);
            assertEquals(maxGenerationLimit * i, users.size());
        }

        assertEquals(chunksAmount * maxGenerationLimit, users.size());
    }
}
