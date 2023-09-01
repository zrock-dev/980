package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public FlightRepository getFlightManagement() {
        return new FlightRepository();
    }

    @Bean
    public TicketRepository getTicketManagement() {
        return new TicketRepository();
    }

}