package com.fake_orgasm.generator.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FileReaderTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    // to-do: Instead of using full paths it could be better just to use relative paths.
    @Test
    void readLinesIntegrity() {
        try {
            FileReader fileReader = new FileReader("src/test/resources/generator/light_text.txt");
            assertTrue(fileReader.hasNext());
            assertEquals("Line 1", fileReader.nextLine());
            assertEquals("Line 2", fileReader.nextLine());
            assertFalse(fileReader.hasNext());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            fail();
        }
    }
}
