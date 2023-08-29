package com.fake_orgasm.generator.user_generator.name_generation;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftingBehaviourTest {

    private void populate(UserGenerator userGenerator, int limit, List<User> list, Stack<User> stack) {
        for (int i = 0; i < limit; i++) {
            User user = userGenerator.make();
            list.add(user);
            stack.push(user);
        }
    }

    @Test
    void checkGenerationGoal() {
        UserGenerator userGenerator = new UserGenerator();
        int maxGenerationLimit = (int) Math.pow(UserGenerator.GENERATION_CHUNK_SIZE, UserGenerator.GENERATION_STACKS);
        int chunksAmount = 1;

        List<User> users = new ArrayList<>();
        Stack<User> userStack = new Stack<>();
        for (int i = 1; i <= chunksAmount; i++) {
            populate(userGenerator, maxGenerationLimit, users, userStack);
            assertEquals(maxGenerationLimit * i, users.size());
        }

        assertEquals(chunksAmount * maxGenerationLimit, users.size());
    }
}
