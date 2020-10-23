package service.impl;

import dao.LetterDao;
import dao.impl.LetterDaoIMPL;
import model.LetterModel;
import service.LetterService;

import java.sql.SQLException;
import java.util.List;

public class LetterServiceIMPL implements LetterService {
    private LetterDao letterDao = new LetterDaoIMPL();

    @Override
    public List<LetterModel> findAll() throws SQLException {
        return letterDao.findAll();
    }

    @Override
    public LetterModel findById(int id) throws SQLException {
        if (id>0){
            return letterDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(LetterModel leter) throws SQLException {

        if(letterDao.insert(leter) != null) return true;
        return false;
    }

    @Override
    public boolean update(LetterModel letter) throws SQLException {
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return letterDao.update(letter);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return (id > 0) ? letterDao.delete(id) : false;
    }
}
