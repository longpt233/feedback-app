package service.impl;

import dao.GroupHasLetterDao;
import dao.GroupLetterDao;
import dao.LetterDao;
import dao.impl.GroupHasLetterDaoIMPL;
import dao.impl.GroupLetterDaoIMPL;
import dao.impl.LetterDaoIMPL;
import model.GroupHasLetter;
import model.GroupLetter;
import model.Letter;
import service.GroupLetterService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupLetterServiceIMPL implements GroupLetterService {
    private GroupLetterDao groupLetterDao = new GroupLetterDaoIMPL();
    private LetterDao letterDao = new LetterDaoIMPL();
    private GroupHasLetterDao groupHasLetterDao = new GroupHasLetterDaoIMPL();

    @Override
    public boolean insertListLetter(ArrayList<String> listId, String groupName) throws SQLException {
        int quantity = listId.size();
        int status = letterDao.findById(listId.get(0)).getStatusLetter();
        int id=1;
//        String name="";

        GroupLetter groupLetter = new GroupLetter(id, groupName, status, quantity);
        System.out.println(groupLetter);
        groupLetterDao.insert(groupLetter.getName(), groupLetter.getStatus(), groupLetter.getQuantity());
        for (int i = 0; i < quantity; i++) {
            groupHasLetterDao.insert(new GroupHasLetter(listId.get(i), id));
        }

        return false;
    }

    @Override
    public boolean deleteListLetter(ArrayList<String> listId) {
        return false;
    }

    @Override
    public boolean updateStatusGroup(GroupLetter groupLetter, int status) throws SQLException {
        int id = groupLetter.getId();
        groupLetter.setStatus(status);
        groupLetterDao.update(groupLetter);

        List<GroupHasLetter> groupHasLetterList = groupHasLetterDao.findByIds(id);
        ArrayList<Letter> listLetter = new ArrayList<>();
        for (GroupHasLetter ghl: groupHasLetterList) {
            listLetter.add(letterDao.findById(ghl.getId_letter()));
        }
        for (Letter l: listLetter) {
            l.setStatusLetter(status);
            letterDao.update(l);
        }
        return true;
    }

    @Override
    public boolean addNewGroup(ArrayList<Letter> listLetter, String name)  {
        try {
            int quantity = listLetter.size();
            int status = letterDao.findById(listLetter.get(0).getId()).getStatusLetter();
            groupLetterDao.insert( name, status, quantity);
            GroupLetter groupLetter = groupLetterDao.findByName(name);
            int id_group = groupLetter.getId();
            for (int i = 0; i < quantity; i++) {
                GroupHasLetter groupHasLetter =new GroupHasLetter(listLetter.get(i).getId(), id_group);
                groupHasLetterDao.insert(groupHasLetter);
            }
            return true;

        } catch (SQLException e){
            System.out.println("loi roi");
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ArrayList<Letter> detailGroup(GroupLetter groupLetter) throws SQLException {
        int id = groupLetter.getId();
        List<GroupHasLetter> groupHasLetterList = groupHasLetterDao.findByIds(id);
        ArrayList<Letter> listLetter = new ArrayList<>();
        for (GroupHasLetter ghl: groupHasLetterList) {
            listLetter.add(letterDao.findById(ghl.getId_letter()));
        }
        return listLetter;
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
