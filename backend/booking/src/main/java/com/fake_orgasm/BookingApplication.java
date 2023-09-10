package com.fake_orgasm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The main class for the booking application.
 *
 * This class initializes and runs the booking application.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BookingApplication {

    /**
     * Runs the main function of the Java application.
     *
     * @param args the command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }
}
