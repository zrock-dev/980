package com.fake_orgasm.generator.flight_history_generator;

public class Main {
    public static void main(String[] args) {
        AirportLoader airportLoader = AirportLoader.getInstance();
        airportLoader.printAirports();
    }
}
