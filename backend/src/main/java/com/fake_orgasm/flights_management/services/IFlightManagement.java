package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Flight;

public interface IFlightManagement {

    public Flight search(String id);

    public boolean create(Flight flight);

    public boolean update(String id, Flight flight);

    public boolean remove(String id);

}