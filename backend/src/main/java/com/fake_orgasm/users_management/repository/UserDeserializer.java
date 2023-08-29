package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDeserializer extends JsonDeserializer<User> {

    /**
     * Method that can be called to ask implementation to deserialize
     * JSON content into the value type this serializer handles.
     * Returned instance is to be constructed by method itself.
     * <p>
     * Pre-condition for this method is that the parser points to the
     * first event that is part of value to deserializer (and which
     * is never JSON 'null' literal, more on this below): for simple
     * types it may be the only value; and for structured types the
     * Object start marker.
     * Post-condition is that the parser will point to the last
     * event that is part of deserialized value (or in case deserialization
     * fails, event that was not recognized or usable, which may be
     * the same event as the one it pointed to upon call).
     * <p>
     * Note that this method is never called for JSON null literal,
     * and thus deserializers need (and should) not check for it.
     *
     * @param jp   Parsed used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *             this deserialization activity.
     * @return Deserializer value
     */
    @Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = jp.getCodec().readTree(jp);

        int id = node.get("id").asInt();
        String name = node.get("name").asText();
        String lastName = node.get("lastName").asText();
        String dateBirthString = node.get("dateBirth").asText();
        Category category = mapper.readValue(node.get("category").traverse(), Category.class);
        String country = node.get("country").asText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date dateBirth;
        try {
            dateBirth = dateFormat.parse(dateBirthString);
        } catch (ParseException e) {
            throw new IOException("Error parsing dateBirth", e);
        }

        return new User(id, name, lastName, dateBirth, category, country);
    }
}
