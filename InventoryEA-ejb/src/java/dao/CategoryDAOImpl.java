package dao;

import javax.ejb.Stateless;
import model.Category;
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

@Stateless
public class CategoryDAOImpl implements CategoryDAO {

    mysqlDB conn = new mysqlDB();

    public CategoryDAOImpl() {
    }
    
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

    @Override
    public void insertCategory(Category category) throws SQLException {
        String query = "INSERT INTO category (catName) VALUES (?)";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getCatName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD

    @Override
    public List<Category> selectAllCategory() throws SQLException {
        String query = "SELECT * FROM category";
        List< Category> category = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("catId");
                String categoryName = rs.getString("catName");
                category.add(new Category(categoryId, categoryName));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
        return category;
    }//END METHOD

    @Override
    public Category selectCategory(int id) throws SQLException {
        Category category = null;
        String query = "SELECT * FROM category WHERE catId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int categoryId = rs.getInt("catId");
                String categoryName = rs.getString("catName");

                category = new Category();
                category.setCatId(categoryId);
                category.setCatName(categoryName);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

        return category;
    }//END METHOD

    @Override
    public void updateCategory(Category category) throws SQLException {
        String query = "UPDATE category SET catName = ? WHERE catId = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getCatName());
            ps.setInt(2, category.getCatId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH

    }//END METHOD
    
    @Override
    public void deleteCategory(Category category) throws SQLException {
        String query = "DELETE FROM category WHERE catId = ? ";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, category.getCatId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }//END TRY CATCH
    }//END METHOD


}
