package com.fake_orgasm.flights_management.data_handler.data_request;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;

public class DocumentRequester extends JsonRequester {

    private Map<String, JsonNode> documentsMap;

    public DocumentRequester(String jsonPath) {
        super(jsonPath);
        this.documentsMap = new HashMap<>();
        loadDocuments();
    }

    private void loadDocuments() {
        try {
            JsonNode documents = getDocuments();
            for (JsonNode document : documents) {
                String id = document.get("id").asText();
                documentsMap.put(id, document);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getDocument(String id) {
        return documentsMap.get(id);
    }
}