package com.fake_orgasm.generator.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class FileReaderTest {
    protected final Logger logger = LogManager.getLogger(getClass());

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
