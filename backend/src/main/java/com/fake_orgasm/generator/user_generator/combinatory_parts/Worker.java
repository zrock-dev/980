package com.fake_orgasm.generator.user_generator.combinatory_parts;

import com.fake_orgasm.generator.utils.FileReader;
import com.fake_orgasm.generator.utils.Notifiable;

/**
 * The Worker class represents a worker component that operates on a collection of items and interacts with a neighbor
 * object. It extends the Piece class and implements the Notifiable interface. This class provides methods for iterating
 * through items and performing notifications when necessary.
 */
public class Worker extends Piece implements Notifiable {

    /**
     * Constructs a Worker object with the specified FileReader and Notifiable neighbor.
     *
     * @param fileReader The FileReader object to read items from.
     * @param neighbor   The Notifiable object representing the neighbor to interact with.
     */
    public Worker(FileReader fileReader, Notifiable neighbor) {
        super(fileReader, neighbor);
    }

    /**
     * Retrieves the current item in the collection.
     *
     * @return The current item in the collection.
     */
    public String next() {
        return current;
    }

    /**
     * Overrides the startup method from the Piece class to initialize the iterator and set the current item.
     */
    @Override
    public void startup() {
        iterator = items.iterator();
        current = iterator.next();
    }

    /**
     * Performs a notification. If the item collection is not empty, advances the current item. If the collection
     * is empty, resets the iterator, advances the current item, and notifies the neighbor.
     */
    @Override
    public void doNotify() {
        if (!isEmpty()) {
            current = iterator.next();
        } else {
            iterator = items.iterator();
            current = iterator.next();
            neighbor.doNotify();
        }
    }
}
