package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.Getter;

/**
 * This class is responsible for generating random tickets for users on flights.
 */
public class TicketGenerator {

    private Random random;

    @Getter
    private ArrayList<Flight> flights;

    @Getter
    private List<User> users;

    @Getter
    private ArrayList<Ticket> tickets;

    private final Category[] categories = {Category.VIP, Category.FREQUENT_PASSENGER, Category.REGULAR_PASSENGER};

    /**
     * This method constructs a TicketGenerator instance with a list of users and flights.
     *
     * @param users   The list of users for whom tickets will be generated.
     * @param flights The list of flights for which tickets will be generated.
     */
    public TicketGenerator(List<User> users, ArrayList<Flight> flights) {
        this.users = users;
        this.flights = flights;
        this.tickets = new ArrayList<>();
        random = new Random();
    }

    /**
     * This method generates a list of random tickets with the specified amount.
     *
     * @param amount The number of tickets to generate.
     * @return An ArrayList of randomly generated tickets.
     */
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
            category = categories[random.nextInt(categories.length)];
            ticket = new Ticket(
                    UUID.randomUUID().toString(), flight.getNextNumber(), category, user.getId(), flight.getId());
            tickets.add(ticket);
            flights.get(indexFlight).addTicket(ticket);
        }

        return tickets;
    }

    /**
     * This method generates a specified amount of tickets for a specific user.
     *
     * @param userIndex The index of the user for whom tickets will be generated.
     * @param amount    The number of tickets to generate for the user.
     */
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

    /**
     * This method generate a ticket for a specific user and flight.
     *
     * @param user   the user who owns the ticket.
     * @param flight the flight that will contain the ticket.
     * @return the new ticket generated.
     */
    private Ticket getTicketRandomly(User user, Flight flight) {
        Category category = categories[random.nextInt(categories.length)];
        return new Ticket(UUID.randomUUID().toString(), flight.getNextNumber(), category, user.getId(), flight.getId());
    }
}
