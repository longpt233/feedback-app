package dao;

import model.Letter;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LetterDao extends BaseDao<Letter> {
    // kế thừa những cái chung và có thể bổ sung thêm các tùy chọn
    Letter findById(String id) throws SQLException;
    List<Letter> findByCategory(String category) throws SQLException;
    List<Letter> findByProblem(String problem) throws SQLException;
    Letter findByIDApplicant(int id) throws SQLException;
    List<Letter> findByApplyDate(Date date) throws SQLException;
    Letter findWithApplyDate(Date date) throws SQLException;
    List<Letter> findByStatus(int status) throws SQLException;
    public boolean delete(String id) throws SQLException;
    List<Letter> searchBy(String id, String category, String problem, int idApplicant, String content,String organization, java.util.Date applyDate, int statusLetter) throws SQLException;
}
