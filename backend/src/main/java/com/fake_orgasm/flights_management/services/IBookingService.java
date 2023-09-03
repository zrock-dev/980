package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;

import java.util.List;

public interface IBookingService {


    public boolean booking(User user, String flightId, Category category);

    public List<Ticket> getFlightTickets(String flightId);

    public List<Ticket> getFlightTickets(Flight flight);
}