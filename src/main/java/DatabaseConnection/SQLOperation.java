package DatabaseConnection;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLOperation {
    public static Boolean SetDatabase(String Query, String msg) {
        Boolean Completed = false;
        try{
            Connection con = SQLConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Query);
            if(!msg.equals("")) Completed=Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            Completed = Boolean.FALSE;
        }
        return Completed;
    }
}
