package org.fox.jdbc;

import org.flywaydb.core.Flyway;
import java.util.Properties;

public class DatabaseMigrationService {

    public static void migrate(Properties props) {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password")
                )
                .load();
        // run Flyway migrations (safe to run multiple times)
        flyway.migrate();
    }
}