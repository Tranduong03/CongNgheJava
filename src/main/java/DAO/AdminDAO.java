package DAO;

import Model.Admin;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    protected static String md5Converter(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void addAdminAccount(Admin admin) {
        SQLOperation.SetDatabase("insert into AdminAccount(Account, Password) values('" + admin.getAccount() + "','" + md5Converter(admin.getPassword()) + "');", "");
    }

    public static Admin checkAdminAccount(String account, String password) {
        Admin admin = null;
        try {
            ResultSet rs = SQLOperation.GetDatabase("select* from AdminAccount "
                    + "where Account='"+ account +"' "
                    + "and Password= '"+ AdminDAO.md5Converter(password) + "'; ");
            while (true){
                assert rs != null;
                if(!rs.next()) break;
//                admin = new Admin(account, password);
                admin = new Admin();

                admin.setAccount(rs.getString("Account"));
                admin.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}
