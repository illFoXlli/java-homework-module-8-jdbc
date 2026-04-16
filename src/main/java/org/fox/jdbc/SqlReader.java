package org.fox.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SqlReader {

    private SqlReader() {
    }

    public static String read(String fileName) {
        String resourceName = "sql/" + fileName;

        try (InputStream input = SqlReader.class
                .getClassLoader()
                .getResourceAsStream(resourceName)) {

            if (input == null) {
                throw new IllegalArgumentException("SQL file not found: " + resourceName);
            }

            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read SQL file: " + resourceName, e);
        }
    }
}
