package service;

import model.GroupLetter;
import model.Letter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupLetterService extends BaseService<GroupLetter>{
    // liên quan đến groupHasLetter
    boolean insertListLetter(ArrayList<String> listId, String groupName) throws SQLException;
    boolean deleteListLetter(ArrayList<String> listId);
    
    // --------
    // liên quan đến Group
    boolean addNewGroup(ArrayList<Letter> listLetter, String name) throws SQLException;
    boolean updateStatusGroup(GroupLetter groupLetter, int status) throws SQLException;
    ArrayList<Letter> detailGroup(GroupLetter groupLetter) throws SQLException;
}
