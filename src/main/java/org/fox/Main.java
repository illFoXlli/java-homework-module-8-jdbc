package org.fox;

import org.fox.dao.ClientDaoService;
import org.fox.dao.ClientDaoServiceImpl;
import org.fox.jdbc.DatabaseMigrationService;
import org.fox.service.ClientService;
import org.fox.service.ClientServiceImpl;

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

        DatabaseMigrationService.migrate(props);

        ClientDaoService dao = new ClientDaoServiceImpl();

        ClientService cs = new ClientServiceImpl(dao);



        System.out.println("=== CREATE CLIENT ===");
        long id = cs.create("Fox");
        System.out.println("Created client id: " + id);

        System.out.println("\n=== GET BY ID ===");
        System.out.println(cs.getById(id));

        System.out.println("\n=== UPDATE ===");
        cs.setName(id, "FoxUpdated");
        System.out.println(cs.getById(id));

        System.out.println("\n=== ALL CLIENTS ===");
        cs.listAll().forEach(System.out::println);

        System.out.println("\n=== DELETE ===");
        cs.deleteById(id);
        cs.listAll().forEach(System.out::println);
    }
}