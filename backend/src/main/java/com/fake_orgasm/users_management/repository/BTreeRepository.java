package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.generator.flight_history_generator.Airport;
import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * This class writes and reads the nodes in json form.
 * It implements the IBTreeRepository interface that allows to save the
 * nodes in json form and read them.
 */
public class BTreeRepository implements IBTreeRepository<User> {
    /**
     * Path where the nodes are saved.
     */
    private final String pathUserDataBase = "/home/fundacion/Documents/ProyectoProgra4/980/backend/src/main/resources/Database/Users";

    private JsonFactory jsonFactory;

    /**
     * Constructor of the class.
     */
    public BTreeRepository() {
        jsonFactory = new JsonFactory();
    }

    /**
     * Save a node in the secondary memory.
     *
     * @param node Node to save;
     * @return result of the operation.
     */
    @Override
    public boolean save(Node<User> node) {
        boolean resultOperation = true;
        String nameFile = String.valueOf(node.getId());
        nameFile = pathUserDataBase + "/" + nameFile + ".json";
        FileOutputStream fileOutputStream;
        JsonGenerator jsonGenerator;
        try {
            fileOutputStream = new FileOutputStream(new File(nameFile));
            jsonGenerator = jsonFactory.createGenerator(fileOutputStream, JsonEncoding.UTF8);
            jsonGenerator.useDefaultPrettyPrinter();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", node.getId());
            jsonGenerator.writeNumberField("size", node.getSize());
            jsonGenerator.writeNumberField("order", node.getOrder());
            jsonGenerator.writeFieldName("keys");
            jsonGenerator.writeStartArray();
            for (int i = 0; i < node.getSize(); i++) {
                writeUser(node.getKey(i), jsonGenerator);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeFieldName("idChildren");
            jsonGenerator.writeStartArray();
            for (String currenId : node.getIdChildren()) {
                jsonGenerator.writeString(currenId);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeBooleanField("leaf", node.isLeaf());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            resultOperation = false;
            e.printStackTrace();
        }
        return resultOperation;
    }

    /**
     * This class writes a target of type user to a json.
     * Write a user in the json generator that is passed as a parameter,
     * use the streaming approach by writing line by line the json.
     *
     * @param user      User to write in a json.
     * @param generator JsonGenerator, json constructor.
     * @throws IOException exception if the deed fails.
     */
    private void writeUser(User user, JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", user.getId());
        generator.writeNumberField("citizenId", user.getCitizenId());
        generator.writeStringField("firstName", user.getFirstName());
        generator.writeStringField("secondName", user.getSecondName());
        generator.writeStringField("firstLastName", user.getFirstLastName());
        generator.writeStringField("secondLastName", user.getSecondLastName());
        String formattedDate = user.getDateBirth().format(DateTimeFormatter.ISO_DATE);
        generator.writeStringField("dateBirth", formattedDate);

        generator.writeFieldName("flights");
        generator.writeStartArray();
        for (FlightHistory currentFlightId : user.getFlights()) {
            writeFlightHistory(currentFlightId, generator);
        }
        generator.writeEndArray();
        generator.writeStringField("category", user.getCategory().name());
        generator.writeStringField("country", user.getCountry());
        generator.writeEndObject();
    }

    private void writeFlightHistory(FlightHistory flightHistory, JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("departureAirport");
        writeAirport(flightHistory.getDepartureAirport(), generator);
        generator.writeFieldName("destinationAirport");
        writeAirport(flightHistory.getDestinationAirport(), generator);
        generator.writeStringField("ticketType", flightHistory.getTicketType().name());
        generator.writeEndObject();
    }

    private void writeAirport(Airport airport, JsonGenerator generator) throws IOException {

        generator.writeStartObject();
        generator.writeStringField("airportName", airport.getAirportName());
        generator.writeStringField("country", airport.getCountry());
        generator.writeStringField("state", airport.getState());
        generator.writeEndObject();
    }

    /**
     * Delete a node in the secondary memory.
     *
     * @param node Node to delete.
     * @return result of operation.
     */
    @Override
    public boolean delete(Node<User> node) {
        if (node == null) {
            return false;
        }
        String nameFile = pathUserDataBase + "/" + node.getId() + ".json";
        boolean resultOperation = false;
        File file = new File(nameFile);
        if (file.exists()) {
            file.delete();
            resultOperation = true;
        }
        return resultOperation;
    }

    /**
     * Get a node by id of the secondary memory.
     *
     * @param id id node to remove.
     * @return Node found.
     */
    @Override
    public Node<User> readNodeById(String id) {
        String pathFile = pathUserDataBase + "/" + id + ".json";
        File file = new File(pathFile);
        Node<User> userNode = null;
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                userNode = objectMapper.readValue(file, Node.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userNode;
    }
}