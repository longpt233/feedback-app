package service;

import model.Letter;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LetterService extends BaseService<Letter>{
    Letter findById(String id) throws SQLException;
    Letter findByCategory(String category) throws SQLException;
    Letter findByProblem(String problem) throws SQLException;
    Letter findByIDApplicant(int id) throws SQLException;
    Letter findByApplyDate(Date date) throws SQLException;
    Letter findByStatus(int status) throws SQLException;
    public boolean delete(String id) throws SQLException;
}
