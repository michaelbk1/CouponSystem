package db;

public class DatabaseManager {

    //Step 1 mutual object
    private static  DatabaseManager instance = new DatabaseManager();
    //private static  Bank instance = null;
    //Step2 ctor
    private DatabaseManager()
    {

    }

    //step 3
    public static DatabaseManager getInstance() {
        return instance;
    }

    private static final String QUERY_CREATE_SCHEMA = "create schema `CouponApp`";
    private static final String QUERY_DROP_SCHEMA = "drop schema `CouponApp`;";
    private static final String QUERY_CREATE_TABLE_CATEGORIES = "CREATE TABLE `CouponApp`.`categories` (\n" +
                    " `id` INT NOT NULL ,\n" +
                    " `name` VARCHAR(255) NOT NULL,\n" +
                    " PRIMARY KEY (`id`));\n" ;

    private static final String QUERY_CREATE_TABLE_COMPANIES =" CREATE TABLE `CouponApp`.`companies` (\n" +
            " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `name` varchar(50) NOT NULL ,\n" +
            " `email` varchar(50) NOT NULL ,\n" +
            " `password` varchar(30) NOT NULL ,\n" +
            " PRIMARY KEY (`id`));\n" ;

    private static final String QUERY_CREATE_TABLE_CUSTOMERS ="CREATE TABLE `CouponApp`.`customers` (\n" +
            "`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "`first_name` varchar(50) NOT NULL ,\n" +
            "`last_name` varchar(50) NOT NULL ,\n" +
            "`email` varchar(50) NOT NULL ,\n" +
            "`password` varchar(30) NOT NULL ,\n" +
            "PRIMARY KEY (`ID`));\n" ;


    private static final String QUERY_CREATE_TABLE_COUPONS ="  CREATE TABLE `CouponApp`.`coupons`  (\n" +
            "              `id` int  NOT NULL AUTO_INCREMENT, \n" +
            "              `company_id` int  NOT NULL , \n" +
            "              `category_id` int  NOT NULL , \n" +
            "              `title` varchar(50) NOT NULL , \n" +
            "              `description` varchar(5000) NOT NULL , \n" +
            "              `start_date` date NOT NULL , \n" +
            "              `end_date` date NOT NULL , \n" +
            "              `amount` int  NOT NULL , \n" +
            "              `price` double NOT NULL , \n" +
            "              `image` varchar(100) NOT NULL , \n" +
            "              PRIMARY KEY (`ID`) ,\n" +
            "               INDEX `company_id_idx` (`company_id` ASC) VISIBLE, \n" +
            "               INDEX `category_id_idx` (`category_id` ASC) VISIBLE,  \n" +
            "               CONSTRAINT `company_id` \n" +
            "               FOREIGN KEY (`company_id`) \n" +
            "               REFERENCES `couponapp`.`companies` (`id`) \n" +
            "               ON DELETE NO ACTION \n" +
            "               ON UPDATE NO ACTION, \n" +
            "               CONSTRAINT `category_id` \n" +
            "               FOREIGN KEY (`category_id`) \n" +
            "               REFERENCES `couponapp`.`categories` (`id`) \n" +
            "               ON DELETE NO ACTION \n" +
            "               ON UPDATE NO ACTION  \n" +
            "             )   ";

    private static final String QUERY_CREATE_TABLE_CUSTOMERS_VS_COUPONS ="CREATE TABLE `couponapp`.`customers_coupons` (\n" +
            "  `customer_id` INT NOT NULL,\n" +
            "  `coupon_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`customer_id`, `coupon_id`),\n" +
            "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\n" +
            "  CONSTRAINT `customer_id`\n" +
            "    FOREIGN KEY (`customer_id`)\n" +
            "    REFERENCES `couponapp`.`customers` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `coupon_id`\n" +
            "    FOREIGN KEY (`coupon_id`)\n" +
            "    REFERENCES `couponapp`.`coupons` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";





    public void dropCreateStrategy() {

        try {
            JDBCUtils.executeQuery(QUERY_DROP_SCHEMA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            JDBCUtils.executeQuery(QUERY_CREATE_SCHEMA);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CATEGORIES);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COMPANIES);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CUSTOMERS);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COUPONS);

            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CUSTOMERS_VS_COUPONS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}