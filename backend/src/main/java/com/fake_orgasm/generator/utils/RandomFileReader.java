package com.fake_orgasm.generator.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * The RandomFileReader class reads lines from a file and provides a method to
 * retrieve a random line from the file.
 * <p>
 * It operates in a separate thread to load the file contents asynchronously.
 */
public class RandomFileReader extends Thread {

    private Random random;
    private int lastIndex;
    private String filePath;
    private String[] lines;

    /**
     * This method constructs a RandomFileReader instance with the specified file path and the
     * number of lines to read.
     *
     * @param filePath      The path to the file to read.
     * @param numberOfLines The number of lines to read from the file.
     */
    public RandomFileReader(String filePath, int numberOfLines) {
        this.filePath = filePath;
        lastIndex = 0;
        random = new Random();
        lines = new String[numberOfLines];
    }

    /**
     * This method loads the contents of the file into memory by reading each
     * line and storing it in an array.
     *
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            lines[index] = line;
            index++;
        }
        reader.close();
    }

    /**
     * This method retrieves a random line from the loaded file contents.
     *
     * @return A randomly selected line from the file.
     */
    public String getRandomLine() {
        String randomLine = "";
        int randomIndex;

        do {
            randomIndex = random.nextInt(lines.length);
        } while (lastIndex == randomIndex);
        randomLine = lines[randomIndex];
        lastIndex = randomIndex;

        return randomLine;
    }

    /**
     * This method overrides the run method to load the file contents when the thread is started.
     */
    @Override
    public void run() {
        try {
            loadFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}