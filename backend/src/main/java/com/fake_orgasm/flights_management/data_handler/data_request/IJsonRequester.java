package com.fake_orgasm.flights_management.data_handler.data_request;

import com.fasterxml.jackson.databind.JsonNode;

public interface IJsonRequester {

    /**
     * This method returns the methods that were obtained from the JSON file.
     *
     * @return documents obtained.
     */
    JsonNode getDocuments();
}