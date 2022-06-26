package jobs;


import dao.CouponDAO;
import dao.CouponDAOImpl;
import exceptions.CouponSystemException;
import utils.ART_UTIL;

import java.sql.SQLException;
import java.time.LocalDate;


public class DeleteExpired implements Runnable{

    public DeleteExpired() {

    }

    @Override
    public void run() {
        while (true) {
            CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
            LocalDate date = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            System.out.println(ART_UTIL.DELETE_EXPIRED);
            try {
                couponDAO.deleteExpired(sqlDate);
            } catch (CouponSystemException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

