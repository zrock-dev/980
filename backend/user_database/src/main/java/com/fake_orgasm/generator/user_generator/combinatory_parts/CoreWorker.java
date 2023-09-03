package com.fake_orgasm.generator.user_generator.combinatory_parts;

import com.fake_orgasm.generator.utils.Notifiable;
import com.fake_orgasm.utils.FileReader;

/**
 * The CoreWorker class represents a worker that operates on a collection of items and interacts with a neighbor object.
 * It extends the Piece class and provides methods for iterating through items and performing notifications.
 */
public class CoreWorker extends Piece {

    /**
     * Constructs a CoreWorker object with the specified FileReader and Notifiable neighbor.
     *
     * @param fileReader The FileReader object to read items from.
     * @param neighbor   The Notifiable object representing the neighbor to interact with.
     */
    public CoreWorker(FileReader fileReader, Notifiable neighbor) {
        super(fileReader, neighbor);
    }

    /**
     * Retrieves the next item in the collection. If the collection is empty, resets the iterator
     * and notifies the neighbor.
     *
     * @return The next item in the collection.
     */
    public String next() {
        if (isEmpty()) {
            setIterator(getItems().iterator());
            getNeighbor().doNotify();
        }

        return getIterator().next();
    }

    /**
     * Overrides the startup method from the Piece class to initialize the iterator for the collection.
     */
    @Override
    public void startup() {
        setIterator(getItems().iterator());
    }
}
