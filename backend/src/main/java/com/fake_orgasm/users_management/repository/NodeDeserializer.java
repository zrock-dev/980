package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.generator.flight_history_generator.Airport;
import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.Category;
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
     * @param mapper ObjectMapper, mapper for cast data.
     * @return User list saved;
     * @throws IOException Exception to input or output.
     */
    private List<User> deserializeUsers(JsonNode usersNode, ObjectMapper mapper) throws IOException {
        List<User> users = new ArrayList<>();
        DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;
        for (JsonNode userNode : usersNode) {
            User user = new User();
            user.setId(userNode.get("id").asInt());
            user.setCitizenId(userNode.get("citizenId").asInt());
            user.setFirstName(userNode.get("firstName").asText());
            user.setSecondName(userNode.get("secondName").asText());
            user.setFirstLastName(userNode.get("firstLastName").asText());
            user.setSecondLastName(userNode.get("secondLastName").asText());
            user.setDateBirth(LocalDate.parse(userNode.get("dateBirth").asText(), dateFormat));
            user.setCategory(mapper.readValue(userNode.get("category").traverse(), Category.class));
            user.setCountry(userNode.get("country").asText());
            List<FlightHistory> arrayFlights = deserializeFlightsHistory(userNode.get("flights"), mapper);
            user.setFlights(arrayFlights);
            users.add(user);
        }
        return users;
    }

    /**
     * Deserializes the flights history of the user represented in json.
     * Through a JsonNode that represents a list, the data is deserialized in FlightHistory
     * objects, the data is obtained and FlightHistory objects are created.
     *
     * @param flightsHistoryNode JsonNode, contents the flights history list.
     * @param mapper ObjectMapper, mapper for cast data.
     * @return FlightHistory list saved;
     * @throws IOException Exception to input or output.
     */
    private List<FlightHistory> deserializeFlightsHistory(JsonNode flightsHistoryNode, ObjectMapper mapper)
            throws IOException {
        List<FlightHistory> flights = new ArrayList<>();
        for (JsonNode cuurentHistory : flightsHistoryNode) {
            Airport departureAirport = deserializeAirport(cuurentHistory.get("departureAirport"));
            Airport destinationAirport = deserializeAirport(cuurentHistory.get("destinationAirport"));
            Category category =
                    mapper.readValue(cuurentHistory.get("ticketType").traverse(), Category.class);
            FlightHistory flightHistory = new FlightHistory(departureAirport, destinationAirport, category);
            flights.add(flightHistory);
        }
        return flights;
    }

    /**
     * Deserializes a json node into an Airport.
     *
     * @param airportNode JsonNode to deserializes.
     * @return Airport, object deserialized.
     * @throws IOException Exception to input or output.
     */
    private Airport deserializeAirport(JsonNode airportNode) throws IOException {
        String airportName = airportNode.get("airportName").asText();
        String country = airportNode.get("country").asText();
        String state = airportNode.get("state").asText();
        return new Airport(airportName, country, state);
    }

    /**
     * Deserializes a json node into an int[].
     *
     * @param idChildrenNode JsonNode to deserializes.
     * @return int[], array deserialized.
     */
    private int[] deserializeArrayInt(JsonNode idChildrenNode) {
        int[] array = new int[idChildrenNode.size()];
        for (int i = 0; i < idChildrenNode.size(); i++) {
            array[i] = idChildrenNode.get(i).asInt();
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
            array[i] = idChildrenNode.get(i).asText();
        }
        return array;
    }
}
