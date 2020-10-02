package dao.impl;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {
    // interface thực hiện các công việc chung

    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    T insert(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
}
