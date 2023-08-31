package com.fake_orgasm.flights_management.repository.requesters;

import org.json.JSONArray;

public interface IJsonRequester {

    /**
     * This method returns the methods that were obtained from the JSON file.
     *
     * @return documents obtained.
     */
    JSONArray getDocuments();
}