package com.fake_orgasm.generator.user_generator.combinatory_parts;

import com.fake_orgasm.generator.user_generator.UserNameGenerator;
import com.fake_orgasm.utils.FileReader;
import com.fake_orgasm.utils.Notifiable;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Piece class represents an abstract base class for components that operate on a collection of items and interact
 * with a neighbor object. It provides methods for iterating through items, loading new items from a file, clearing the
 * item collection, and checking if the collection is empty.
 */
public abstract class Piece {

    private final FileReader fileReader;

    @Getter
    private final List<String> items;

    @Getter
    private final Notifiable neighbor;

    @Getter
    @Setter
    private Iterator<String> iterator;

    @Getter
    @Setter
    private String current;

    /**
     * Constructs a Piece object with the specified FileReader and Notifiable neighbor.
     *
     * @param fileReader The FileReader object used to read items from a file.
     * @param neighbor   The Notifiable object representing the neighbor to interact with.
     */
    public Piece(FileReader fileReader, Notifiable neighbor) {
        this.fileReader = fileReader;
        this.neighbor = neighbor;
        this.items = new ArrayList<>(UserNameGenerator.GENERATION_CHUNK_SIZE);

        current = null;
    }

    /**
     * Retrieves the next item in the collection.
     *
     * @return The next item in the collection.
     */
    public abstract String next();

    /**
     * Loads a new chunk of items from the file into the item collection.
     *
     * @return True if loading was successful, false otherwise.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public boolean shift() throws IOException {
        boolean success = true;
        for (int i = 0; i < UserNameGenerator.GENERATION_CHUNK_SIZE; i++) {
            if (!fileReader.hasNext()) {
                fileReader.load();
            }
            items.add(fileReader.nextLine());
        }
        return success;
    }

    /**
     * Clears the item collection.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Checks if the item collection is empty.
     *
     * @return True if the item collection is empty, false otherwise.
     */
    public boolean isEmpty() {
        return !iterator.hasNext();
    }

    /**
     * Abstract method that subclasses should implement to perform startup initialization.
     */
    public abstract void startup();
}
