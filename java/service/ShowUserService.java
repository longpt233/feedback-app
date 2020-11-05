package service;

import dao.impl.UserDaoIMPL;
import model.User;

public class ShowUserService {
    private User user;

    public ShowUserService(User user) {
        this.user = user;
    }

//    public boolean CheckExist(){
//        UserDaoIMPL tmp=new UserDaoIMPL();
//        if (tmp.findUser(user.getName())==null)
//            return false;
//        return true;
//    }

    public void show(){

    }
}
