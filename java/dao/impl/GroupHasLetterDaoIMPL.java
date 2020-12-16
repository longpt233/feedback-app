package dao.impl;

import connection.InitConnection;
import dao.GroupHasLetterDao;
import dao.GroupLetterDao;
import dao.LetterDao;
import model.GroupHasLetter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupHasLetterDaoIMPL implements GroupHasLetterDao {
    private InitConnection initConnection = new InitConnection();
    private GroupLetterDao groupLetterDao = new GroupLetterDaoIMPL();
    private LetterDao letterDao = new LetterDaoIMPL();

    public GroupHasLetter getGroupHasLetter(ResultSet resultSet) throws SQLException {
        String id_letter = resultSet.getString("id_letter");
        int id_group = resultSet.getInt("id_group");

        return new GroupHasLetter(id_letter, id_group);
    }

    @Override
    public boolean insertListLetter(ArrayList<String> listId) {

        return false;
    }

    @Override
    public boolean deleteListLetter(ArrayList<String> listId) {
        return false;
    }

    @Override
    public List<GroupHasLetter> findByIds(int id) throws SQLException {
        String sql= "SELECT * FROM GroupHasLetter WHERE id_group=?";
        List<GroupHasLetter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getGroupHasLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public List<GroupHasLetter> findAll() throws SQLException {
        String sql= "SELECT * FROM GroupHasLetter";

        List<GroupHasLetter> results = new ArrayList<>();
        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getGroupHasLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public GroupHasLetter findById(int id) throws SQLException {
        String sql= "SELECT * FROM GroupHasLetter WHERE id=?";
        List<GroupHasLetter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getGroupHasLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public GroupHasLetter insert(GroupHasLetter groupHasLetter) throws SQLException {
        String sql = "INSERT INTO GroupHasLetter (id_letter, id_group) " +
                "value (?,?)";
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setString(1, groupHasLetter.getId_letter());
        statement.setInt(2, groupHasLetter.getId_group());
        int isDone = statement.executeUpdate(); //  > 0 khi insert thành công
        System.out.println("isDone"+isDone);
        if (isDone > 0){
            return  groupHasLetter;
        }else{
            return  null;
        }
    }

    @Override
    public boolean update(GroupHasLetter groupHasLetter) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "delete from GroupHasLetter where id = ?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setInt(1, id);
        int isDone = statement.executeUpdate();
        if(isDone > 0) return true;
        return false;
    }
}
