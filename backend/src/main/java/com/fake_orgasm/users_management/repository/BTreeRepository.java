package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BTreeRepository implements IBTreeRepository{
    private final String PATH_USERS_DATABASE = System.getProperty("user.home")+"/980/DataBase/Users";
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
    public boolean saveNode(Node node) {
        return false;
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
