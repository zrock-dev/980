package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.data_handler.requests.DocumentRequester;
import com.fake_orgasm.flights_management.data_handler.requests.JsonRequester;
import com.fake_orgasm.flights_management.data_handler.requests.SourceJson;
import com.fake_orgasm.flights_management.data_handler.schemas.AirportSchema;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        JsonRequester jsonRequester = new JsonRequester(SourceJson.AIRPORTS.getSource());
        DocumentRequester documentRequester = new DocumentRequester(jsonRequester.getDocuments());

        JSONObject airport = documentRequester.getDocument("4d8eb5f2-1fe0-4f6d-8080-5bf14445e9b7");
        System.out.println(airport.get(AirportSchema.NAME.getField()));

    }

}