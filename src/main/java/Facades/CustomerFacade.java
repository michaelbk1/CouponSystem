package Facades;

import beans.Category;
import beans.Coupon;
import beans.Customer;
import dao.*;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerFacade extends  ClientFacade {
    private int customerID;

    @Override
    public boolean login(String email, String password) throws InterruptedException, SQLException, CouponSystemException {
        CustomerDAO customerDAO =  new CustomerDAOImpl();

        if ( !customerDAO.isCustomerExists(email, password)) {
            return false;
        }

        Customer customer = customerDAO.getByEmail(email);
        System.out.println(customer);

        customerID = customer.getId();


        return true;
    }



    public void purchaseCoupon(Coupon coupon) throws Exception {
        java.sql.Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        CouponDAO couponDAO = new CouponDAOImpl();
        int couponId = coupon.getId();


        if (couponDAO.isCustomerCouponExists(customerID, couponId ) ) {
            throw new CouponSystemException(ErrMsg.COUPON_CUSTOMER_EXIST);
        }

        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_PURCHASE_SOLD_OUT);
        }


        if (currentDate.after(coupon.getEndDate())) {
            throw new CouponSystemException(ErrMsg.COUPON_PURCHASE_EXPIRED);
        }

        ArrayList<Coupon> coupons = getCustomerCoupons();


        if(coupons.contains(coupon))
            throw new CouponSystemException(ErrMsg.COUPON_CUSTOMER_EXIST);


        coupon = couponDAO.getOne(coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponDAO.update(coupon.getId(), coupon);
        couponDAO.addCouponPurchase(customerID, coupon.getId());

    }

    public ArrayList<Coupon> getCustomerCoupons() throws Exception {
        CouponDAO couponDAO = new CouponDAOImpl();
        ArrayList<Coupon> coupons = couponDAO.getAllByCustomer(customerID);
        return coupons;
    }

    public ArrayList<Coupon> getCustomerCategoryCoupons(int categoryID) throws Exception {
        CouponDAO couponDAO = new CouponDAOImpl();
        ArrayList<Coupon> coupons = couponDAO.getAllByCustomerCategory(customerID,categoryID);
        return coupons;
    }

    public ArrayList<Coupon> getCustomerMaxPriceCoupons(double maxPrice) throws Exception {
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        ArrayList<Coupon> coupons = couponDAO.getAllByCustomerMaxPrice(customerID, maxPrice);
        return coupons;

    }

    public Customer getCustomerDetails() throws SQLException, InterruptedException, CouponSystemException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.getOne(customerID);
        return customer;
    }


}
