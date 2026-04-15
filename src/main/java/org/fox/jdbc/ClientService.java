package org.fox.jdbc;

import org.fox.jdbc.dto.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private final Connection conn;

    public ClientService() {
        this.conn = Database.getInstance().getConnection();
    }

    public long create(String name) {

        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("Invalid name");
        }

        try {
            String sql = "INSERT INTO client(name) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                return rs.getLong(1);
            }

            throw new RuntimeException("Failed to get ID");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id) {

        try {
            String sql = "SELECT name FROM client WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }

            throw new RuntimeException("Client not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name) {

        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("Invalid name");
        }

        try {
            String sql = "UPDATE client SET name = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {

        try {
            String sql = "DELETE FROM client WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll() {

        List<Client> result = new ArrayList<>();

        try {
            String sql = "SELECT id, name FROM client";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new Client(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}