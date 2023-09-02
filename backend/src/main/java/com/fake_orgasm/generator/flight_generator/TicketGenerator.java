package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class TicketGenerator {

    private Random random;
    @Getter
    private ArrayList<Flight> flights;
    private final Category[] CATEGORIES = {Category.VIP,
            Category.FREQUENT_PASSENGER, Category.REGULAR_PASSENGER};

    public TicketGenerator(ArrayList<Flight> flights) {
        this.flights = flights;
        random = new Random();
    }

    public ArrayList<Ticket> getTicketsRandomly(int amount) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        Category category;
        Flight flight;
        Ticket ticket;
        int userId = 12345678;
        int indexFlight;

        for (int i = 0; i < amount; i++) {
            indexFlight = random.nextInt(flights.size());
            flight = flights.get(indexFlight);
            category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            ticket = new Ticket(
                    UUID.randomUUID().toString(), flight.getNextNumber(),
                    category, userId, flight.getId()
            );
            tickets.add(ticket);
            flights.get(indexFlight).addTicket(ticket);
        }

        return tickets;
    }


}