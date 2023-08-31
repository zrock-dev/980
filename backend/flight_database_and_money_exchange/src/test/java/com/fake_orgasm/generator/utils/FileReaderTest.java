package com.fake_orgasm.generator.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FileReaderTest {

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
