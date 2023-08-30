package com.fake_orgasm.flights_management.data_handler.requests;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class has the responsibility of representing a document or JSON object.
 */
public class DocumentRequester {

    private JSONArray documents;

    public DocumentRequester(JSONArray documents) {
        this.documents = documents;
    }

    /**
     * This method looks for a document in a documents.
     *
     * @param id is the id as a reference to find a document.
     * @return the document found.
     */
    public JSONObject getDocument(String id) {
        JSONObject document;
        for (int i = 0; i < documents.length(); i++) {
            document = documents.getJSONObject(i);
            if (id.equals(document.getString("id")))
                return document;
        }

        return null;
    }

}