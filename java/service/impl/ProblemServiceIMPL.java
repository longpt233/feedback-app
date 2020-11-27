package service.impl;

import dao.ProblemDao;
import dao.impl.ProblemDaoIMPL;
import model.Problem;
import service.ProblemService;

import java.sql.SQLException;
import java.util.List;

public class ProblemServiceIMPL implements ProblemService {
    private ProblemDao problemDao = new ProblemDaoIMPL();


    @Override
    public List<Problem> findAll() throws SQLException {
        return problemDao.findAll();
    }

    @Override
    public Problem findById(int id) throws SQLException {
        if (id>0){
            return problemDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(Problem problem) throws SQLException, ClassNotFoundException {
        // trả về true nếu insert thành công
        if(problemDao.insert(problem) != null) return true;
        return false;
    }

    @Override
    public boolean update(Problem problem) throws SQLException {
        return problemDao.update(problem);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return (id > 0) ? problemDao.delete(id) : false;
    }
}
