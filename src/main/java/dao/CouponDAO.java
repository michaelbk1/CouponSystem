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

public interface CouponDAO extends DAO<Coupon,Integer> {
    void addCouponPurchase(int customerID, int couponID) throws CouponSystemException, SQLException, InterruptedException;
    void deleteCouponPurchase(int customerID, int couponID) throws CouponSystemException, SQLException, InterruptedException;

    @Override
    void add(Coupon coupon) throws CouponSystemException, SQLException, InterruptedException;

    @Override
    void update(Integer couponID, Coupon coupon) throws CouponSystemException, SQLException, InterruptedException;

    @Override
    void delete(Integer couponID) throws CouponSystemException, SQLException, InterruptedException;
    void deleteCouponCompany(Integer companyID) throws  SQLException, InterruptedException;
    @Override
    ArrayList<Coupon> getAll() throws SQLException, InterruptedException, CouponSystemException;

    @Override
    Coupon getOne(Integer couponID) throws CouponSystemException, SQLException, InterruptedException;

    void deleteExpired(Date expiryDate) throws CouponSystemException, SQLException, InterruptedException;

    Coupon findByCompanyTitle(int CompanyID, String  title) throws SQLException, InterruptedException;
    ArrayList<Coupon> getAllByCompany(int companyID) throws SQLException, InterruptedException;
    ArrayList<Coupon> getAllByCompanyCategory(int companyID, int categoryID) throws SQLException, InterruptedException;
    ArrayList<Coupon> getAllByCompanyMaxPrice(int companyID, double maxPrice) throws SQLException, InterruptedException;
    ArrayList<Coupon> getAllByCustomer(int customerID) throws SQLException, InterruptedException ;
    ArrayList<Coupon> getAllByCustomerCategory(int customerID, int categoryID) throws SQLException, InterruptedException ;
    ArrayList<Coupon> getAllByCustomerMaxPrice(int customerID, double maxPrice) throws SQLException, InterruptedException ;
    boolean isCustomerCouponExists(int customerID, int CouponID ) throws SQLException, InterruptedException;

}








