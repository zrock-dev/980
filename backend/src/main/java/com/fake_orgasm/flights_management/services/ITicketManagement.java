package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Ticket;

public interface ITicketManagement {

    public Ticket search(String id);

    public boolean create(Ticket ticket);

    public boolean update(String id, Ticket ticket);

    public boolean remove(String id);

}