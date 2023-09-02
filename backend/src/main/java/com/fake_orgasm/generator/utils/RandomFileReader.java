package com.fake_orgasm.generator.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class RandomFileReader extends Thread {

    private Random random;
    private int lastIndex;
    private String filePath;
    private String[] lines;

    public RandomFileReader(String filePath, int numberOfLines) {
        this.filePath = filePath;
        lastIndex = 0;
        random = new Random();
        lines = new String[numberOfLines];
    }

    public void loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            lines[index] = (line);
            index++;
        }
        reader.close();
    }

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

    @Override
    public void run() {
        try {
            loadFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}