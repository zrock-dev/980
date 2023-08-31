package com.fake_orgasm.generator.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import com.fake_orgasm.utils.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

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
            System.err.println(e.getMessage());
            fail();
        }
    }
}
