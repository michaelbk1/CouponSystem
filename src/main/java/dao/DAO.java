package dao;

import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.List;


public interface DAO<T, ID> {

    void add(T t) throws SQLException, CouponSystemException, InterruptedException;
    void update(ID id, T t) throws SQLException, CouponSystemException, InterruptedException;
    void delete(ID id) throws SQLException, CouponSystemException, InterruptedException;
    List<T> getAll() throws SQLException, InterruptedException, CouponSystemException;
    T getOne(ID id) throws SQLException, CouponSystemException, InterruptedException;

}



