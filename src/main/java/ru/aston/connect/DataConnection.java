package ru.aston.connect;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataConnection extends HttpServlet {

    private Connection connection;

    public Connection conn() {
        try {

            Properties properties = loadProperties();
            Class.forName(properties.getProperty("driver"));

            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));

            return connection;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            log("Ошибка в инициализации", e);
            throw new RuntimeException(e);
        }
    }

    public void closeConn() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Логирование ошибок
            log("Ошибка в закрытие базы", e);
        }
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл database.properties не найден");
            }
            properties.load(input);
        }
        return properties;
    }
}
