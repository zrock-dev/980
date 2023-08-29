package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BTreeRepository implements IBTreeRepository<User>{
    private final String PATH_USERS_DATABASE = "../backend/src/main/resources/DataBase/Users";
    private JsonFactory jsonFactory;

    public BTreeRepository(){
        jsonFactory = new JsonFactory();

    }
    /**
     * Save a node in the secondary memory.
     *
     * @param node Node to save;
     * @return result of the operation.
     */
    @Override
    public boolean saveNode(Node<User> node) {
        String nameFile = String.valueOf(node.getId());
        nameFile = PATH_USERS_DATABASE+"/"+nameFile+".json";
        File file = new File(nameFile);
        FileOutputStream fileOutputStream;
        JsonGenerator jsonGenerator;
        try {
            fileOutputStream = new FileOutputStream(file);
            jsonGenerator = jsonFactory.createGenerator(fileOutputStream);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", node.getId());
            jsonGenerator.writeNumberField("size", node.getSize());
            jsonGenerator.writeNumberField("order", node.getOrder());
            jsonGenerator.writeFieldName("keys");
            jsonGenerator.writeStartArray();
            for(int i = 0; i < node.getSize(); i++){
                writeUser(node.getKey(i), jsonGenerator);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeFieldName("idChildren");
            jsonGenerator.writeStartArray();
            for(Integer currenId : node.getIdChildren()){
                jsonGenerator.writeNumber(currenId);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeBooleanField("leaf", node.isLeaf());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeUser(User user, JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("name", user.getName());
        generator.writeStringField("lastName", user.getLastName());
        generator.writeStringField("dateBirth", user.getDateBirth().toString());
        generator.writeFieldName("flights");
        generator.writeStartArray();
        for(Integer currentFlightId : user.getFlights()){
            generator.writeNumber(currentFlightId);
        }
        generator.writeEndArray();
        generator.writeStringField("category", user.getCategory().name());
        generator.writeStringField("country", user.getCountry());
        generator.writeEndObject();
    }

    /**
     * Update the data of a node in the secondary memory.
     *
     * @param node Node with the new information.
     * @return result of the operation.
     */
    @Override
    public boolean updateNode(Node node) {
        return false;
    }

    /**
     * Delete a node in the secondary memory.
     *
     * @param node Node to delete.
     * @return result of operation.
     */
    @Override
    public boolean deleteNode(Node node) {
        return false;
    }

    /**
     * Get a node by id of the secondary memory.
     *
     * @param id id node to remove.
     * @return Node found.
     */
    @Override
    public Node readNodeById(int id) {
        String pathFile = PATH_USERS_DATABASE+"/"+id+".json";
        File file = new File(pathFile);
        if(file.exists()){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Node<User> dataObject = objectMapper.readValue(file, Node.class);
                System.out.println(dataObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
