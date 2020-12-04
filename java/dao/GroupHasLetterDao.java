package dao;

import model.GroupHasLetter;

import java.util.ArrayList;

public interface GroupHasLetterDao extends BaseDao<GroupHasLetter> {
    boolean insertListLetter(ArrayList<String> listId);
    boolean deleteListLetter(ArrayList<String> listId);
}
