package org.fox.jdbc;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private static Database instance;
    private final String url;
    private final String user;
    private final String password;

    private Database() {
        String propertiesFileName = System.getProperty("app.config", "application.properties");

        try (InputStream input = Database.class
                .getClassLoader()
                .getResourceAsStream(propertiesFileName)) {

            Properties props = new Properties();

            if (input == null) {
                throw new RuntimeException("Файл " + propertiesFileName + " не найден");
            }

            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения настроек БД", e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
