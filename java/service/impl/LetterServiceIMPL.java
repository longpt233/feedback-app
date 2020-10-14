package service.impl;

import dao.impl.LetterDaoIMPL;
import model.LetterModel;
import service.LetterService;

import java.sql.SQLException;
import java.util.List;

public class LetterServiceIMPL implements LetterService {
    @Override
    public List<LetterModel> findAll() throws SQLException {

        return new LetterDaoIMPL().findAll();
    }

    @Override
    public LetterModel findById(int id) throws SQLException {
        if (id>0){
            return (LetterModel) new LetterDaoIMPL().findById(id);
        }
        return null;
    }

    @Override
    public LetterModel insert(LetterModel leter) throws SQLException {
        return null;
    }

    @Override
    public boolean update(LetterModel letter) throws SQLException {
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
