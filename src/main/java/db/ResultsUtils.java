package db;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import java.sql.Date;
import java.util.HashMap;


public class ResultsUtils {

    public static Company companyFromRow(HashMap<String, Object> map) {
        int id = (int) map.get("id");
        String name = (String) map.get("name");
        String email = (String) map.get("email");
        String password = (String) map.get("password");

        return new Company(id, name, email, password);
    }
    public static Category categoryFromRow(HashMap<String, Object> map) {

        String name = (String) map.get("name");

        return   Category.valueOf(name);
    }
    public static Customer customerFromRow(HashMap<String, Object> map) {
        int id = (int) map.get("id");
        String firstname = (String) map.get("first_name");
        String lastname = (String) map.get("last_name");
        String email = (String) map.get("email");
        String password = (String) map.get("password");

        return new Customer(id, firstname, lastname, email, password);
    }
    public static Coupon couponFromRow(HashMap<String, Object> map) {
        int id = (int) map.get("id");
        int companyID = (int) map.get("company_id");
        Category category = Category.values()[(int) map.get("category_id")];
        String title = (String) map.get("title");
        String description = (String) map.get("description");
        Date startDate = (Date) map.get("start_date");
        Date endDate = (Date) map.get("end_date");
        int amount = (int) map.get("amount");
        double price = (double) map.get("price");
        String image = (String) map.get("image");

        return new Coupon(id, companyID, category, title, description, startDate, endDate,amount,price,image);
    }
    public static boolean booleanFromRow(HashMap<String, Object> map) {
        long val = (long) map.get("res");
        return val==1;
    }


}