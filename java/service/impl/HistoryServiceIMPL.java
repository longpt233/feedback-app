package service.impl;

import dao.HistoryDao;
import dao.impl.HistoryDaoIMPL;
import model.History;
import service.HistoryService;

import java.sql.SQLException;
import java.util.List;

public class HistoryServiceIMPL implements HistoryService {
    private HistoryDao historyDao = new HistoryDaoIMPL();

    @Override
    public List<History> findAll() throws SQLException {
        // trả về toàn bộ danh sách
        return historyDao.findAll();
    }

    @Override
    public History findById(int id) throws SQLException {
        // trả về đối tượng đã tìm kiếm
        if (id>0){
            return historyDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(History history) throws SQLException {
        // trả về true nếu insert thành công
        if(historyDao.insert(history) != null) return true;
        return false;
    }

    @Override
    public boolean update(History history) throws SQLException {
        // trả về true nếu cập nhật thành công
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return historyDao.update(history);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // trả về true nếu xóa thành công
        return (id > 0) ? historyDao.delete(id) : false;
    }
}
