package dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
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
import model.Orders;

@Remote
public interface OrderDAO {
    List<Orders> selectAllOrders() throws SQLException;
    
    Orders selectOrders(int id) throws SQLException;
    
    void updatePayOrders(Orders orders) throws SQLException;
    
    void insertOrders(Orders orders) throws SQLException;
    
    int getlastOrderID() throws SQLException;
    
    List<Orders> selectOrdersCreatedby(int id) throws SQLException;
    
}
