package dao.impl;

import connection.InitConnection;
import dao.ProblemDao;
import model.Problem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDaoIMPL implements ProblemDao {
    private InitConnection initConnection = new InitConnection();
    
    @Override
    public List<Problem> findAll() throws SQLException {
        List<Problem> results = new ArrayList<>();
        String sql = "SELECT * FROM Problem";

        try {
            PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                results.add(new Problem(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public Problem findById(int id) throws SQLException {
        List<Problem> results = new ArrayList<>();
        String sql = "SELECT *FROM Problem WHERE id=?";

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new Problem(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Problem insert(Problem problem) throws SQLException {
        Problem problem1 = new Problem();

        String sql = "INSERT INTO Problem (name) value (?)";
        PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
        preparedStatement.setString(1, problem.getName());

        int isDone = preparedStatement.executeUpdate(); //  > 0 khi insert thành công
        if (isDone > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys(); // lấy ra id của bản ghi đã thêm vào
            problem1 = findById((int) resultSet.getLong(1)); // tìm kiếm lại dderr kiểm tra
        }

        return problem1;
    }

    @Override
    public boolean update(Problem problem) throws SQLException {
        String sql = "UPDATE Problem SET name=? WHERE id=?";
        PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
        preparedStatement.setString(1, problem.getName());
        preparedStatement.setInt(2, problem.getId());

        int isDone = preparedStatement.executeUpdate(); //  > 0 khi insert thành công
        if (isDone > 0) return true;
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
