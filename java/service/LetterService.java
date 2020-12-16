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

    List<Letter> findByApplyDate(Date date1, Date date2) throws SQLException;
    List<Letter> findByApplicantName(String name) throws SQLException;

    List<Letter> findByStatus(int status) throws SQLException;
    public boolean delete(String id) throws SQLException;
    List<Letter> searchBy(String id, String category, String problem, int idApplicant, String content,String organization, java.sql.Date applyDate1, java.sql.Date applyDate2, int statusLetter) throws SQLException;

}
