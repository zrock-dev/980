package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.services.BookingService;

public class MainService {

    public static void main(String[] args) {

        BookingService bookingService = new BookingService();
//        bookingService.getFlightRepository().findAllFlightsJoined().forEach(System.out::println);
        String flightId = "3e86b995-e361-4a27-87d1-3670df9415f1";
        System.out.println(bookingService.getFlightJoined(flightId));
    }

}