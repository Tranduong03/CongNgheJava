package DAO;

public class Table {
    public static void main(String[] args) {
        try {
            String NewQuery = "create table mygroup ( id int identity(1,1) primary key, name varchar(255), password varchar(255))";

            Boolean action = SQLOperation.SetDatabase(NewQuery, "Create table");
            if(action==Boolean.TRUE) System.out.println("Table created");
            else System.out.println("Table already exists or Wrong query");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
