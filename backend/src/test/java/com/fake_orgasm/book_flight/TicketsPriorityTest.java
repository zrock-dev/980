package com.fake_orgasm.book_flight;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class TicketsPriorityTest {

    @Test
    public void orderPriorityTest() throws FlightCapacityException {
        Airport source = new Airport(UUID.randomUUID().toString(), "source1", "Bolivia", "Cochabamba");
        Airport destionation = new Airport(UUID.randomUUID().toString(), "destination", "Argentina", "Buenos Aries");

        User user1 = new User(12341, "name1", "lastname1", LocalDate.now(), "Bolivia");
        User user2 = new User(12342, "name2", "lastname1", LocalDate.now(), "Bolivia");
        User user3 = new User(12343, "name3", "lastname1", LocalDate.now(), "Bolivia");
        User user4 = new User(12344, "name4", "lastname1", LocalDate.now(), "Bolivia");
        User user5 = new User(12345, "name5", "lastname1", LocalDate.now(), "Bolivia");
        User user6 = new User(12346, "name6", "lastname1", LocalDate.now(), "Bolivia");

        Flight flight = new Flight(UUID.randomUUID().toString(), source.getId(), destionation.getId(), 100);

        Ticket ticket1 = new Ticket(UUID.randomUUID().toString(), 1, Category.FREQUENT_PASSENGER, user1.getId(), flight.getId());
        Ticket ticket2 = new Ticket(UUID.randomUUID().toString(), 2, Category.REGULAR_PASSENGER, user2.getId(), flight.getId());
        Ticket ticket3 = new Ticket(UUID.randomUUID().toString(), 3, Category.VIP, user3.getId(), flight.getId());
        Ticket ticket4 = new Ticket(UUID.randomUUID().toString(), 4, Category.FREQUENT_PASSENGER, user4.getId(), flight.getId());
        Ticket ticket5 = new Ticket(UUID.randomUUID().toString(), 5, Category.VIP, user5.getId(), flight.getId());
        Ticket ticket6 = new Ticket(UUID.randomUUID().toString(), 6, Category.VIP, user6.getId(), flight.getId());

        flight.addTicket(ticket1, ticket2, ticket3, ticket4, ticket5, ticket6);
        String ticketsString = flight.getPriorityTickets();
        String[] tickets = ticketsString.split(",");
        System.out.println(Arrays.toString(tickets));
    }

}