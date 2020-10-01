package dao.impl;

import dao.UserQuery;
import map.Mapable;
import map.UserMap;
import model.User;

import java.util.List;

public class UserQueryIMPL extends QueryIMPL<User> implements UserQuery {


    @Override
    public User findUser(String user) {
        String sql= "SELECT * FROM users AS u WHERE username = ? ";
        // mot query can co sql mau + maper+ cac tham so truyen vao query
        List<User> users = query(sql, new UserMap(), user);
        return users.isEmpty() ? null : users.get(0);
    }
}
