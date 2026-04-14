package org.fox.jdbc;

import org.fox.jdbc.dto.Client;
import org.fox.jdbc.dto.MaxProjectCountClient;
import org.fox.jdbc.dto.Project;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_max_projects_client.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String name = rs.getString("name");
                    int count = rs.getInt("project_count");

                    result.add(new MaxProjectCountClient(name, count));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Client> findAllClients() {
        List<Client> result = new ArrayList<>();

        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_all_clients.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    result.add(new Client(id, name));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Client findClientById(int id) {
        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_client_by_id.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new Client(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Client> findClientsByName(String name) {
        List<Client> result = new ArrayList<>();

        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_clients_by_name.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    result.add(new Client(
                            rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Project> findProjectsByClientId(int clientId) {
        List<Project> result = new ArrayList<>();

        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_projects_by_client_id.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, clientId);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    result.add(new Project(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("client_id")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}