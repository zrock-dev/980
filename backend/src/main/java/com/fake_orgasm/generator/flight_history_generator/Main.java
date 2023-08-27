package com.fake_orgasm.generator.flight_history_generator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numHistoriesToGenerate = 1000000;
        FlightHistoryGenerator historyGenerator = FlightHistoryGenerator.getInstance();
        long startTime = System.currentTimeMillis();
        List<FlightHistory> flightHistories = historyGenerator.generateFlightHistories(numHistoriesToGenerate);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        long startTime2 = System.currentTimeMillis();
        int x = 0;
        for (FlightHistory flightHistory : flightHistories) {
            x++;
            System.out.println("Departure Airport: " + flightHistory.getDepartureAirport().getAirportName());
            System.out.println("Destination Airport: " + flightHistory.getDestinationAirport().getAirportName());
            System.out.println("Ticket Type: " + flightHistory.getTicketType());
            System.out.println(x+ " ------------------------------- " + x);
        }
        System.out.println("Generated " + flightHistories.size() + " flight histories.");
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;

        System.out.println("Time taken: " + elapsedTime + " milliseconds");
        System.out.println("Time taken: " + elapsedTime2 + " milliseconds22");
    }
}
