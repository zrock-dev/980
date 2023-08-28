package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BTreeRepository implements IBTreeRepository{
    private final String PATH_USERS_DATABASE = System.getProperty("user.home")+"/980/DataBase/Users";
    private JsonFactory jsonFactory;

    public BTreeRepository(){
        jsonFactory = new JsonFactory();

    }
    private void createRootDirectory(){
        String directoryProject = System.getProperty("user.home")+"/980";
        createDirectory(directoryProject);
        String directoryDataBase = System.getProperty("user.home")+"/980/DataBase";
        createDirectory(directoryDataBase);
        String directoryUsers =  System.getProperty("user.home")+"/980/DataBase/Users";
        createDirectory(directoryUsers);
    }

    private void createDirectory(String pathDirectory){
        Path path = Paths.get(pathDirectory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println(e.getMessage());
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
    public boolean saveNode(Node<User> node) {
        String nameFile = String.valueOf(node.getId());
        nameFile = PATH_USERS_DATABASE+"/"+nameFile;
        File file = new File(nameFile);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonGenerator jsonGenerator;
        try {
            jsonGenerator = jsonFactory.createGenerator(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", node.getId());
            jsonGenerator.writeNumberField("size", node.getSize());
            jsonGenerator.writeNumberField("order", node.getOrder());
            jsonGenerator.writeStartArray();
            for(User user : node.getKeys()){
                writeUser(user, jsonGenerator);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeStartArray();
            for(Integer currenId : node.getIdChildren()){
                jsonGenerator.writeNumber(currenId);
            }
            jsonGenerator.writeBooleanField("leaf", node.isLeaf());


        }catch (Exception e){
            e.getMessage();
        }

        return false;
    }

    private void writeUser(User user, JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("name", user.getName());
        generator.writeStringField("lastName", user.getLastName());
        generator.writeStringField("dateBirth", user.getDateBirth().toString());
        generator.writeStartArray();
        for(Integer currentFlightId : user.getFlights()){
            generator.writeNumber(currentFlightId);
        }
        generator.writeEndArray();
        generator.writeStringField("category", user.getCategory().name());
        generator.writeStringField("country", user.getCountry());
    }

    private void writeFlightsUser(){

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
        return null;
    }

}
