package org.fox.service;

import org.fox.dao.ClientDaoServiceImpl;
import org.fox.dto.Client;
import org.fox.jdbc.DatabaseMigrationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientServiceImplTest {

    private final ClientService service = new ClientServiceImpl(new ClientDaoServiceImpl());

    @BeforeAll
    static void migrateDatabase() {
        System.setProperty("app.config", "application-test.properties");

        Properties props = new Properties();

        try (InputStream input = ClientServiceImplTest.class
                .getClassLoader()
                .getResourceAsStream("application-test.properties")) {

            if (input == null) {
                throw new IllegalStateException("application-test.properties not found");
            }

            props.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        DatabaseMigrationService.migrate(props);
    }

    @Test
    void createAndGetByIdReturnsName() {
        long id = service.create("Test Client");

        try {
            String name = service.getById(id);

            assertEquals("Test Client", name);
        } finally {
            service.deleteById(id);
        }
    }

    @Test
    void setNameUpdatesExistingClient() {
        long id = service.create("Old Name");

        try {
            service.setName(id, "New Name");

            assertEquals("New Name", service.getById(id));
        } finally {
            service.deleteById(id);
        }
    }

    @Test
    void deleteByIdRemovesClient() {
        long id = service.create("Delete Me");

        service.deleteById(id);

        assertThrows(RuntimeException.class, () -> service.getById(id));
    }

    @Test
    void listAllContainsCreatedClient() {
        long id = service.create("List Me");

        try {
            List<Client> clients = service.listAll();

            assertTrue(clients.stream().anyMatch(client -> client.getId() == id));
        } finally {
            service.deleteById(id);
        }
    }

    @Test
    void setNameThrowsWhenClientDoesNotExist() {
        assertThrows(RuntimeException.class, () -> service.setName(Long.MAX_VALUE, "Missing"));
    }

    @Test
    void deleteByIdThrowsWhenClientDoesNotExist() {
        assertThrows(RuntimeException.class, () -> service.deleteById(Long.MAX_VALUE));
    }

    @Test
    void createRejectsInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> service.create(" "));
    }

    @Test
    void getByIdRejectsInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> service.getById(0));
    }
}
