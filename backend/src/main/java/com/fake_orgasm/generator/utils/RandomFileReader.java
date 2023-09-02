package com.fake_orgasm.generator.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFileReader extends Thread {

    private Random random;
    private int lastIndex;
    private String filePath;
    private ArrayList<String> lines;

    public RandomFileReader(String filePath) {
        this.filePath = filePath;
        lastIndex = 0;
        random = new Random();
        lines = new ArrayList<>();
    }

    public void loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }

    public String getRandomLine() {
        String randomLine = "";
        int randomIndex;

        if (!lines.isEmpty()) {
            do {
                randomIndex = random.nextInt(lines.size());
            } while (lastIndex == randomIndex);
            randomLine = lines.get(randomIndex);
            lastIndex = randomIndex;
        }

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