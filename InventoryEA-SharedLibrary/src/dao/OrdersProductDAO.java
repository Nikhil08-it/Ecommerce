package dao;

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
import model.OrdersProduct;


@Remote
public interface OrdersProductDAO {
    
    List<OrdersProduct> selectAllOrdersProduct(int id) throws SQLException;
    
    public void deleteOrdersProduct(OrdersProduct op) throws SQLException;
    
    OrdersProduct selectOrdersProduct(int id) throws SQLException;
    
    public void updateOrdersProductQty(OrdersProduct op) throws SQLException;
    
    void insertOrdersProduct(OrdersProduct op) throws SQLException;
    
    Boolean selectexistOrdersProduct(OrdersProduct op) throws SQLException;
    
    double getTotalOrders() throws SQLException;
    
    double getUnpdaidOrders() throws SQLException;
    
    double getStaffTotalOrders(int id) throws SQLException;
    
    double getStaffUnpaidOrders(int id) throws SQLException;
    
    double getTotalOrdersbyDate(String mydate) throws SQLException;
}
