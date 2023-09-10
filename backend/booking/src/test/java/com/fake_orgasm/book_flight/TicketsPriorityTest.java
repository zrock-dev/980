package com.fake_orgasm.book_flight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketsPriorityTest {

    private Flight flight;
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;
    private Ticket ticket4;
    private Ticket ticket5;
    private Ticket ticket6;

    @BeforeEach
    public void setup() throws FlightCapacityException {
        User user1 = new User(12341, "name1", "lastname1", LocalDate.now(), "Bolivia");
        User user2 = new User(12342, "name2", "lastname1", LocalDate.now(), "Bolivia");
        User user3 = new User(12343, "name3", "lastname1", LocalDate.now(), "Bolivia");
        User user4 = new User(12344, "name4", "lastname1", LocalDate.now(), "Bolivia");
        User user5 = new User(12345, "name5", "lastname1", LocalDate.now(), "Bolivia");
        User user6 = new User(12346, "name6", "lastname1", LocalDate.now(), "Bolivia");

        Airport airport1 = new Airport(UUID.randomUUID().toString(), "source1", "Bolivia", "Cochabamba");
        Airport airport2 = new Airport(UUID.randomUUID().toString(), "destination", "Argentina", "Buenos Aries");

        flight = new Flight(UUID.randomUUID().toString(), airport1.getId(), airport2.getId(), 100, 200);

        ticket1 =
                new Ticket(UUID.randomUUID().toString(), 1, Category.FREQUENT_PASSENGER, user1.getId(), flight.getId());
        ticket2 =
                new Ticket(UUID.randomUUID().toString(), 2, Category.REGULAR_PASSENGER, user2.getId(), flight.getId());
        ticket3 = new Ticket(UUID.randomUUID().toString(), 3, Category.VIP, user3.getId(), flight.getId());
        ticket4 =
                new Ticket(UUID.randomUUID().toString(), 4, Category.FREQUENT_PASSENGER, user4.getId(), flight.getId());
        ticket5 = new Ticket(UUID.randomUUID().toString(), 5, Category.VIP, user5.getId(), flight.getId());
        ticket6 = new Ticket(UUID.randomUUID().toString(), 6, Category.VIP, user6.getId(), flight.getId());

        flight.addTicket(List.of(ticket1, ticket2, ticket3, ticket4, ticket5, ticket6));
    }

    @Test
    public void orderPriorityTest() {
        Ticket nextTicket;

        nextTicket = flight.getNextTicket();
        assertEquals(nextTicket, ticket3);
        nextTicket = flight.getNextTicket();
        assertEquals(nextTicket, ticket5);
        nextTicket = flight.getNextTicket();
        assertEquals(nextTicket, ticket6);
        nextTicket = flight.getNextTicket();
        assertEquals(nextTicket, ticket1);
        nextTicket = flight.getNextTicket();
        assertEquals(nextTicket, ticket4);
        nextTicket = flight.getNextTicket();
        assertEquals(nextTicket, ticket2);
    }
}
