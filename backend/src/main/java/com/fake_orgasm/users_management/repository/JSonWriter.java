package com.fake_orgasm.users_management.repository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;

public class JSonWriter {
    private JsonFactory jsonFactory;
    private StringWriter stringWriter;
    private JsonGenerator jsonGenerator;

    public JSonWriter(){
        jsonFactory = new JsonFactory();
        stringWriter = new StringWriter();
        try {
            jsonGenerator = jsonFactory.createGenerator(stringWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void
}
