package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.TicketRepository;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        TicketRepository ticketRepository = new TicketRepository();
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), 3, Category.REGULAR_PASSENGER, "nose2", "nose1");
        boolean wasSaved = ticketRepository.save(ticket);
        System.out.println(wasSaved ? "success" : "failed");

    }

}