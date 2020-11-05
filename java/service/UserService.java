package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> findAll() throws SQLException;
    User findById(int id) throws SQLException;
    boolean insert(User user) throws SQLException, ClassNotFoundException;
    boolean update(User user) throws SQLException;
    boolean delete(int id) throws SQLException;
}
