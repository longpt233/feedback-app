package dao;

import model.User;

public interface UserQuery extends Queryable<User> {
    User findUser(String user);
}
