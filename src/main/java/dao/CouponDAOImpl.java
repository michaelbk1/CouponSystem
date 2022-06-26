package dao;

import beans.Coupon;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.CouponSystemException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponDAOImpl implements CouponDAO {

    private static final String QUERY_INSERT = "INSERT INTO `couponapp`.`coupons`(`company_ID`,`category_ID`,`title`,`description`,`start_date`,`end_date`,`amount`,`price`,`image`) VALUES(?,?,?,?,?,?,?,?,?);";
    private static final String QUERY_UPDATE = "UPDATE `couponapp`.`coupons` \n" +
            "SET `COMPANY_ID` = ?,\n" +
            "`category_ID` = ?,`title` = ?, `description` = ?, `start_date` = ?,\n" +
            "`end_date` = ?,`amount` = ?,`price` = ?,`image` = ?\n" +
            " WHERE `ID` = ?;";
    private static final String QUERY_DELETE = "DELETE FROM `CouponApp`.`COUPONS` WHERE (`id` = ?);";
    private static final String QUERY_GET_ALL = "SELECT * FROM `CouponApp`.`COUPONS`;";
    private static final String QUERY_GET_ONE = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`id` = ?);";

    private static final String QUERY_ADD_PURCHASE = "INSERT INTO `CouponApp`.`customers_coupons`\n" +
            "(`customer_ID`, `COUPON_ID`) VALUES ( ? , ? ); ";
    private static final String QUERY_DELETE_PURCHASE = "DELETE FROM `CouponApp`.`customers_coupons` WHERE `CUSTOMER_ID` = ? AND `coupon_id` = ? ";
    private static final String QUERY_DELETE_COUPON = "DELETE FROM `CouponApp`.`customers_coupons` WHERE  `coupon_id` = ? ";
    private static final String QUERY_DELETE_COMPANY = "DELETE FROM `CouponApp`.`customers_coupons` WHERE  `coupon_id` IN  (SELECT coupon_id FROM coupons.coupons where `coupons`.`company_ID`=?));DELETE FROM `coupons`.`coupons` where `coupons`.`company_ID`=?;";
    private static final String QUERY_DELETE_EXPIRED = "DELETE FROM `CouponApp`.`coupons` where `coupons`.`end_date`<?;";
    private static final String QUERY_DELETE_EXPIRED_PURCHASE = "DELETE FROM `CouponApp`.`customers_coupons` WHERE  `coupon_id` IN  (SELECT coupon_id FROM `couponapp`.`coupons` where `coupons`.`end_date`<?) ;";

    private static final String QUERY_GET_ONE_COUPON_BY_COMPANY_ID_TITLE = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) AND (`title` = ?);";
    private static final String QUERY_GET_ALL_COUPON_BY_COMPANY_ID = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) ;";
    private static final String QUERY_GET_ALL_COUPON_BY_COMPANY_ID_CATEGORY = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) AND (`category_ID`=?) ;";
    private static final String QUERY_GET_ALL_COUPON_BY_COMPANY_ID_MAXPRICE = "SELECT * FROM `CouponApp`.`COUPONS` WHERE (`company_ID` = ?) AND (`price`<= ?) ;";

    private static final String QUERY_GET_ALL_COUPON_BY_CUSTOMER_ID = "SELECT *  FROM `CouponApp`.`coupons` c JOIN `CouponApp`.`customers_coupons` cc on c.id = cc.coupon_id where CUSTOMER_ID=?;";
    private static final String QUERY_GET_ALL_COUPON_BY_CUSTOMER_ID_CATEGORY = "SELECT *  FROM `CouponApp`.`coupons` c JOIN CouponApp.customers_coupons cc on c.id = cc.coupon_id WHERE (`customer_ID` = ?) AND (`category_ID`=?) ;";
    private static final String QUERY_GET_ALL_COUPON_BY_CUSTOMER_ID_MAXPRICE = "SELECT *  FROM `CouponApp`.`coupons` c JOIN CouponApp.customers_coupons cc on c.id = cc.coupon_id WHERE (`customer_ID` = ?) AND (`price`<= ?) ;";
    private static final String QUERY_EXISTS_COUPON_CUSTOMER = "select exists (SELECT * FROM `CouponApp`.`customers_coupons` where `customer_ID`=? and `coupon_id`=?) as res;";


    @Override
    public void add(Coupon coupon) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyID());
        params.put(2, coupon.getCategory().ordinal());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());

        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public void update(Integer id, Coupon coupon) throws SQLException, InterruptedException {
        try {

            Map<Integer, Object> params = new HashMap<>();
            params.put(1, coupon.getCompanyID());
            params.put(2, coupon.getCategory().ordinal());
            params.put(3, coupon.getTitle());
            params.put(4, coupon.getDescription());
            params.put(5, coupon.getStartDate());
            params.put(6, coupon.getEndDate());
            params.put(7, coupon.getAmount());
            params.put(8, coupon.getPrice());
            params.put(9, coupon.getImage());
            params.put(10, id);
            JDBCUtils.executeQuery(QUERY_UPDATE, params);
            System.out.println("update");
        }
        catch(Exception e )
        {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void delete(Integer id) throws  SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE_COUPON, params);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public void deleteCouponCompany(Integer companyID) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);

        JDBCUtils.executeQuery(QUERY_DELETE_COMPANY, params);
    }

    @Override
    public ArrayList<Coupon> getAll() throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();

        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }


    @Override
    public Coupon getOne(Integer id) throws SQLException, InterruptedException {
        Coupon result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.couponFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);
        JDBCUtils.executeQuery(QUERY_ADD_PURCHASE, params);
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);
        JDBCUtils.executeQuery(QUERY_DELETE_PURCHASE, params);
    }



    @Override
    public void deleteExpired(Date expiryDate) throws SQLException, InterruptedException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, expiryDate);
        JDBCUtils.executeQuery(QUERY_DELETE_EXPIRED_PURCHASE, map);
        JDBCUtils.executeQuery(QUERY_DELETE_EXPIRED, map);
    }

    public Coupon findByCompanyTitle(int companyID, String title) throws SQLException, InterruptedException {

        Coupon result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, title);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE_COUPON_BY_COMPANY_ID_TITLE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.couponFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;

    }
    public ArrayList<Coupon> getAllByCompany(int companyID) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_COMPANY_ID, params);
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
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_COMPANY_ID_CATEGORY, params);
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
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_COMPANY_ID_MAXPRICE, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }

    public ArrayList<Coupon> getAllByCustomer(int customerID) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_CUSTOMER_ID, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }
    public ArrayList<Coupon> getAllByCustomerCategory(int customerID, int categoryID) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, categoryID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_CUSTOMER_ID_CATEGORY, params);
        for (Object object : rows)
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        return (ArrayList<Coupon>) results;
    }
    public ArrayList<Coupon> getAllByCustomerMaxPrice(int customerID, double maxPrice) throws SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, maxPrice);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_CUSTOMER_ID_MAXPRICE, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return (ArrayList<Coupon>) results;
    }

    public boolean isCustomerCouponExists(int customerID, int couponID ) throws SQLException, InterruptedException {
        boolean result = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_EXISTS_COUPON_CUSTOMER, params);
        result = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return result;
    }
}
























