package dao;

import beans.Company;
import beans.Coupon;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDAOImpl implements CompanyDAO {

    private static final String QUERY_INSERT = "INSERT INTO `CouponApp`.`COMPANIES` (`NAME`, `EMAIL`, `password`) VALUES (?, ?, ?);";
    private static final String QUERY_UPDATE = "UPDATE `CouponApp`.`COMPANIES` SET  `EMAIL`= ? , `password`= ?  WHERE (`id` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `CouponApp`.`COMPANIES` WHERE (`id` = ?);";
    private static final String QUERY_GET_ALL = "SELECT * FROM `CouponApp`.`COMPANIES`;";
    private static final String QUERY_GET_ONE = "SELECT * FROM `CouponApp`.`COMPANIES` WHERE (`id` = ?);";
    private static final String QUERY_GET_EMAIL = "SELECT * FROM `CouponApp`.`COMPANIES` WHERE (`email` = ?);";
    private static final String QUERY_EXISTS = "select exists (SELECT * FROM `CouponApp`.COMPANIES where `EMAIL`=? and `password`=?) as res;";
    private static final String QUERY_EMAIL_EXIST = "select exists (SELECT * FROM `CouponApp`.COMPANIES where `EMAIL`=? ) as res;";
    private static final String QUERY_NAME_EXIST  = "select exists (SELECT * FROM `CouponApp`.COMPANIES where `name`=?) as res;";




    @Override
    public void add(Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());

        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public void update(Integer id, Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        //params.put(1, company.getName());
        params.put(1, company.getEmail());
        params.put(2, company.getPassword());
        params.put(3, id);
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }

    @Override
    public void delete(Integer id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public ArrayList<Company> getAll() throws SQLException, InterruptedException {
        List<Company> results = new ArrayList<>();

        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);//ecuteQueryWithResults(QUERY_GET_ALL);


        for (Object object : rows) {
            results.add(ResultsUtils.companyFromRow((HashMap<String, Object>) object));
        }

        return (ArrayList<Company>) results;
    }


    @Override
    public Company getOne(Integer id) throws SQLException, InterruptedException {
        Company result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.companyFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

    @Override
    public Company getByEmail(String email) throws SQLException, InterruptedException {
        Company result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_EMAIL, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.companyFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException {
        boolean result = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_EXISTS, params);
        result = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return result;
    }

    @Override
    public boolean isCompanyExistByEmail(String email) throws SQLException, InterruptedException {
        boolean result = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_EMAIL_EXIST, params);
        result = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return result;
    }

    @Override
    public boolean isCompanyExistByName(String name) throws SQLException, InterruptedException {
        boolean result = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_NAME_EXIST, params);
        result = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return result;
    }



}


