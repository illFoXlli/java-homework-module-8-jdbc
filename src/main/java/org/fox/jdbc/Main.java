package org.fox.jdbc;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();

        try (InputStream is = Main.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (is == null) {
                throw new IllegalStateException("application.properties not found");
            }

            props.load(is);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Flyway flyway = Flyway.configure()
                .dataSource(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password")
                )
                .load();

        flyway.migrate();


        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println("=== CLIENT BY ID ===");
        System.out.println(service.findClientById(1));

        System.out.println("\n=== CLIENTS BY NAME ===");
        service.findClientsByName("John").forEach(System.out::println);

        System.out.println("\n=== PROJECTS BY CLIENT ID ===");
        service.findProjectsByClientId(1).forEach(System.out::println);
    }
}


