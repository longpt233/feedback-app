package dao.impl;

import connection.InitConn;
import dao.LetterDao;
import model.LetterModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LetterDaoIMPL implements LetterDao {
    @Override
    public List findAll() throws SQLException {
        String sql= "SELECT * FROM letter";
        List<LetterModel> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            InitConn initConn=new InitConn();
            connection = initConn.getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new LetterModel(resultSet));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }

        return results.isEmpty() ? null : results;
    }

    @Override
    public Object findById(int id) throws SQLException {
        String sql= "SELECT * FROM letter WHERE id=?";
        List<LetterModel> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            InitConn initConn=new InitConn();
            connection = initConn.getConn();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new LetterModel(resultSet));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Object insert(Object o) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Object o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<LetterModel> sortById(int id) throws SQLException {
        return null;
    }
}
