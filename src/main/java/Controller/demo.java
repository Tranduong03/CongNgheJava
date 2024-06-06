package Controller;

import Controller.AdminAcc.*;

public class demo {
    public static void main(String[] args) {

        String account = "admin";
        String password = "admin";

        new AdminAcc().saveAcc(account, password);


    }
}