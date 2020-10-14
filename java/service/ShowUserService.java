package service;

import dao.impl.UserDaoIMPL;
import model.UserModel;

public class ShowUserService {
    private UserModel user;

    public ShowUserService(UserModel user) {
        this.user = user;
    }

    public boolean CheckExist(){
        UserDaoIMPL tmp=new UserDaoIMPL();
        if (tmp.findUser(user.getName())==null)
            return false;
        return true;
    }

    public void show(){

    }
}
