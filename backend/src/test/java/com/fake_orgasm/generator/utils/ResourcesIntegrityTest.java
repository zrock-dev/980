package com.fake_orgasm.generator.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcesIntegrityTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void filesIntegrity() {
        String[] names =
                new String[] {"first_name_pool", "second_name_pool", "second_last_name_pool", "first_last_name_pool"};
        for (String name : names) {
            checkFile(name);
        }
    }

    private void checkFile(String fileName) {
        try {
            FileReader fileReader =
                    new FileReader(String.format("%s/%s.txt", UserNameGenerator.GENERATOR_ROOT, fileName));
            Set<String> items = new HashSet<>();
            while (fileReader.hasNext()) {
                String item = fileReader.nextLine();
                assertTrue(items.add(item), String.format("Failed with file: %s", fileName));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
