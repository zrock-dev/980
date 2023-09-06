package com.fake_orgasm.flights_management.models;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class TicketJoined {

    private String id;
    private int number;
    private Category priority;
    private Date date;
    private Airport source;
    private Airport destination;

    public TicketJoined(String id, int number, Category priority, Date date,
                        Airport source, Airport destination) {
        this.id = id;
        this.number = number;
        this.priority = priority;
        this.date = date;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "(id - " + id + ") - number: ";
    }
}