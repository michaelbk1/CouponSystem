package dao;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String QUERY_INSERT = "INSERT INTO `CouponApp`.`CUSTOMERS` (`first_name`,`last_name`, `email`, `password`) VALUES (?,?, ?, ?);";
    private static final String QUERY_UPDATE = "UPDATE `CouponApp`.`CUSTOMERS` SET  `first_name` = ?,    `last_name` = ?,    `email`= ? , `password`= ?  WHERE (`id` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `CouponApp`.`CUSTOMERS` WHERE (`id` = ?);";
    private static final String QUERY_GET_ALL = "SELECT * FROM `CouponApp`.`CUSTOMERS`;";
    private static final String QUERY_GET_ONE = "SELECT * FROM `CouponApp`.`CUSTOMERS` WHERE (`id` = ?);";
    private static final String QUERY_IS_EXISTS = "select exists (SELECT * FROM `CouponApp`.CUSTOMERS where email=? and password=?) as res;";
    private static final String QUERY_GET_EMAIL = "SELECT * FROM `CouponApp`.`CUSTOMERS` WHERE (`email` = ?);";

    private static final String QUERY_GET_ONE_BY_COMPANY_ID_TITLE = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) AND (`title` = ?);";
    private static final String QUERY_GET_ALL_BY_COMPANY_ID = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) ;";
    private static final String QUERY_GET_ALL_BY_COMPANY_ID_CATEGORY = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) AND (`category_ID`=?) ;";
    private static final String QUERY_GET_ALL_BY_COMPANY_ID_MAXPRICE = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) AND (`price`<= ?) ;";


    @Override
    public void add(Customer company) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getFirstName());
        params.put(2, company.getLastName());
        params.put(3, company.getEmail());
        params.put(4, company.getPassword());

        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public void update(Integer id, Customer company) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getFirstName());
        params.put(2, company.getLastName());
        params.put(3, company.getEmail());
        params.put(4, company.getPassword());
        params.put(5, id);
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }


    @Override
    public void delete(Integer id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, InterruptedException {
        List<Customer> results = new ArrayList<>();

        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Object object : rows) {
            results.add(ResultsUtils.customerFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Customer>) results;
    }


    @Override
    public Customer getOne(Integer id) throws SQLException, InterruptedException {
        Customer result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.customerFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

    @Override
    public Customer getByEmail(String email) throws SQLException, InterruptedException {
        Customer result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_EMAIL, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.customerFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }


    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException {
        boolean result = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS, params);
        result = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return result;
    }


    public ArrayList<Coupon> getAllByCompany(int companyID) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_BY_COMPANY_ID, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }
    public ArrayList<Coupon> getAllByCompanyCategory(int companyID, int categoryID) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, categoryID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_BY_COMPANY_ID_CATEGORY, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }
    public ArrayList<Coupon> getAllByCompanyMaxPrice(int companyID, double maxPrice) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, maxPrice);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_BY_COMPANY_ID_MAXPRICE, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }
}