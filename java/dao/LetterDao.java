package dao;

import dao.BaseDao;
import model.LetterModel;

import java.sql.SQLException;
import java.util.List;

public interface LetterDao extends BaseDao {
    // kế thừa những cái chung và có thể bổ sung thêm các tùy chọn
    List<LetterModel> sortById(int id) throws SQLException;
}
