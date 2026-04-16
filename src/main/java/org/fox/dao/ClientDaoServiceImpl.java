package org.fox.dao;

import org.fox.jdbc.Database;
import org.fox.dto.Client;
import org.fox.jdbc.SqlReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoServiceImpl implements ClientDaoService {

    private static final String CREATE_CLIENT_SQL = SqlReader.read("create_client.sql");
    private static final String FIND_CLIENT_BY_ID_SQL = SqlReader.read("find_client_by_id.sql");
    private static final String UPDATE_CLIENT_NAME_SQL = SqlReader.read("update_client_name.sql");
    private static final String DELETE_CLIENT_BY_ID_SQL = SqlReader.read("delete_client_by_id.sql");
    private static final String FIND_ALL_CLIENTS_SQL = SqlReader.read("find_all_clients.sql");

    @Override
    public long create(String name) {
        try {
            try (Connection conn = Database.getInstance().getConnection();
                 PreparedStatement stmt =
                         conn.prepareStatement(CREATE_CLIENT_SQL, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, name);
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                }
            }

            throw new RuntimeException("Failed to get ID");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getById(long id) {
        try {
            try (Connection conn = Database.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(FIND_CLIENT_BY_ID_SQL)) {

                stmt.setLong(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("name");
                    }
                }
            }

            throw new RuntimeException("Client not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setName(long id, String name) {
        try {
            try (Connection conn = Database.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(UPDATE_CLIENT_NAME_SQL)) {

                stmt.setString(1, name);
                stmt.setLong(2, id);

                int updatedRows = stmt.executeUpdate();
                if (updatedRows == 0) {
                    throw new RuntimeException("Client not found");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(long id) {
        try {
            try (Connection conn = Database.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(DELETE_CLIENT_BY_ID_SQL)) {

                stmt.setLong(1, id);

                int updatedRows = stmt.executeUpdate();
                if (updatedRows == 0) {
                    throw new RuntimeException("Client not found");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> listAll() {
        List<Client> result = new ArrayList<>();

        try {
            try (Connection conn = Database.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(FIND_ALL_CLIENTS_SQL);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    result.add(new Client(
                            rs.getLong("id"),
                            rs.getString("name")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
