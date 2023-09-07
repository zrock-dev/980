package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public static final String PATH_USER_DATA_BASE = "../backend/user_database";

    private JsonFactory jsonFactory;

    /**
     * Constructor of the class.
     */
    public BTreeRepository() {
        jsonFactory = new JsonFactory();
        createUserDirectory();
    }

    /**
     * This method create a directory user if it does not exist.
     */
    private void createUserDirectory() {
        File pathUser = new File(PATH_USER_DATA_BASE);
        if (!pathUser.exists()) {
            Path directory = Path.of(PATH_USER_DATA_BASE);
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
        nameFile = String.format("%s/%s.json", PATH_USER_DATA_BASE, nameFile);
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
                jsonGenerator.writeNumber(currenId);
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
        generator.writeStringField("firstName", user.getFirstName());
        generator.writeStringField("secondName", user.getSecondName());
        generator.writeStringField("firstLastName", user.getFirstLastName());
        generator.writeStringField("secondLastName", user.getSecondLastName());
        String formattedDate = user.getDateBirth().format(DateTimeFormatter.ISO_DATE);
        generator.writeStringField("dateBirth", formattedDate);

        generator.writeFieldName("flights");
        generator.writeStartArray();
        for (String currentFlightId : user.getFlights()) {
            generator.writeString(currentFlightId);
        }
        generator.writeEndArray();
        generator.writeStringField("country", user.getCountry());
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
        String nameFile = String.format("%s/%s.json", PATH_USER_DATA_BASE, node.getId());
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
        String pathFile = String.format("%s/%s.json", PATH_USER_DATA_BASE, id);
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
