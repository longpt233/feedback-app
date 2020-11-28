package service.impl;

import dao.LetterDao;
import dao.impl.LetterDaoIMPL;
import model.Letter;
import service.LetterService;

import java.sql.SQLException;
import java.util.List;

public class LetterServiceIMPL implements LetterService {
    public LetterServiceIMPL(){

    }
    private LetterDao letterDao = new LetterDaoIMPL();

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
        // trả về đối tượng đã tìm kiếm

        System.out.println("vao tim kiem tim "+id);
        System.out.println("tìm id 1 và ra id là "+letterDao.findById(id).toString());
        return letterDao.findById(id);
    }

    @Override
    public boolean insert(Letter letter) throws SQLException {
        // trả về true nếu insert thành công
        System.out.println("service recv"+letter.toString());
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
        // trả về true nếu xóa thành công
        return (id > 0) ? letterDao.delete(id) : false;
    }
}
