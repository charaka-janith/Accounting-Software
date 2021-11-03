package back_end.db;

import back_end.config.DontPush;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector dbConnection;
    private Connection connection;

    private DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountingSoftware", DontPush.userName, DontPush.password);
    }

    public static DBConnector getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnector();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
