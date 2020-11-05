package service.impl;

import dao.UserDao;
import dao.impl.UserDaoIMPL;
import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceIMPL implements UserService {
    private UserDao userDao = new UserDaoIMPL();

    @Override
    public List<User> findAll() throws SQLException {
        // trả về toàn bộ danh sách
        return userDao.findAll();
    }

    @Override
    public User findById(int id) throws SQLException {
        // trả về đối tượng đã tìm kiếm
        if (id>0){
            return userDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(User user) throws SQLException {
        // trả về true nếu insert thành công
        if(userDao.insert(user) != null) return true;
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        // trả về true nếu cập nhật thành công
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return userDao.update(user);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // trả về true nếu xóa thành công
        return (id > 0) ? userDao.delete(id) : false;
    }
}
