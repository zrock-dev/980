package com.fake_orgasm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Main Class to set up project application.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class MoneyExchangeApplication {

    /**
     * Method main to launch app.
     *
     * @param args It does not expect any args as parameter.
     */
    public static void main(String[] args) {
        SpringApplication.run(MoneyExchangeApplication.class, args);
    }
}
