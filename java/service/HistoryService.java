package service;

import model.History;

import java.sql.SQLException;
import java.util.List;

public interface HistoryService {
    List<History> findAll() throws SQLException;
    History findById(int id) throws SQLException;
    boolean insert(History history) throws SQLException, ClassNotFoundException;
    boolean update(History history) throws SQLException;
    boolean delete(int id) throws SQLException;
}
