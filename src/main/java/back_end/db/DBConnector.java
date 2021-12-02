package back_end.db;

import back_end.config.SqlConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector dbConnection;
    private final Connection connection;

    // initialize a connection once and for all
    private DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/accountingSoftware",
                SqlConfig.user,
                SqlConfig.password
        );
    }

    // if no connection, then create and return one
    public static DBConnector getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnector();
        }
        return dbConnection;
    }

    // giving same connection to everyone who asks for it
    public Connection getConnection() {
        return connection;
    }
}
