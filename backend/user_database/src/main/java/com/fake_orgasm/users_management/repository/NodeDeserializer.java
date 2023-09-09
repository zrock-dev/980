package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a custom Deserialization.
 * It indicates how the correct deserialization of node<User> class should be.
 */
public class NodeDeserializer extends JsonDeserializer<Node<User>> {
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
    public Node<User> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = jp.getCodec().readTree(jp);
        String id = node.get("id").asText();
        int size = node.get("size").asInt();
        int order = node.get("order").asInt();
        boolean leaf = node.get("leaf").asBoolean();
        List<User> users = deserializeUsers(node.get("keys"), mapper);
        String[] idChildren = deserializeArrayString(node.get("idChildren"));
        Node<User> userNode = new Node<>(order);
        userNode.setId(id);
        userNode.setSize(size);
        userNode.setLeaf(leaf);
        for (int i = 0; i < users.size(); i++) {
            userNode.setKey(i, users.get(i));
        }
        userNode.setIdChildren(idChildren);
        return userNode;
    }

    /**
     * Deserializes the users of the node represented in json.
     * Through a JsonNode that represents a list, the data is deserialized in User
     * objects, the data is obtained and User objects are created.
     *
     * @param usersNode JsonNode, contents the user list.
     * @param mapper    ObjectMapper, mapper for cast data.
     * @return User list saved;
     * @throws IOException Exception to input or output.
     */
    private List<User> deserializeUsers(JsonNode usersNode, ObjectMapper mapper) throws IOException {
        List<User> users = new ArrayList<>();
        DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;
        for (JsonNode userNode : usersNode) {
            User user = new User();
            user.setId(userNode.get("id").asInt());
            user.setFirstName(userNode.get("firstName").asText());
            user.setSecondName(userNode.get("secondName").asText());
            user.setFirstLastName(userNode.get("firstLastName").asText());
            user.setSecondLastName(userNode.get("secondLastName").asText());
            user.setDateBirth(LocalDate.parse(userNode.get("dateBirth").asText(), dateFormat));
            user.setCountry(userNode.get("country").asText());

            List<String> flights = new ArrayList<>();
            String[] arrayFlights = deserializeArrayInt(userNode.get("flights"));
            for (String currentFlight : arrayFlights) {
                flights.add(currentFlight);
            }
            user.setFlights(flights);
            users.add(user);
        }
        return users;
    }




    /**
     * Deserializes a json node into an int[].
     *
     * @param idChildrenNode JsonNode to deserializes.
     * @return int[], array deserialized.
     */
    private String[] deserializeArrayInt(JsonNode idChildrenNode) {
        String[] array = new String[idChildrenNode.size()];
        for (int i = 0; i < idChildrenNode.size(); i++) {
            array[i] = idChildrenNode.get(i).asText();
        }
        return array;
    }

    /**
     * Deserializes a json node into a String[].
     *
     * @param idChildrenNode JsonNode to deserializes.
     * @return String[], array deserialized.
     */
    private String[] deserializeArrayString(JsonNode idChildrenNode) {
        String[] array = new String[idChildrenNode.size()];
        for (int i = 0; i < idChildrenNode.size(); i++) {
            if (idChildrenNode.get(i).isTextual()) {
                array[i] = idChildrenNode.get(i).asText();
            } else {
                array[i] = null;
            }
        }
        return array;
    }
}
