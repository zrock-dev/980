package com.fake_orgasm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application to launch boot app.
 */
@SpringBootApplication
public class Application {

    /**
     * Method main to launch app.
     *
     * @param args It does not expect any args as parameter.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
