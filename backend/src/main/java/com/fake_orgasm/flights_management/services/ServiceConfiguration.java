package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.generator.flight_generator.FlightGeneratorHandler;
import com.fake_orgasm.users_management.services.IUserManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private IUserManagement userManagement;
    private AirportRepository airportRepository;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;

    public ServiceConfiguration(IUserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @Bean
    public FlightRepository getFlightManagement() {
        if (airportRepository == null) {
            flightRepository = new FlightRepository();
            flightRepository.createTable();
        }

        return flightRepository;
    }

    @Bean
    public TicketRepository getTicketManagement() {
        if (ticketRepository == null) {
            ticketRepository = new TicketRepository();
            ticketRepository.createTable();
        }

        return ticketRepository;
    }

    @Bean
    public AirportRepository getAirportManagement() {
        if (airportRepository == null) {
            airportRepository = new AirportRepository();
            airportRepository.createTable();
        }

        return airportRepository;
    }

    @Bean
    public FlightGeneratorHandler getGeneratorHandler() {
        return new FlightGeneratorHandler(userManagement, getAirportManagement(),
                getFlightManagement(), getTicketManagement());
    }

}