package Controller;

import Controller.AdminAcc.*;

public class demo {
    public static void main(String[] args) {

        String account = "DreamKillDemon";
        String password = "legendNeverDie";

        new AdminAcc().saveAcc(account, password);


    }
}