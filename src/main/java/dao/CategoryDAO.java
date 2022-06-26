package dao;

import beans.Category;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    public void AddCategory(Category category) throws SQLException, InterruptedException, CouponSystemException;
    List<Category> getAllCategories() throws CouponSystemException, SQLException, InterruptedException;
}

