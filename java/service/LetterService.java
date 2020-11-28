package service;

import model.Letter;

import java.sql.SQLException;
import java.util.List;

public interface LetterService extends BaseService<Letter>{
    Letter findById(String id) throws SQLException;
    // có các chức năng mình cần
    // Xét quyền ở đây ( tại các hàm thì có kiểm tra điều kiện đầu vào để tiến hành vào DB hay k)

}
