package dao;

import model.Problem;

import java.sql.SQLException;

public interface ProblemDao extends BaseDao<Problem> {
    Problem findByName(String name) throws SQLException;
}
