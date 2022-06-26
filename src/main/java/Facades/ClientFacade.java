package Facades;

import dao.*;

public abstract class ClientFacade {
    protected CompanyDAO companyDAO =  new CompanyDAOImpl();
    protected CustomerDAO customerDAO = new CustomerDAOImpl();
    protected CouponDAO couponDAO = new CouponDAOImpl();

    public abstract boolean login (String email, String password) throws Exception;

}
