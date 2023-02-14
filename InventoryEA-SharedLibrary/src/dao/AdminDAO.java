package dao;

import javax.ejb.Remote;
import model.Admin;
import db.mysqlDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Remote
public interface AdminDAO {

    List<Admin> selectAllAdmin() throws SQLException;

    void insertAdmin(Admin admin) throws SQLException;

    Admin selectAdmin(int id) throws SQLException;

    void updateAdmin(Admin admin) throws SQLException;

    public void deleteAdmin(Admin admin) throws SQLException;

    Boolean loginAdmin(Admin admin) throws SQLException;
    
    public Admin getAdminID(Admin admin) throws SQLException;
}
