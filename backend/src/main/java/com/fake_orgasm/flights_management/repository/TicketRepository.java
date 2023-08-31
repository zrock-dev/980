package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.requesters.JsonRequester;
import com.fake_orgasm.flights_management.repository.requesters.SourceJson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;

public class TicketRepository {

    private JsonRequester requester;

    public TicketRepository() {
        requester = new JsonRequester(SourceJson.TICKETS.getSource());
    }

    private JSONObject toJsonObject(Ticket ticket) {
        JSONObject jsonTicket = new JSONObject();
        jsonTicket.put("id", ticket.getId());
        jsonTicket.put("number", ticket.getNumber());
        jsonTicket.put("priority", ticket.getPriority().getType());
        jsonTicket.put("userId", ticket.getUserId());
        jsonTicket.put("flightId", ticket.getFlightId());;
        return jsonTicket;
    }

    public boolean save(Ticket ticket) {
        boolean wasSaved = false;
        try {
            JSONArray currentDocuments = requester.getDocuments();
            currentDocuments.put(toJsonObject(ticket));
            FileWriter writer = requester.getFileWriter();
            writer.write(currentDocuments.toString(4));
            writer.flush();
            wasSaved = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return wasSaved;
    }

}