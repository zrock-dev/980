package com.fake_orgasm.generator.utils;

import com.fake_orgasm.generator.UserGenerator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourcesIntegrityTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void filesIntegrity(){
        String[] names = new String[]{"first_name_pool", "second_last_name_pool", "first_last_name_pool"};
        for (String name : names) {
            checkFile(name);
        }
    }

    private void checkFile(String fileName){
        try {
            FileReader fileReader = new FileReader(String.format("%s/%s.txt", UserGenerator.GENERATOR_ROOT, fileName));
            Set<String> items = new HashSet<>();
            while (fileReader.hasNext()){
                String item = fileReader.nextLine();
                assertTrue(items.add(item), String.format("Failed with file: %s", fileName));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
