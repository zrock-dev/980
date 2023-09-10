package com.fake_orgasm.users_management.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexerRepository {
    private static final String PATH_INDEXER_DATA_BASE = "users/indexer";

    /**
     * Constructor of the class.
     */
    public IndexerRepository() {
        File dir = new File(PATH_INDEXER_DATA_BASE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    /**
     * Save a node in the secondary memory.
     *
     * @param key  key to save;
     * @param idNode idNode to save.
     */
    public void save(String key, String idNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String pathFile = String.format("%s/%s.json", PATH_INDEXER_DATA_BASE, key);
        File jsonFile = new File(pathFile);
        Map<String, Set<String>> existingData = null;
        if (jsonFile.exists()) {
            try {
                existingData = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Set<String>>>() {});
                existingData.get(key).add(idNode);
                String jsonText = objectMapper.writeValueAsString(existingData);
                writeJson(jsonText, pathFile, objectMapper);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            existingData = Map.of(key, Set.of(idNode));
            try {
                String jsonText = objectMapper.writeValueAsString(existingData);
                writeJson(jsonText, pathFile, objectMapper);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Write a json in a file.
     *
     * @param jsonText String with the json.
     * @param pathFile path of the file.
     * @param objectMapper ObjectMapper to write the json.
     * @throws IOException if the file does not exist.
     */
    private void writeJson(String jsonText, String pathFile, ObjectMapper objectMapper) throws IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        FileWriter fileWriter = new FileWriter(pathFile);
        fileWriter.write(jsonText);
        fileWriter.close();
    }

    /**
     * Get a map from the secondary memory.
     *
     * @param key key to get the map.
     * @return map found.
     */
    public Map<String, Set<String>> getMap(String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        String pathFile = String.format("%s/%s.json", PATH_INDEXER_DATA_BASE, key);
        File jsonFile = new File(pathFile);
        Map<String, Set<String>> existingData = null;
        if (jsonFile.exists()) {
            try {
                existingData = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Set<String>>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return existingData;
    }

    /**
     * Get a map from the secondary memory.
     *
     * @return map found.
     */
    public Map<String, Set<String>> getMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        File[] jsonFiles = getJsonFiles();
        Map<String, Set<String>> map = new HashMap<>();
        for (File jsonFile : jsonFiles) {
            Map<String, Set<String>> existingData = null;
            try {
                existingData = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Set<String>>>() {});
                map.putAll(existingData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    /**
     * Get the json files from the secondary memory.
     *
     * @return json files found.
     */
    private File[] getJsonFiles() {
        File dir = new File(PATH_INDEXER_DATA_BASE);
        File[] jsonFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String nameFile) {
                return nameFile.toLowerCase().endsWith(".json");
            }
        });
        return jsonFiles;
    }
}
