package service.impl;

import model.GroupLetter;
import model.Letter;
import service.GroupLetterService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupLetterServiceIMPL implements GroupLetterService {
    @Override
    public boolean insertListLetter(ArrayList<String> listId) {
        return false;
    }

    @Override
    public boolean deleteListLetter(ArrayList<String> listId) {
        return false;
    }

    @Override
    public boolean updateStatusGroup(GroupLetter groupLetter, int status) {
        return false;
    }

    @Override
    public boolean addNewGroup(ArrayList<Letter> listLetter, String name) {
        return false;
    }

    @Override
    public ArrayList<Letter> detailGroup(GroupLetter groupLetter) {
        return null;
    }

    @Override
    public List<GroupLetter> findAll() throws SQLException {
        return null;
    }

    @Override
    public GroupLetter findById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean insert(GroupLetter groupLetter) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(GroupLetter groupLetter) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
