package dao;
import beans.Category;

import db.JDBCUtils;
import db.ResultsUtils;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CategoryDAOImpl implements CategoryDAO {
    private static final String QUERY_INSERT = "INSERT INTO `CouponApp`.`categories` (`id`,`name`) VALUES (?,?)";
    private static final String QUERY_GET_ALL = "SELECT name FROM `CouponApp`.`categories`";


    public List<Category> getAllCategories() throws  SQLException, InterruptedException {
        List<Category> categories = new ArrayList<>();
        List<?> resultRows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Object object : resultRows) {
            categories.add(ResultsUtils.categoryFromRow((HashMap<String, Object>) object));
        }
        return categories;
    }

    @Override
    public void AddCategory(Category category) throws SQLException, InterruptedException {
        System.out.println(category.ordinal());
        System.out.println(category.name());
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, category.ordinal());
        params.put(2, category.name());

        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }
}
