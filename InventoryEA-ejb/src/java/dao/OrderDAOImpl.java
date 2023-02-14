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
import model.Orders;

@Stateless
public class OrderDAOImpl implements OrderDAO {

    mysqlDB conn = new mysqlDB();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public OrderDAOImpl() {
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
    public List<Orders> selectAllOrders() throws SQLException {

        String query = "SELECT * FROM orders";
        List< Orders> orders = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                String orderDate = rs.getString("orderDate");
                int orderCreatedby = rs.getInt("orderCreatedby");
                String orderStatus = rs.getString("orderStatus");

                orders.add(new Orders(orderId, orderDate, orderCreatedby, orderStatus));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return orders;
    }//END METHOD
    
    @Override
    public List<Orders> selectOrdersCreatedby(int id) throws SQLException {

        String query = "SELECT * FROM orders WHERE orderCreatedby = ?";
        List< Orders> orders = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                String orderDate = rs.getString("orderDate");
                int orderCreatedby = rs.getInt("orderCreatedby");
                String orderStatus = rs.getString("orderStatus");

                orders.add(new Orders(orderId, orderDate, orderCreatedby, orderStatus));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return orders;
    }//END METHOD
    
    
    
    @Override
    public Orders selectOrders(int id) throws SQLException {
        Orders orders = null;
        String query = "SELECT * FROM orders WHERE orderId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                String orderDate = rs.getString("orderDate");
                int orderCreatedby = rs.getInt("orderCreatedby");
                String orderStatus = rs.getString("orderStatus");

                orders = new Orders();
                orders.setOrderId(orderId);
                orders.setOrderDate(orderDate);
                orders.setOrderCreatedby(orderCreatedby);
                orders.setOrderStatus(orderStatus);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return orders;
    }//END METHOD
    
    

    
    
    @Override
    public void updatePayOrders(Orders orders) throws SQLException {
        String query = "UPDATE orders SET orderStatus = ? WHERE orderId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, orders.getOrderStatus());
            ps.setInt(2, orders.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD
    
    @Override
    public void insertOrders(Orders orders) throws SQLException {
        String query = "INSERT INTO orders (orderCreatedby, orderStatus) VALUES (?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orders.getOrderCreatedby());
            ps.setString(2, "Unpaid");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD
    
    @Override
    public int getlastOrderID() throws SQLException{
    String query = "SELECT MAX(orderId) FROM orders;";
    int orderId = 0;
            try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderId = rs.getInt("MAX(orderId)");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    return orderId;
    }//END METHOD

}//END CLASS
