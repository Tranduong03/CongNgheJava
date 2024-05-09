package DAO;

public class Table {
    public static void main(String[] args) {

    }

    public static void inputQuery(String query) {
        try{
            Boolean action = SQLOperation.SetDatabase(query, "Input Query");
            if(action==Boolean.TRUE) System.out.println("Query action completed");
            else System.out.println("Wrong query");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
