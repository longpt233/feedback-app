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
        // trả về toàn bộ danh sách
        return letterDao.findAll();
    }

    @Override
    public LetterModel findById(int id) throws SQLException {
        // trả về đối tượng đã tìm kiếm
        if (id>0){
            return letterDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(LetterModel leter) throws SQLException {
        // trả về true nếu insert thành công
        if(letterDao.insert(leter) != null) return true;
        return false;
    }

    @Override
    public boolean update(LetterModel letter) throws SQLException {
        // trả về true nếu cập nhật thành công
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return letterDao.update(letter);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // trả về true nếu xóa thành công
        return (id > 0) ? letterDao.delete(id) : false;
    }
}
