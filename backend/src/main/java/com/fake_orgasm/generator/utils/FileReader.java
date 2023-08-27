package com.fake_orgasm.generator.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileReader {

    private final LineIterator lineIterator;

    public FileReader(String filePath) throws IOException {
        lineIterator = FileUtils.lineIterator(new File(filePath), "UTF-8");
    }

    public String nextLine() {
        return lineIterator.nextLine();
    }

    public boolean hasNext() {
        if (!lineIterator.hasNext()) {
            lineIterator.close();
        }

        return lineIterator.hasNext();
    }
}
