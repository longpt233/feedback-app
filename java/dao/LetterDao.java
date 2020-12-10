package dao;

import model.Letter;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LetterDao extends BaseDao<Letter> {
    // kế thừa những cái chung và có thể bổ sung thêm các tùy chọn
    Letter findById(String id) throws SQLException;
    Letter findByCategory(String category) throws SQLException;
    Letter findByProblem(String problem) throws SQLException;
    Letter findByIDApplicant(int id) throws SQLException;
    Letter findByApplyDate(Date date) throws SQLException;
    Letter findWithApplyDate(Date date) throws SQLException;
    Letter findByStatus(int status) throws SQLException;
    public boolean delete(String id) throws SQLException;
}
