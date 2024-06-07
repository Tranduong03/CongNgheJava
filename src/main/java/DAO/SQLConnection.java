package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection getConnection() {

        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BTL_Java;trustServerCertificate=true;encrypt=true";
        String user = "sa";
        String password = "29112004";

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
