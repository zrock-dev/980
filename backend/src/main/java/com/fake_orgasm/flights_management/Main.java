package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.data_handler.data_request.DocumentRequester;
import com.fake_orgasm.flights_management.data_handler.data_request.SourceJson;
import com.fake_orgasm.flights_management.data_handler.schemas.AirportSchema;
import com.fasterxml.jackson.databind.JsonNode;

public class Main {

    public static void main(String[] args) {

        /*JsonRequester jsonRequester = new JsonRequester(SourceJson.AIRPORTS.getSource());
        DocumentRequester documentRequester = new DocumentRequester(jsonRequester.getDocuments());

        JSONObject airport = documentRequester.getDocument("4d8eb5f2-1fe0-4f6d-8080-5bf14445e9b7");
        System.out.println(airport.get(AirportSchema.NAME.getField()));*/

        DocumentRequester airports = new DocumentRequester(SourceJson.AIRPORTS.getSource());
        JsonNode airport = airports.getDocument("4d8eb5f2-1fe0-4f6d-8080-5bf14445e9b7");
        System.out.println(airport.get(AirportSchema.ID.getField()));
        System.out.println(airport.get(AirportSchema.COUNTRY.getField()));
        System.out.println(airport.get(AirportSchema.COUNTRY.getField()));

    }

}