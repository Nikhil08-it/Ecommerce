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
import model.Admin;
import model.Product;

@Stateless
public class ProductDAOImpl implements ProductDAO {

    mysqlDB conn = new mysqlDB();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ProductDAOImpl() {
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
    public void insertProduct(Product product) throws SQLException {
        System.out.println(product.getProdName());
        String query = "INSERT INTO product (prodName, prodQty, prodPrice, prodUnit, prodAdded, catId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, product.getProdName());
            ps.setString(2, product.getProdQty());
            ps.setString(3, product.getProdPrice());
            ps.setString(4, product.getProdUnit());
            ps.setString(5, product.getProdAdded());
            ps.setString(6, product.getProdCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        String query = "SELECT * FROM product";
        List< Product> product = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String prodId = rs.getString("prodId");
                String prodName = rs.getString("prodName");
                String prodQty = rs.getString("prodQty");
                String prodPrice = rs.getString("prodPrice");
                String prodUnit = rs.getString("prodUnit");
                String prodDateAdded = rs.getString("prodDateAdded");
                String prodAdded = rs.getString("prodAdded");
                String catId = rs.getString("catId");

                product.add(new Product(prodId, prodName, prodQty, prodPrice, prodUnit, prodDateAdded, prodAdded, catId));
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

        return product;

    }//END METHOD

    @Override
    public Product selectProduct(int id) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM product WHERE prodId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String productId = rs.getString("prodId");
                String productName = rs.getString("prodName");
                String productQty = rs.getString("prodQty");
                String productPrice = rs.getString("prodPrice");
                String productUnit = rs.getString("prodUnit");
                String productDateAdded = rs.getString("prodDateAdded");
                String productAdded = rs.getString("prodAdded");
                String productCategory = rs.getString("catId");

                product = new Product();
                product.setProdId(productId);
                product.setProdName(productName);
                product.setProdQty(productQty);
                product.setProdPrice(productPrice);
                product.setProdUnit(productUnit);
                product.setProdDateAdded(productDateAdded);
                product.setProdAdded(productAdded);
                product.setProdCategory(productCategory);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

        return product;
    }//END METHOD

    @Override
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE product SET prodName = ?, prodQty = ?, prodPrice = ?, prodUnit = ?, catId= ? WHERE prodId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getProdName());
            ps.setString(2, product.getProdQty());
            ps.setString(3, product.getProdPrice());
            ps.setString(4, product.getProdUnit());
            ps.setString(5, product.getProdCategory());
            ps.setString(6, product.getProdId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public void deleteProduct(Product product) throws SQLException {
        String query = "DELETE FROM product WHERE prodId = ? ";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getProdId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public double getTotalInventory() {
        String query = "SELECT SUM(prodQty * prodPrice) AS TotalInventory FROM product";
        double TotalInventory = 0;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TotalInventory = rs.getDouble("TotalInventory");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return TotalInventory;
    }//END METHOD

    @Override
    public void updateProductQty(Product product) throws SQLException {
        String query = "UPDATE product SET prodQty = ? WHERE prodId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getProdQty());
            ps.setString(2, product.getProdId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

}//END CLASS
