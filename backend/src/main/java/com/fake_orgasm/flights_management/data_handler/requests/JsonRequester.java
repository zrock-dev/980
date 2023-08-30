package com.fake_orgasm.flights_management.data_handler.requests;

import org.json.JSONArray;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This class is responsible for getting the documents from a JSON file.
 */
public class JsonRequester implements IJsonRequester {

    private JSONArray documents;
    private final String jsonPath;

    /**
     * This is the constructor method.
     *
     * @param jsonPath is the JSON path as a reference to get documents.
     */
    public JsonRequester(String jsonPath) {
        this.jsonPath = jsonPath;
        this.documents = new JSONArray();
    }

    /**
     * This method returns the methods that were obtained from the JSON file.
     *
     * @return documents obtained.
     */
    @Override
    public JSONArray getDocuments() {
        if (documents.isEmpty())
            generateJSON();

        return documents;
    }

    /**
     * This method gets the documents from the JSON file.
     */
    private void generateJSON() {
        documents = new JSONArray(getFilePath());
    }

    /**
     * This method gets the address of the JSON file.
     *
     * @return path JSON file.
     */
    private String getFilePath() {
        try {
            File filePath = new File(jsonPath);
            return new String(Files.readAllBytes(filePath.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}