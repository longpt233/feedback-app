package dao;

import model.GroupLetter;

import java.sql.SQLException;

public interface GroupLetterDao extends BaseDao<GroupLetter>{
    public boolean insert(String name,int status,int quantity) throws SQLException;
    GroupLetter findByName(String name) throws SQLException;
}
