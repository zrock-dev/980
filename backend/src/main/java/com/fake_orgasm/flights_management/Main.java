package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.services.BookingService;

public class Main {

    public static void main(String[] args) {

        BookingService bookingService = new BookingService();
        bookingService.getFlightRepository().findAllFlightsJoined().forEach(System.out::println);
//        String flightId = "9c190aac-2ec2-4198-8896-725f63e0d67c";

    }

}