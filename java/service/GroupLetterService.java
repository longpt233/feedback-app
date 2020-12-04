package service;

import model.GroupLetter;
import model.Letter;

import java.util.ArrayList;

public interface GroupLetterService extends BaseService<GroupLetter>{
    // liên quan đến groupHasLetter
    boolean insertListLetter(ArrayList<String> listId);
    boolean deleteListLetter(ArrayList<String> listId);
    
    // --------
    // liên quan đến Group
    boolean addNewGroup(ArrayList<Letter> listLetter, String name);
    boolean updateStatusGroup(GroupLetter groupLetter, int status);
    ArrayList<Letter> detailGroup(GroupLetter groupLetter);
}
