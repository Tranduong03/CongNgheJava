package Controller;

import DAO.AdminDAO;
import Model.Admin;

import java.security.MessageDigest;

public class AdminAcc {

    protected static void saveAcc(String adminName, String adminPassword) {
        Admin acc = new Admin(adminName,adminPassword);
        new AdminDAO().addAdminAccount(acc);
    }

    public static void main(String[] args) {
        String account1 = "DreamKillDemon", account2="adminNumber2";
        String password1 = "Kietdeptrai", password2="passwordNumber2";

        saveAcc(account1, password1);
        saveAcc(account2,password2);
    }

}
