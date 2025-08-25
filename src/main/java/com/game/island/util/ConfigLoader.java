package com.game.island.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.game.island.config.AnimalConfig;
import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static AnimalConfig load(String path) {
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + path);
            }
            return mapper.readValue(inputStream, AnimalConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Config loading error: " + path, e);
        }
    }
}
