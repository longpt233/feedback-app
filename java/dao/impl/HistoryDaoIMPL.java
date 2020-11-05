package dao.impl;

import dao.HistoryDao;
import model.History;

import java.sql.SQLException;
import java.util.List;

public class HistoryDaoIMPL implements HistoryDao {
    @Override
    public List<History> findAll() throws SQLException {
        return null;
    }

    @Override
    public History findById(int id) throws SQLException {
        return null;
    }

    @Override
    public History insert(History history) throws SQLException {
        return null;
    }

    @Override
    public boolean update(History history) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
