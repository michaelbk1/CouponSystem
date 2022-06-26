import beans.Company;
import beans.Customer;
import beans.Coupon;
import beans.Category;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;


import db.ConnectionPool;
import db.DatabaseManager;
import exceptions.CouponSystemException;
import jobs.DeleteExpired;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


public class CouponApplication {
    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {
        System.out.print("START");
        DatabaseManager.getInstance().dropCreateStrategy();
        Thread t1 = new Thread(new DeleteExpired());
        t1.start();

    }
}
