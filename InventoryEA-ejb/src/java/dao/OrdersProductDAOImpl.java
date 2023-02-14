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
import model.OrdersProduct;

@Stateless
public class OrdersProductDAOImpl implements OrdersProductDAO {

    mysqlDB conn = new mysqlDB();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
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

    public OrdersProductDAOImpl() {
    }

    @Override
    public List<OrdersProduct> selectAllOrdersProduct(int id) throws SQLException {
        String query = "SELECT * FROM orders_product WHERE orderId = ?";
        List< OrdersProduct> op = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderprodId = rs.getInt("orderprodId");
                int orderId = rs.getInt("orderId");
                int prodId = rs.getInt("prodId");
                int orderQty = rs.getInt("orderQty");
                op.add(new OrdersProduct(orderprodId, orderQty, orderId, prodId));
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return op;
    }//END METHOD

    @Override
    public void deleteOrdersProduct(OrdersProduct op) throws SQLException {
        String query = "DELETE FROM orders_product WHERE orderprodId = ? ";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, op.getOrderprodId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public OrdersProduct selectOrdersProduct(int id) throws SQLException {
        OrdersProduct op = null;
        String query = "SELECT * FROM orders_product WHERE orderprodId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                int prodId = rs.getInt("prodId");
                int orderQty = rs.getInt("orderQty");

                op = new OrdersProduct();
                op.setOrderId(orderId);
                op.setProdId(prodId);
                op.setOrderQty(orderQty);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return op;
    }//END METHOD

    @Override
    public Boolean selectexistOrdersProduct(OrdersProduct op) throws SQLException {
        String query = "SELECT * FROM orders_product WHERE orderId = ? AND prodId = ?";
        Boolean isTrue = false;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, op.getOrderId());
            ps.setInt(2, op.getProdId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                isTrue = true;
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return isTrue;
    }

    @Override
    public void updateOrdersProductQty(OrdersProduct op) throws SQLException {
        String query = "UPDATE orders_product SET orderQty = ? WHERE orderprodId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, op.getOrderQty());
            ps.setInt(2, op.getOrderprodId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public void insertOrdersProduct(OrdersProduct op) throws SQLException {
        String query = "INSERT INTO orders_product (orderId, prodId, orderQty) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, op.getOrderId());
            ps.setInt(2, op.getProdId());
            ps.setInt(3, op.getOrderQty());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public double getTotalOrders() throws SQLException {
        String query = "SELECT SUM(orders_product.orderQty * product.prodPrice) AS TotalOrders FROM orders_product INNER JOIN product ON orders_product.prodId = product.prodId INNER JOIN orders ON orders_product.orderId = orders.orderId WHERE orders.orderStatus = 'Paid';";
        double TotalOrders = 0;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TotalOrders = rs.getDouble("TotalOrders");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return TotalOrders;
    }//END METHOD
    
    
        @Override
    public double getTotalOrdersbyDate(String mydate) throws SQLException {
        String query = "SELECT SUM(orders_product.orderQty * product.prodPrice) AS TotalOrders FROM orders_product INNER JOIN product ON orders_product.prodId = product.prodId INNER JOIN orders ON orders_product.orderId = orders.orderId WHERE orders.orderStatus = 'Paid' AND orders.orderDate = ?;";
        double TotalOrders = 0;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, mydate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TotalOrders = rs.getDouble("TotalOrders");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return TotalOrders;
    }//END METHOD
    
    
        @Override
    public double getStaffTotalOrders(int id) throws SQLException {
        String query = "SELECT SUM(orders_product.orderQty * product.prodPrice) AS TotalOrders FROM orders_product INNER JOIN product ON orders_product.prodId = product.prodId INNER JOIN orders ON orders_product.orderId = orders.orderId WHERE orders.orderStatus = 'Paid' AND orders.orderCreatedby = ?;";
        double TotalOrders = 0;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TotalOrders = rs.getDouble("TotalOrders");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return TotalOrders;
    }//END METHOD

    @Override
    public double getUnpdaidOrders() throws SQLException {
        String query = "SELECT SUM(orders_product.orderQty * product.prodPrice) AS TotalOrders FROM orders_product INNER JOIN product ON orders_product.prodId = product.prodId INNER JOIN orders ON orders_product.orderId = orders.orderId WHERE orders.orderStatus = 'Unpaid';";
        double UnpaidOrders = 0;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UnpaidOrders = rs.getDouble("TotalOrders");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return UnpaidOrders;
    }//END METHOD
    
    
            @Override
    public double getStaffUnpaidOrders(int id) throws SQLException {
        String query = "SELECT SUM(orders_product.orderQty * product.prodPrice) AS TotalOrders FROM orders_product INNER JOIN product ON orders_product.prodId = product.prodId INNER JOIN orders ON orders_product.orderId = orders.orderId WHERE orders.orderStatus = 'Unpaid' AND orders.orderCreatedby = ?;";
        double TotalOrders = 0;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TotalOrders = rs.getDouble("TotalOrders");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return TotalOrders;
    }//END METHOD

}//END CLASS
