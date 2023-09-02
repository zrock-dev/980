package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TicketGenerator {

    private Random random;
    @Getter
    private ArrayList<Flight> flights;
    @Getter
    private List<User> users;
    @Getter
    private ArrayList<Ticket> tickets;

    private final Category[] CATEGORIES = {Category.VIP,
            Category.FREQUENT_PASSENGER, Category.REGULAR_PASSENGER};

    public TicketGenerator(List<User> users, ArrayList<Flight> flights) {
        this.users = users;
        this.flights = flights;
        this.tickets = new ArrayList<>();
        random = new Random();
    }

    public ArrayList<Ticket> getTicketsRandomly(int amount) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        Category category;
        Flight flight;
        Ticket ticket;
        User user;
        int indexFlight;

        for (int i = 0; i < amount; i++) {
            user = users.get(random.nextInt(users.size()));
            indexFlight = random.nextInt(flights.size());
            flight = flights.get(indexFlight);
            category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            ticket = new Ticket(
                    UUID.randomUUID().toString(), flight.getNextNumber(),
                    category, user.getId(), flight.getId()
            );
            tickets.add(ticket);
            flights.get(indexFlight).addTicket(ticket);
        }

        return tickets;
    }

    public void generateTickets(int userIndex, int amount) {
        Flight flight;
        Ticket ticket;
        int flightIndex;

        User user = users.get(userIndex);
        for (int i = 0; i < amount; i++) {
            flightIndex = random.nextInt(flights.size());
            flight = flights.get(flightIndex);
            ticket = getTicketRandomly(user, flight);
            tickets.add(ticket);
            users.get(userIndex).addFlight(ticket.getId());
            flights.get(flightIndex).addTicket(ticket);
        }
    }

    private Ticket getTicketRandomly(User user, Flight flight) {
        Category category = CATEGORIES[random.nextInt(CATEGORIES.length)];
        return new Ticket(
                UUID.randomUUID().toString(), flight.getNextNumber(),
                category, user.getId(), flight.getId()
        );
    }
}