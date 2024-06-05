package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLOperation {
    public static Boolean SetDatabase(String Query, String msg) {
        Boolean Completed = false;
        try{
            Connection con = SQLConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Query);
            if(!msg.equals("")) {
                Completed=Boolean.TRUE;
                System.out.println(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Completed = Boolean.FALSE;
        }
        return Completed;
    }

    public static ResultSet GetDatabase(String Query) {
        Boolean Completed = false;
        SQLConnection sqlConn = new SQLConnection();
        try {
            Connection con = sqlConn.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(Query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
