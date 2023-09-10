package com.fake_orgasm.flight_generator;

import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.Page;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.flights_management.services.RestClient;
import com.fake_orgasm.users_management.models.User;
import org.springframework.web.client.RestTemplate;

/**
 * The RestGenerator class is responsible for generating flight tickets and saving them using a REST API client.
 * It implements the Runnable interface, allowing it to be executed as a separate thread.
 */
public class RestGenerator implements Runnable {
    private FlightGeneratorHandler generator;
    private int from;
    private int to;
    private RestClient restClient;

    /**
     * Constructs a new RestGenerator with the specified range for processing.
     *
     * @param from The starting index for the range of pages to retrieve.
     * @param to   The ending index for the range of pages to retrieve.
     */
    public RestGenerator(int from, int to) {
        this.from = from;
        this.to = to;
        generator = new FlightGeneratorHandler(new RestClient(new RestTemplate()), new AirportRepository(), new FlightRepository(), new TicketRepository());
        restClient = new RestClient(new RestTemplate());
    }

    /**
     * Executes the thread to retrieve user data from REST API pages and generate flight tickets.
     * It retrieves pages from 'from' to 'to' and generates 2 tickets for each user on each page.
     */
    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            Page<User> page = restClient.getPage(i);
            this.generator.generateTicketsAndSave(page.elements(), 2);
        }
    }
}