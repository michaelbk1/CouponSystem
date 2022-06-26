package Facades;

import beans.Category;
import beans.Company;
import beans.Coupon;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade extends  ClientFacade {
    private int companyID;

    public CompanyFacade() {
    }

    @Override
    public boolean login(String email, String password) throws InterruptedException, SQLException {
        CompanyDAO companyDAO = (CompanyDAO) new CompanyDAOImpl();

        if ( !companyDAO.isCompanyExists(email, password)) {
            return false;
        }
        Company company = companyDAO.getByEmail(email);
        companyID = company.getId();
        System.out.println("@@@@ LOGIN @@@@" + companyID);
        return true;
    }
    public void addCoupon(Coupon newCoupon) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ addCoupon  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        Coupon coupon = couponDAO.findByCompanyTitle(newCoupon.getCompanyID(), newCoupon.getTitle());

        if (coupon != null) {
            throw new CouponSystemException(ErrMsg.COMPANY_COUPON_EXIST); //throw new CouponException("Company", "ADD", "Coupon exists");
        }

        couponDAO.add(newCoupon);

    }
    public void updateCoupon(Coupon updatedCoupon) throws Exception {
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        Coupon coupon = couponDAO.getOne(updatedCoupon.getId());
        if (coupon == null) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXIST);
        } else if (coupon.getCompanyID() != updatedCoupon.getCompanyID()) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_MATCH);
        }
        couponDAO.update(updatedCoupon.getId(), updatedCoupon);
    }
    public void deleteCoupon(int couponID) throws Exception {
        CouponDAO couponDAO =  new CouponDAOImpl();
        Coupon coupon = couponDAO.getOne(couponID);
        if (coupon.getId() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXIST);
        }
        couponDAO.delete(couponID);
    }
    public ArrayList<Coupon> getCompanyCoupons() throws SQLException, InterruptedException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ getCompanyCoupons  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CouponDAO couponDAO = new CouponDAOImpl();
        ArrayList<Coupon> allCoupons = couponDAO.getAllByCompany( companyID);
        return allCoupons;
    }
    public ArrayList<Coupon> getCompanyCouponsCategory(int categoryID) throws SQLException, InterruptedException {
        CouponDAO couponDAO = new CouponDAOImpl();
        ArrayList<Coupon> allCoupons = couponDAO.getAllByCompanyCategory(companyID, categoryID);
        return allCoupons;
    }
    public ArrayList<Coupon> getCompanyCouponsMaxPrice(double maxPrice) throws SQLException, InterruptedException {
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        ArrayList<Coupon> allCoupons = couponDAO.getAllByCompanyMaxPrice(companyID, maxPrice);
        return allCoupons;
    }
    public Company getCompanyDetails() throws SQLException, InterruptedException {
        CompanyDAO companyDAO =  new CompanyDAOImpl();
        Company company = companyDAO.getOne(companyID);
        return company;
    }
}
