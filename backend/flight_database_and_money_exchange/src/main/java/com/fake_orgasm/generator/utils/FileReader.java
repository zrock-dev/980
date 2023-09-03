package com.fake_orgasm.generator.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * The FileReader class provides utility methods for reading lines from a text file.
 * It uses the Apache Commons IO library for file handling.
 */
public class FileReader {

    private LineIterator lineIterator;
    private final String filePath;

    /**
     * Constructs a FileReader object with the specified file path and loads the file.
     *
     * @param filePath The path to the file to be read.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public FileReader(String filePath) throws IOException {
        this.filePath = filePath;
        load();
    }

    /**
     * Reads the next line from the file.
     *
     * @return The next line from the file.
     */
    public String nextLine() {
        return lineIterator.nextLine();
    }

    /**
     * Loads the file and prepares the LineIterator for reading lines.
     *
     * @throws IOException If an I/O error occurs while loading the file.
     */
    public void load() throws IOException {
        lineIterator = FileUtils.lineIterator(new File(filePath), "UTF-8");
    }

    /**
     * Checks if there are more lines to be read from the file.
     *
     * @return True if there are more lines, false otherwise.
     */
    public boolean hasNext() {
        return lineIterator.hasNext();
    }
}
