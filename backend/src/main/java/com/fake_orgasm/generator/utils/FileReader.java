package com.fake_orgasm.generator.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileReader {
    private LineIterator lineIterator;
    private final String filePath;

    public FileReader(String filePath) throws IOException {
        this.filePath = filePath;
        load();
    }

    public String nextLine() {
        return lineIterator.nextLine();
    }

    public void load() throws IOException {
        lineIterator = FileUtils.lineIterator(new File(filePath), "UTF-8");
    }

    public boolean hasNext() {
        return lineIterator.hasNext();
    }
}
