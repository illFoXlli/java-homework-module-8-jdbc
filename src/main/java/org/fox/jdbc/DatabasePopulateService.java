package org.fox.jdbc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args) {
        try {
            String sql = new String(Files.readAllBytes(Paths.get("sql/populate_db.sql")));

            Connection conn = Database.getInstance().getConnection();

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }

            System.out.println("DB populated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}