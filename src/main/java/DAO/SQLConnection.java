package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection getConnection() {

        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=database_for_dkstore;trustServerCertificate=true;encrypt=true";
        String user = "sa";
        String password = "03072004";

        try {
            conn = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to database:"+ conn.getCatalog());
        } catch (SQLServerException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return conn;
    }
}
