package dao;

import javax.ejb.Stateless;
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
import model.Admin;

@Stateless
public class AdminDAOImpl implements AdminDAO {

    mysqlDB conn = new mysqlDB();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public AdminDAOImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(conn.getClassName());
            connection = DriverManager.getConnection(conn.getUrl(), conn.getUsername(), conn.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }//END METHOD

    @Override
    public List<Admin> selectAllAdmin() throws SQLException {
        String query = "SELECT * FROM admin";
        List< Admin> admin = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int adminId = rs.getInt("adminId");
                String adminFname = rs.getString("adminFname");
                String adminLname = rs.getString("adminLname");
                String adminUsername = rs.getString("adminUsername");
                String adminPass = rs.getString("adminPass");
                admin.add(new Admin(adminId, adminFname, adminLname, adminUsername, adminPass));
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

        return admin;
    }//END METHOD

    @Override
    public void insertAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admin (adminFname, adminLname, adminUsername, adminPass) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, admin.getAdminFname());
            ps.setString(2, admin.getAdminLname());
            ps.setString(3, admin.getAdminUsername());
            ps.setString(4, admin.getAdminPass());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

    }//END METHOD

    @Override
    public Admin selectAdmin(int id) throws SQLException {
        Admin admin = null;
        String query = "SELECT * FROM admin WHERE adminId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                int adminId = rs.getInt("adminId");
                String adminFname = rs.getString("adminFname");
                String adminLname = rs.getString("adminLname");
                String adminUsername = rs.getString("adminUsername");
                String adminPass = rs.getString("adminPass");

                admin = new Admin();
                admin.setAdminId(adminId);
                admin.setAdminFname(adminFname);
                admin.setAdminLname(adminLname);
                admin.setAdminUsername(adminUsername);
                admin.setAdminPass(adminPass);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH       
        return admin;
    }//END METHOD

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        String query = "UPDATE admin SET adminFname = ?, adminLname = ?, adminUsername	= ? WHERE adminId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, admin.getAdminFname());
            ps.setString(2, admin.getAdminLname());
            ps.setString(3, admin.getAdminUsername());
            ps.setInt(4, admin.getAdminId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

    }//END METHOD

    @Override
    public void deleteAdmin(Admin admin) throws SQLException {
        String query = "DELETE FROM admin WHERE adminId = ? ";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, admin.getAdminId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public Boolean loginAdmin(Admin admin) throws SQLException {
        Boolean isLogin = false;
        String query = "SELECT * FROM admin WHERE adminUsername = ? AND adminPass = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, admin.getAdminUsername());
            ps.setString(2, admin.getAdminPass());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                isLogin = true;
            }

        } catch (SQLException e) {
            isLogin = false;
        }//END TRY CATCH  
        return isLogin;
    }//END METHOD
    
    
        @Override
    public Admin getAdminID(Admin admin) throws SQLException {
        String query = "SELECT * FROM admin WHERE adminUsername = ? AND adminPass = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, admin.getAdminUsername());
            ps.setString(2, admin.getAdminPass());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int adminId = rs.getInt("adminId");
                String adminFname = rs.getString("adminFname");
                String adminLname = rs.getString("adminLname");

                admin = new Admin();
                admin.setAdminId(adminId);
                admin.setAdminFname(adminFname);
                admin.setAdminLname(adminLname);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH       
        return admin;
    }//END METHOD
    

}//END CLASS
