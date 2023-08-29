package com.fake_orgasm.generator.user_generator.combinatory_parts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Administrator {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Piece[] handlers;

    public Administrator(Piece[] handlers) {
        this.handlers = handlers;
    }

    public void startup() throws IOException {
        for (Piece handler : handlers) {
            if (!handler.shift()) {
                logger.error("Shifting operation unsuccessful");
            }
            handler.startup();
        }
    }

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
