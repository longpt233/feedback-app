package service;

import dao.UserQuery;
import dao.impl.UserQueryIMPL;
import model.User;

public class ShowUser {
    private User user;

    public ShowUser(User user) {
        this.user = user;
    }

    public boolean CheckExist(){
        UserQueryIMPL tmp=new UserQueryIMPL();
        if (tmp.findUser(user.getName())==null)
            return false;
        return true;
    }
}
