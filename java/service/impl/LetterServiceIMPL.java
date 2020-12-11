package service.impl;

import dao.LetterDao;
import dao.ProblemDao;
import dao.impl.LetterDaoIMPL;
import dao.impl.ProblemDaoIMPL;
import model.Letter;
import model.Problem;
import service.LetterService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class LetterServiceIMPL implements LetterService {
    public LetterServiceIMPL(){
    }

    private LetterDao letterDao = new LetterDaoIMPL();
    private ProblemDao problemDao = new ProblemDaoIMPL();

    @Override
    public List<Letter> findAll() throws SQLException {
        // trả về toàn bộ danh sách
        return letterDao.findAll();
    }

    @Override
    public Letter findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Letter findById(String id) throws SQLException {
        return letterDao.findById(id);
    }

    @Override
    public List<Letter> findByCategory(String category) throws SQLException {
        return letterDao.findByCategory(category);
    }


    @Override
    public List<Letter> findByProblem(String problem) throws SQLException {
        return letterDao.findByProblem(problem);
    }

    @Override
    public Letter findByIDApplicant(int id) throws SQLException {
        return letterDao.findByIDApplicant(id);
    }

    @Override
    public List<Letter> findByApplyDate(Date date) throws SQLException {
        return letterDao.findByApplyDate(date);
    }

    @Override
    public List<Letter> findByStatus(int status) throws SQLException {
        return letterDao.findByStatus(status);
    }

    @Override
    public boolean insert(Letter letter) throws SQLException {
        // trả về true nếu insert thành công
        System.out.println("service recv"+letter.toString());
        if (problemDao.findByName(letter.getProblem()) == null){
            Problem p = new Problem();
            p.setName(letter.getProblem());
            problemDao.insert(p);
        }
        if(letterDao.insert(letter) != null) return true;
        return false;
    }

    @Override
    public boolean update(Letter letter) throws SQLException {
        // trả về true nếu cập nhật thành công
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return letterDao.update(letter);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        // trả về true nếu xóa thành công
//        return (id > 0) ?
// xu li logic cho dau vao
              return  letterDao.delete(id) ;
    }
}




