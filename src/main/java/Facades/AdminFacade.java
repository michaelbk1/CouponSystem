package Facades;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.*;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.sql.SQLException;
import java.util.ArrayList;


public class AdminFacade extends ClientFacade {
    @Override
    public boolean login (String email, String password) {

        if(email.equals("admin@admin.com") && password.equals("admin"))
        {
            System.out.println("T");
            return true;
        }
        System.out.println("F");
        return false;
    }

    public void addCompany(Company company) throws Exception {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        if (companyDAO.isCompanyExistByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_ALREADY_EXIST);
        } else if (companyDAO.isCompanyExistByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_ALREADY_EXIST);
        }
        companyDAO.add(company);
    }

    public void updateCompany(Company company) throws Exception {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company companyDb = companyDAO.getOne(company.getId());
        if (companyDb == null) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
        }
        if (companyDb.getName() != company.getName()) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_NOT_UPDATABLE);
        }
        if (companyDb.getEmail() != company.getEmail()) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_NOT_UPDATABLE);
        }

        companyDAO.update(company.getId(), company);
    }

    public void deleteCompany(int companyID) throws Exception {
        CompanyDAO companyDAO =  new CompanyDAOImpl();
        Company companyDb = companyDAO.getOne(companyID);
        if (companyDb.getId() == 0) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
        }
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        couponDAO.deleteCouponCompany(companyID);

        companyDAO.delete(companyID);

    }

    public ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException {
        CompanyDAO companyDAO =   new CompanyDAOImpl();
        return companyDAO.getAll();
    }

    public Company getOneCompany(int companyID) throws SQLException, InterruptedException, CouponSystemException {
        CompanyDAO companyDAO =  new CompanyDAOImpl();
        Company company = companyDAO.getOne(companyID);
        if (company == null) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
        }
        return company;
    }

    public void addCustomer(Customer customer) throws Exception {
        CustomerDAO customerDAO = (CustomerDAO) new CustomerDAOImpl();
        if (customerDAO.getByEmail(customer.getEmail()) != null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_ALREADY_EXIST);
        }
        customerDAO.add(customer);
    }

    public void updateCustomer(Customer customer) throws Exception {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        if (customerDAO.getOne(customer.getId()) == null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
        }
        customerDAO.update(customer.getId(), customer);

    }
    public void deleteCustomer(int customerID) throws Exception {
        CustomerDAO customerDAO =  new CustomerDAOImpl();
        if (customerDAO.getOne(customerID) == null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
        }
        customerDAO.delete(customerID);

    }

    public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException, CouponSystemException {
        CustomerDAO customerDAO = (CustomerDAO) new CustomerDAOImpl();
        return customerDAO.getAll();
    }

    public Customer getOneCustomer(Integer customerID) throws Exception {
        CustomerDAO customerDAO = (CustomerDAO) new CustomerDAOImpl();
        Customer customer = customerDAO.getOne(customerID);
        if (customer == null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
        }
        return customer;
    }
}
