package dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import model.Staff;

@Remote
public interface StaffDAO {
    
    List<Staff> selectAllStaff() throws SQLException;
    
    void insertStaff(Staff staff) throws SQLException;
    
    Staff selectStaff(int id) throws SQLException;
    
    void updateStaff(Staff staff) throws SQLException;
    
    void deleteStaff(Staff staff) throws SQLException;
    
    Boolean loginStaff(Staff staff) throws SQLException;
    
    void updateStaffPassword(Staff staff) throws SQLException;
    
}
