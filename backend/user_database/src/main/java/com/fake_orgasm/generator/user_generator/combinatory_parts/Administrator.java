package com.fake_orgasm.generator.user_generator.combinatory_parts;

import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The Administrator class manages the initialization and resetting of Piece handlers for username generation.
 * It coordinates the startup and stack-filling operations for each handler.
 */
public class Administrator {
    private final Logger logger = LogManager.getLogger(getClass());
    private final Piece[] handlers;

    /**
     * Initializes a new instance of the Administrator class with an array of Piece handlers.
     *
     * @param handlers An array of Piece handlers to be managed by the Administrator.
     */
    public Administrator(Piece[] handlers) {
        this.handlers = handlers;
    }

    /**
     * Initiates the startup process for each handler.
     * Performs a shift operation and starts each handler.
     *
     * @throws IOException If an I/O error occurs during the shift or startup process.
     */
    public void startup() throws IOException {
        for (Piece handler : handlers) {
            if (!handler.shift()) {
                logger.error("Shifting operation unsuccessful");
            }
            handler.startup();
        }
    }

    /**
     * Clears and refills the stacks of each handler.
     * Performs a shift operation, clears non-empty stacks, and starts each handler.
     *
     * @throws IOException If an I/O error occurs during the shift or startup process.
     */
    public void fillStacks() throws IOException {
        for (Piece handler : handlers) {
            if (!handler.isEmpty()) {
                handler.clear();
            }

            if (!handler.shift()) {
                logger.error("Shifting operation unsuccessful");
            }
            handler.startup();
        }
    }
}
