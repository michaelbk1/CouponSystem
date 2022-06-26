package dao;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends DAO<Customer,Integer> {
    boolean isCustomerExists(String email, String password ) throws CouponSystemException, SQLException, InterruptedException;


    @Override
    void add(Customer customer) throws CouponSystemException, SQLException, InterruptedException;

    @Override
    void update(Integer customerId, Customer customer) throws CouponSystemException, SQLException, InterruptedException;

    @Override
    void delete(Integer customerId) throws CouponSystemException, SQLException, InterruptedException;

    @Override
    ArrayList<Customer> getAll() throws SQLException, InterruptedException, CouponSystemException;

    @Override
    Customer getOne(Integer customerId) throws CouponSystemException, SQLException, InterruptedException;

    Customer getByEmail(String email) throws SQLException, InterruptedException;


}








