package service;

import model.Letter;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LetterService extends BaseService<Letter>{
    Letter findById(String id) throws SQLException;
    List<Letter> findByCategory(String category) throws SQLException;
    List<Letter> findByProblem(String problem) throws SQLException;
    Letter findByIDApplicant(int id) throws SQLException;
    List<Letter> findByApplyDate(Date date) throws SQLException;
    List<Letter> findByStatus(int status) throws SQLException;
    public boolean delete(String id) throws SQLException;
}
