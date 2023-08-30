package com.fake_orgasm.flights_management.data_handler.data_request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonRequester implements IJsonRequester {

    private JsonNode documents;
    private String jsonPath;
    private ObjectMapper objectMapper;

    public JsonRequester(String jsonPath) {
        this.jsonPath = jsonPath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public JsonNode getDocuments() {
        if (documents == null) {
            generateJSON();
        }

        return documents;
    }

    private void generateJSON() {
        try {
            documents = objectMapper.readTree(new File(getFilePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilePath() {
        try {
            File filePath = new File(jsonPath);
            return filePath.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}