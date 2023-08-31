package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public IFlightManagement getFlightManagement() {
        return new FlightRepository();
    }

    @Bean
    public ITicketManagement getTicketManagement() {
        return new TicketRepository();
    }

}