import Facades.CompanyFacade;
import Facades.CustomerFacade;
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
import utils.ART_UTIL;
import utils.ClientType;
import utils.LoginManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;

public class test {

    public static void main(String[] args) throws Exception {

        testAll();
    }

    public static void testAll() throws Exception {
        System.out.println(ART_UTIL.DBMANAGER);
        DatabaseManager.getInstance().dropCreateStrategy();

        System.out.println(LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator));
        System.out.println(ART_UTIL.CATEGORY);
        addCategories();
        //@@@@@@@@@@@@@@@@@@@
        System.out.println(ART_UTIL.CUSTOMER);
        addCustomers();
        updateCustomers();
        deleteCustomers();
        getAllCustomers();
        getCustomerByID();
        //@@@@@@@@@@@@@@@@@@@
        System.out.println(ART_UTIL.COMPANY);
        addCompanies();
        updateCompanies();
        deleteCompanies();
        getAllCompanies();
        getCompanyByID();
        //@@@@@@@@@@@@@@@@@@@
        System.out.println(ART_UTIL.COUPON);
        addCoupons();
        updateCoupons();
        deleteCoupons();
        getAllCoupons();
        getCouponById();
        addCouponPurchase();
        deleteCouponPurchase();
        testDeleteExpired();
        //@@@@@@@@@@@@@@@@@@@
        System.out.println(ART_UTIL.FACADE);
        testCompanyFacade();
        testCustomerFacade();

        Thread thread = new Thread(new DeleteExpired());
        thread.start();
        thread.interrupt();
    }


    public static void addCompanies() throws SQLException, InterruptedException, CouponSystemException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Company company1 = new Company("Company 1","comp1@gmail.com","1234");
        Company company2 = new Company("Company 2","comp2@gmail.com","1234");
        Company company3 = new Company("Company 3","comp3@gmail.com","1234");
        Company company4 = new Company("Company 4","comp4@gmail.com","1234");
        Company company5 = new Company("Company 5","comp5@gmail.com","1234");

        CompanyDAO companyDAO= (CompanyDAO) new CompanyDAOImpl();
        companyDAO.add(company1);
        companyDAO.add(company2);
        companyDAO.add(company3);
        companyDAO.add(company4);
        companyDAO.add(company5);

        companyDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void updateCompanies() throws SQLException, InterruptedException, CouponSystemException {
        CompanyDAO companyDAO= (CompanyDAO) new CompanyDAOImpl();


        Company company = new Company("Company1","comp1@gmail.com","12345");
        companyDAO.update(1,company);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ update Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        companyDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ update Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void deleteCompanies() throws SQLException, InterruptedException, CouponSystemException {

        CompanyDAO companyDAO= (CompanyDAO) new CompanyDAOImpl();
        companyDAO.delete(5);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ delete Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        companyDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ delete Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void getAllCompanies() throws SQLException, InterruptedException, CouponSystemException {
        CompanyDAO companyDAO =  new CompanyDAOImpl();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get all Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        companyDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get all Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void getCompanyByID() throws SQLException, InterruptedException, CouponSystemException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company  company  = companyDAO.getOne(2);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get one Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(company);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get one Companies @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static void addCustomers() throws CouponSystemException, SQLException, InterruptedException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Customer customer1 = new Customer("Customer"," c1","cust1@gmail.com","1234");
        Customer customer2 = new Customer("Customer"," c2","cust2@gmail.com","1234");
        Customer customer3 = new Customer("Customer"," c3","cust3@gmail.com","1234");
        Customer customer4 = new Customer("Customer"," c4","cust4@gmail.com","1234");
        Customer customer5 = new Customer("Customer"," c5","cust5@gmail.com","1234");

        CustomerDAO customerDAO= (CustomerDAO) new CustomerDAOImpl();
        customerDAO.add(customer1);
        customerDAO.add(customer2);
        customerDAO.add(customer3);
        customerDAO.add(customer4);
        customerDAO.add(customer5);

        customerDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ !Add Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void deleteCustomers() throws CouponSystemException, SQLException, InterruptedException {
        CustomerDAO customerDAO= (CustomerDAO) new CustomerDAOImpl();

        customerDAO.delete(5);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ delete Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        customerDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ !delete Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }
    public static void updateCustomers() throws CouponSystemException, SQLException, InterruptedException {
        CustomerDAO customerDAO= (CustomerDAO) new CustomerDAOImpl();

        Customer customer1 = new Customer("Kobi"," c1","cust1@gmail.com","1234");
        customerDAO.update(1,customer1);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ update Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        customerDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ !update Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }
    public static void getAllCustomers() throws SQLException, InterruptedException, CouponSystemException {
        CustomerDAO customerDAO = (CustomerDAO) new CustomerDAOImpl();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get all Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        customerDAO.getAll().forEach(System.out::println);
    }
    public static void getCustomerByID() throws SQLException, InterruptedException, CouponSystemException {
        CustomerDAO customerDAO = (CustomerDAO) new CustomerDAOImpl();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get one Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Customer customer = customerDAO.getOne(2);
        System.out.println(customer);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ get one Customer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static void addCategories() throws SQLException, InterruptedException, CouponSystemException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add Category @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        for (Category category : Category.values()) {
            categoryDAO.AddCategory(category);
        }
        categoryDAO.getAllCategories().forEach(System.out::println);
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static void addCoupons() throws CouponSystemException, SQLException, InterruptedException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add addCoupons @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDateStart = java.sql.Date.valueOf(date);
        java.sql.Date sqlDateEnd = java.sql.Date.valueOf(date.plusYears(1));

        Coupon coup1 = new Coupon(1, Category.Food, "Great Fast Foody", "10% Discount on all snacks", sqlDateStart, sqlDateEnd, 100, 10.5, "aaa");
        Coupon coup2 = new Coupon(1, Category.Restaurant, "Running Shoes", "Run faster & Jump higher", sqlDateStart, sqlDateEnd, 50, 100.25, "aaa");
        Coupon coup3 = new Coupon(3, Category.Food, "Magic 1", "Magic 1", sqlDateStart, sqlDateEnd, 50, 50, "aaa");
        Coupon coup4 = new Coupon(3, Category.Food, "Magic 2", "Magic 2", sqlDateStart, sqlDateEnd, 100, 20, "aaa");

        sqlDateStart = java.sql.Date.valueOf(date.minusDays(10));
        sqlDateEnd = java.sql.Date.valueOf(date.minusDays(2));

        Coupon coup5 = new Coupon(3, Category.Food, "Super drinks", "No thirst", sqlDateStart, sqlDateEnd, 1000, 32, "aaa");
        couponDAO.add(coup1);
        couponDAO.add(coup2);
        couponDAO.add(coup3);
        couponDAO.add(coup4);
        couponDAO.add(coup5);

        couponDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add addCoupons2 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }
    public static void updateCoupons() throws CouponSystemException, SQLException, InterruptedException {
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDateStart = java.sql.Date.valueOf(date);
        java.sql.Date sqlDateEnd = java.sql.Date.valueOf(date.plusYears(1));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ update Coupon @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //Coupon coup = new Coupon(1, Category.Food, "Food me", "Food me", sqlDateStart, sqlDateEnd, 77, 13, "aaa");
        Coupon coup = new Coupon(1, Category.Food, "Great Fast 2", "10% Discount on all snacks", sqlDateStart, sqlDateEnd, 100, 11, "aaa");
        couponDAO.update(2,coup);
        couponDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ update Coupon @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void deleteCoupons() throws CouponSystemException, SQLException, InterruptedException {
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        couponDAO.delete(5);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ delete Coupon @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        couponDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ delete Coupon2 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void getAllCoupons() throws SQLException, InterruptedException, CouponSystemException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ getAllCoupons  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        couponDAO.getAll().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ getAllCoupons  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void getCouponById() throws SQLException, InterruptedException, CouponSystemException {
        CouponDAO couponDAO = (CouponDAO) new CouponDAOImpl();
        Coupon coupon = couponDAO.getOne(3);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ getCouponById  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(coupon);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ getCouponById  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void addCouponPurchase() throws SQLException, InterruptedException, CouponSystemException {
        CouponDAO couponDAO = new CouponDAOImpl();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ addCouponPurchase  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        couponDAO.addCouponPurchase(1, 3);
        couponDAO.addCouponPurchase(2, 3);
        couponDAO.addCouponPurchase(2, 4);
        CustomerDAO customerDAO = new CustomerDAOImpl();
        System.out.println(customerDAO.getOne(2));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ addCouponPurchase2  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void deleteCouponPurchase() throws SQLException, InterruptedException, CouponSystemException {
        CouponDAO couponDAO = new CouponDAOImpl();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteCouponPurchase  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        couponDAO.deleteCouponPurchase(1, 3);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteCouponPurchase2  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void testDeleteExpired() throws CouponSystemException, SQLException, InterruptedException {
        CouponDAO couponDAO =  new CouponDAOImpl();
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testDeleteExpired  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        try {
            couponDAO.deleteExpired(sqlDate);
        } catch (SQLException | InterruptedException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testDeleteExpired2  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        getAllCoupons();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static void testCompanyFacade() throws Exception {
        CompanyFacade cf = new CompanyFacade();
        boolean login = cf.login("comp3@gmail.com", "1234");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCompanyFacade  login  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + login);
        cf.getCompanyCoupons().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCompanyFacade  2  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        cf.getCompanyCouponsMaxPrice(100).forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCompanyFacade  3  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        cf.getCompanyCouponsCategory(Category.Food.ordinal()).forEach(System.out::println);

        LocalDate date = LocalDate.now();
        java.sql.Date sqlDateStart = java.sql.Date.valueOf(date);
        java.sql.Date sqlDateEnd = java.sql.Date.valueOf(date.plusYears(1));
        Company company =  cf.getCompanyDetails();
        System.out.println(company);
        System.out.println( Category.Food.ordinal());
        Coupon coup1 = new Coupon(cf.getCompanyDetails().getId(), Category.Food, "AAA123", "AAA123", sqlDateStart, sqlDateEnd, 100, 70, "aaa");
        Coupon coup2 = new Coupon(cf.getCompanyDetails().getId(), Category.Electricity, "AAA222", "AAA222", sqlDateStart, sqlDateEnd, 90, 88, "aaa");

        CouponDAO couponDAO = new CouponDAOImpl();
        cf.addCoupon(coup1);
        try {
            cf.addCoupon(coup2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        cf.deleteCoupon(6);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCompanyFacade  9  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    public static void testCustomerFacade() throws Exception {
        CustomerFacade cf = new CustomerFacade();
        boolean login = cf.login("cust2@gmail.com", "1234");
        System.out.println("login: " + login);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  2  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CouponDAO couponDAO =  new CouponDAOImpl();
        couponDAO.getAll().forEach(System.out::println);
        Coupon coupon = couponDAO.getOne(2);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  3  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        try {
            cf.purchaseCoupon(coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  4  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        coupon = couponDAO.getOne(1);
        try {
            cf.purchaseCoupon(coupon);
        } catch (Exception e) {
            System.out.println(ART_UTIL.ERROR_MESSAGE);
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  5  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        coupon = couponDAO.getOne(2);
        try {
            cf.purchaseCoupon(coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  6  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        cf.getCustomerCoupons().forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  61  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        cf.getCustomerMaxPriceCoupons(50).forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  62  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        cf.getCustomerCategoryCoupons(Category.Food.ordinal()).forEach(System.out::println);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  63  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(cf.getCustomerDetails());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ testCustomerFacade  7  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

}



//
