package dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import model.Category;

@Remote
public interface CategoryDAO {

    void insertCategory(Category category) throws SQLException;
    
    List<Category> selectAllCategory() throws SQLException;
    
    Category selectCategory(int id) throws SQLException;
    
    void updateCategory(Category category) throws SQLException;
    
    void deleteCategory(Category category) throws SQLException;
}
