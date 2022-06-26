package exceptions;

public enum ErrMsg {
    COMPANY_NAME_ALREADY_EXIST("Company name already exist..."),
    COMPANY_EMAIL_ALREADY_EXIST("Company email already exist..."),
    COMPANY_NOT_FOUND("Company not found..."),
    COMPANY_NAME_NOT_UPDATABLE("Company name not updatable..."),
    COMPANY_EMAIL_NOT_UPDATABLE("Company name not updatable..."),
    COMPANY_ID_NOT_MATCH("Company Id does not match ..."),
    COUPON_NOT_EXIST("Coupon not exist ..."),
    COMPANY_COUPON_EXIST("Coupon Exist for current Company..."),
    COUPON_PURCHASE_SOLD_OUT("Coupon Purchase - sold out..."),
    COUPON_PURCHASE_EXPIRED("Coupon Purchase - expired..."),
    COUPON_CUSTOMER_EXIST("Coupon exist for customer..."),
    CUSTOMER_EMAIL_ALREADY_EXIST("ADD Customer - email already exist..."),
    CUSTOMER_NOT_FOUND("UPDATE customer - customer not found..."),

    ;


    private String msg;
    ErrMsg(String msg)
    {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}


