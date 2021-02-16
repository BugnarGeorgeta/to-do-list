package org.example.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

        InputStream dbproperties = DatabaseConfiguration.class.getClassLoader()
                .getResourceAsStream("db.properties");

        try {
            Properties properties = new Properties();
            properties.load(dbproperties);

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
        } finally {
            if (dbproperties != null) {
                dbproperties.close();
            }

        }
    }
}
