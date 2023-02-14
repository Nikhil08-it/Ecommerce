package dao;

import java.sql.SQLException;
import javax.ejb.Remote;
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

@Remote
public interface ProductDAO {
    void insertProduct(Product product) throws SQLException;
    
    List<Product> selectAllProduct() throws SQLException;
    
    Product selectProduct(int id) throws SQLException;
    
    void updateProduct(Product product) throws SQLException;
    
    void deleteProduct(Product product) throws SQLException;
    
    double getTotalInventory();
    
    void updateProductQty(Product product) throws SQLException;
    
}
