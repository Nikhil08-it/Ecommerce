package dao;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.mysqlDB;
import java.util.ArrayList;
import java.util.List;
import model.Staff;

@Stateless
public class StaffDAOImpl implements StaffDAO {

    mysqlDB conn = new mysqlDB();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public StaffDAOImpl() {
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
    public List<Staff> selectAllStaff() throws SQLException {

        String query = "SELECT * FROM staff";
        List< Staff> staff = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("staffId");
                String firstName = rs.getString("staffFname");
                String lastName = rs.getString("staffLname");
                String address = rs.getString("staffAddress");
                String password = rs.getString("staffPass");
                staff.add(new Staff(id, firstName, lastName, address, password));
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return staff;
    }//END METHOD

    @Override
    public void insertStaff(Staff staff) throws SQLException {
        String query = "INSERT INTO staff (staffFname, staffLname, staffAddress, staffPass) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staff.getStaffFname());
            ps.setString(2, staff.getStaffLname());
            ps.setString(3, staff.getStaffAddress());
            ps.setString(4, staff.getStaffPass());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public Staff selectStaff(int id) throws SQLException {
        Staff staff = null;

        String query = "SELECT * FROM staff WHERE staffId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("staffFname");
                String lastName = rs.getString("staffLname");
                String staffAddress = rs.getString("staffAddress");
                String staffPass = rs.getString("staffPass");

                staff = new Staff();
                staff.setStaffId(id);
                staff.setStaffFname(firstName);
                staff.setStaffLname(lastName);
                staff.setStaffAddress(staffAddress);
                staff.setStaffPass(staffPass);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

        return staff;
    }//END METHOD

    @Override
    public void updateStaff(Staff staff) throws SQLException {

        String query = "UPDATE staff SET staffFname = ?, staffLname = ?, staffAddress = ? WHERE staffId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staff.getStaffFname());
            ps.setString(2, staff.getStaffLname());
            ps.setString(3, staff.getStaffAddress());
            ps.setInt(4, staff.getStaffId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

    }//END METHOD

    @Override
    public void updateStaffPassword(Staff staff) throws SQLException {

        String query = "UPDATE staff SET staffPass = ? WHERE staffId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staff.getStaffPass());
            ps.setInt(2, staff.getStaffId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

    }//END METHOD

    @Override
    public void deleteStaff(Staff staff) throws SQLException {
        String query = "DELETE FROM staff WHERE staffId = ? ";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staff.getStaffId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public Boolean loginStaff(Staff staff) throws SQLException {
        Boolean isLogin = false;
        String query = "SELECT * FROM staff WHERE staffId = ? AND staffPass = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staff.getStaffId());
            ps.setString(2, staff.getStaffPass());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                isLogin = true;
            }

        } catch (SQLException e) {
            isLogin = false;
        }//END TRY CATCH  
        return isLogin;
    }//END METHOD

}//END CLASS
