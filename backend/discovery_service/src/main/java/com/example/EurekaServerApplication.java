package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main Class to set up eureka server.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    /**
     * Method main to launch app.
     *
     * @param args It does not expect any args as parameter.
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
