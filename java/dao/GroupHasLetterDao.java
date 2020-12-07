package dao;

import model.GroupHasLetter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GroupHasLetterDao extends BaseDao<GroupHasLetter> {
    boolean insertListLetter(ArrayList<String> listId);
    boolean deleteListLetter(ArrayList<String> listId);
    List<GroupHasLetter> findByIds(int id) throws SQLException;
}
