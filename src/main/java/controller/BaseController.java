package controller;

import dal.dao.UserDAO;

public abstract class BaseController {

    protected UserDAO userDAO;

    public BaseController(UserDAO userDAO){
        this.userDAO = userDAO;
    }
}
