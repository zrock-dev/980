package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.repository.requesters.JsonRequester;
import com.fake_orgasm.flights_management.repository.requesters.SourceJson;
import com.fake_orgasm.flights_management.services.IFlightManagement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;

public class FlightRepository implements IFlightManagement {

    private JsonRequester requester;

    public FlightRepository() {
        requester = new JsonRequester(SourceJson.FLIGHTS.getSource());
    }

    private JSONObject toJsonObject(Flight flight) {
        JSONObject jsonTicket = new JSONObject();
        jsonTicket.put("id", flight.getId());
        jsonTicket.put("sourceId", flight.getSourceId());
        jsonTicket.put("destinationId", flight.getDestinationId());
        jsonTicket.put("date", flight.getDate());
        jsonTicket.put("capacity", flight.getCapacity());
        jsonTicket.put("tickets", flight.getTickets());
        return jsonTicket;
    }

    @Override
    public boolean create(Flight flight) {
        boolean wasSaved = false;
        try {
            JSONArray currentDocuments = requester.getDocuments();
            currentDocuments.put(toJsonObject(flight));
            FileWriter writer = requester.getFileWriter();
            writer.write(currentDocuments.toString(4));
            writer.flush();
            wasSaved = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return wasSaved;
    }

    @Override
    public boolean update(String id, Flight flight) {
        return false;
    }

    @Override
    public Flight search(String id) {
        return null;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }
}