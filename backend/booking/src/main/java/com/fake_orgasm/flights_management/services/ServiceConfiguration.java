package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flight_generator.FlightGeneratorHandler;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This class provides configuration for creating and managing beans
 * related to the flight management system.
 */
@Configuration
public class ServiceConfiguration {

    private AirportRepository airportRepository;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;

    /**
     * This method creates and configures a bean for managing airport-related operations.
     *
     * @return An AirportRepository bean for managing airport-related operations.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * This method creates and configures a bean for managing flight-related operations.
     *
     * @return A FlightRepository bean for managing flight-related operations.
     */
    @Bean
    public FlightRepository getFlightManagement() {
        if (airportRepository == null) {
            flightRepository = new FlightRepository();
            flightRepository.createTable();
        }

        return flightRepository;
    }

    /**
     * This method creates and configures a bean for managing ticket-related operations.
     *
     * @return A TicketRepository bean for managing ticket-related operations.
     */
    @Bean
    public TicketRepository getTicketManagement() {
        if (ticketRepository == null) {
            ticketRepository = new TicketRepository();
            ticketRepository.createTable();
        }

        return ticketRepository;
    }

    /**
     * This method creates and configures a bean for managing airport-related operations.
     *
     * @return An AirportRepository bean for managing airport-related operations.
     */
    @Bean
    public AirportRepository getAirportManagement() {
        if (airportRepository == null) {
            airportRepository = new AirportRepository();
            airportRepository.createTable();
        }

        return airportRepository;
    }

    /**
     * Returns a RestClient object.
     *
     * @return a RestClient object
     */
    public RestClient getRestClient() {
        return new RestClient(restTemplate());
    }

    /**
     * This method creates and configures a bean for handling flight generation.
     *
     * @return A FlightGeneratorHandler bean for generating and managing flights.
     */
    @Bean
    public FlightGeneratorHandler getGeneratorHandler() {
        return new FlightGeneratorHandler(
                getRestClient(), getAirportManagement(), getFlightManagement(), getTicketManagement());
    }
}
