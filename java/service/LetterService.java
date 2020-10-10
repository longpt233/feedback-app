package service;

import model.Letter;

import java.sql.SQLException;
import java.util.List;

public interface LetterService {
    // có các chức năng mình cần
    // Xét quyền ở đây ( tại các hàm thì có kiểm tra điều kiện đầu vào để tiến hành vào DB hay k)

    List<Letter> findAll() throws SQLException;
    Letter findById(int id) throws SQLException;
    Letter insert() throws SQLException;
    boolean update(Letter letter) throws SQLException;
    boolean delete(int id) throws SQLException;
}
