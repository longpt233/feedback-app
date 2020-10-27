package dao;

import model.Letter;

import java.sql.SQLException;
import java.util.List;

public interface LetterDao extends BaseDao<Letter> {
    // kế thừa những cái chung và có thể bổ sung thêm các tùy chọn
    List<Letter> sortById(int id) throws SQLException;
}
