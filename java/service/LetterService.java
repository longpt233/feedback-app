package service;

import model.LetterModel;

import java.sql.SQLException;
import java.util.List;

public interface LetterService {
    // có các chức năng mình cần
    // Xét quyền ở đây ( tại các hàm thì có kiểm tra điều kiện đầu vào để tiến hành vào DB hay k)

    List<LetterModel> findAll() throws SQLException;
    LetterModel findById(int id) throws SQLException;
    boolean insert(LetterModel leter) throws SQLException, ClassNotFoundException;
    boolean update(LetterModel letter) throws SQLException;
    boolean delete(int id) throws SQLException;
}
