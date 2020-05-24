package datastorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private String url = "jdbc:mysql://localhost:3306/nts";
    private String username = "root";
    private String password = "";
    private Connection connection;

    private static DbConnection instance;

    public static DbConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }
}