package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.repository.requesters.JsonRequester;
import com.fake_orgasm.flights_management.repository.requesters.SourceJson;

public class AirportRepository {

    private JsonRequester requester;

    public AirportRepository() {
        requester = new JsonRequester(SourceJson.AIRPORTS.getSource());
    }

}