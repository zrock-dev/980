package com.fake_orgasm.generator.user_generator.combinatory_parts;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.generator.utils.FileReader;
import com.fake_orgasm.generator.utils.Notifiable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Piece {
    protected final List<String> items;
    protected final Notifiable neighbor;
    private final FileReader fileReader;

    protected String current;
    protected Iterator<String> iterator;

    public Piece(FileReader fileReader, Notifiable neighbor) {
        this.fileReader = fileReader;
        this.neighbor = neighbor;
        this.items = new ArrayList<>(UserGenerator.GENERATION_CHUNK_SIZE);

        current = null;
    }

    public abstract String next();

    public boolean shift() throws IOException {
        boolean success = true;
        for (int i = 0; i < UserGenerator.GENERATION_CHUNK_SIZE; i++) {
            if (!fileReader.hasNext()) {
                fileReader.load();
            }
            items.add(fileReader.nextLine());
        }
        return success;
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return !iterator.hasNext();
    }

    public abstract void startup();
}
