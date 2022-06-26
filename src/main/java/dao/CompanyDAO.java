package dao;

import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;

import java.sql.SQLException;

import java.util.ArrayList;

public interface CompanyDAO extends DAO<Company,Integer> {


    @Override
    void add(Company company) throws SQLException,  InterruptedException;

    @Override
    void update(Integer companyId, Company company) throws SQLException, InterruptedException;

    @Override
    void delete(Integer companyId) throws SQLException, InterruptedException;

    @Override
    ArrayList<Company> getAll() throws SQLException, InterruptedException;

    @Override
    Company getOne(Integer companyId) throws SQLException, InterruptedException;
    Company getByEmail(String email) throws SQLException, InterruptedException;

    boolean isCompanyExists(String email, String password ) throws SQLException, InterruptedException;

    boolean isCompanyExistByEmail(String email) throws SQLException, InterruptedException;
    boolean isCompanyExistByName(String name) throws SQLException, InterruptedException;



}








