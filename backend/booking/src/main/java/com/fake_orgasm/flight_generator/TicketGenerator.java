package com.fake_orgasm.flight_generator;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;
import java.util.*;
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

    private HashMap<String, Ticket> tickets;

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
        this.tickets = new HashMap<>();
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
        Ticket lastTicket;
        int flightIndex;

        User user = users.get(userIndex);
        for (int i = 0; i < amount; i++) {
            flightIndex = random.nextInt(flights.size());
            flight = flights.get(flightIndex);
            ticket = getTicketRandomly(user, flight);

            if (flight.numberOfTickets() > 0) {
                lastTicket = tickets.get(flight.getLastTicket());
                ticket.setPreviousTicket(lastTicket.getId());
                tickets.get(flight.getLastTicket()).setNextTicket(ticket.getId());
            }

            tickets.put(ticket.getId(), ticket);
            users.get(userIndex).addFlight(ticket.getId());
            flights.get(flightIndex).addTicketId(ticket.getId());
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

    /**
     * This method returns a list of the tickets that were generated randomly.
     *
     * @return list of the tickets.
     */
    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets.values());
    }
}
