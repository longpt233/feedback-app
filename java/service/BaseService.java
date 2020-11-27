package service;

import model.Letter;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T> {
    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    boolean insert(T t) throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
}
