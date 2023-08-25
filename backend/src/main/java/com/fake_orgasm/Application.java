package com.fake_orgasm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class to set up project application.
 */
@SpringBootApplication
public class Application {

    /**
     *
     */
    // todo: Application class should be ignored by the checkstyle since
    // its simplicity.
    protected Application() {
        throw new UnsupportedOperationException();
    }

    /**
     * This is the main function of the application it is meant to
     * start the whole app.
     *
     * @param args It does not expect any args as parameter.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
