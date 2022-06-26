package utils;

import Facades.AdminFacade;
import Facades.ClientFacade;
import Facades.CompanyFacade;
import Facades.CustomerFacade;
import db.ConnectionPool;
import exceptions.CouponSystemException;

import java.sql.SQLException;

public class LoginManager {
    private static LoginManager instance = null;

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws SQLException, InterruptedException, CouponSystemException {
        if (clientType == ClientType.Administrator) {
            AdminFacade clientFacade = new AdminFacade();
            if (clientFacade.login(email, password)) {

                return clientFacade;
            }
        } else if (clientType == ClientType.Company) {
            CompanyFacade clientFacade = new CompanyFacade();
            if (clientFacade.login(email, password)) {
                return clientFacade;
            }
        } else if (clientType == ClientType.Customer) {
            CustomerFacade clientFacade = new CustomerFacade();
            if (clientFacade.login(email, password)) {
                return clientFacade;
            }
        }
        return null;
    }


}

