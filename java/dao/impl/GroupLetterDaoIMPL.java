package dao.impl;

import connection.InitConnection;
import dao.GroupLetterDao;
import model.GroupLetter;
import model.Letter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupLetterDaoIMPL implements GroupLetterDao {
    private InitConnection initConnection = new InitConnection();

    public GroupLetter getGroup(ResultSet resultSet) throws SQLException {
        int id= resultSet.getInt("id");
        String name = resultSet.getString("name");
        int status = resultSet.getInt("status");
        int quantity = resultSet.getInt("quantity");

        return new GroupLetter(id, name, status, quantity);
    }
    @Override
    public List<GroupLetter> findAll() throws SQLException {
        String sql= "SELECT * FROM GroupLetter";

        List<GroupLetter> results = new ArrayList<>();
        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getGroup(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }
        return results.isEmpty() ? null : results;
    }


    @Override
    public GroupLetter findById(int id) throws SQLException {
        String sql= "SELECT * FROM GroupLetter WHERE id=?";
        List<GroupLetter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getGroup(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public GroupLetter insert(GroupLetter groupLetter) throws SQLException {
        return null;
    }

    @Override
    public boolean insert(String name,int status,int quantity) throws SQLException {
        String sql = "INSERT INTO GroupLetter (name, status, quantity) " +
                "values (?,?,?)";
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setString(1, name);
        statement.setInt(2, status);
        statement.setInt(3, quantity);

        int isDone = statement.executeUpdate(); //  > 0 khi insert thành công
        System.out.println("isDone"+isDone);
        if (isDone > 0){
            return  true;
        }else{
            return  false;
        }
    }

    @Override
    public GroupLetter findByName(String name) throws SQLException {
        String sql= "SELECT * FROM GroupLetter WHERE name=?";
        List<GroupLetter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setString(1,name);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getGroup(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public boolean update(GroupLetter groupLetter) throws SQLException {
        String sql = "UPDATE Letter set name=?,status=?,quantity=? WHERE id=?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        statement.setString(1, groupLetter.getName());
        statement.setInt(2, groupLetter.getStatus());
        statement.setInt(3, groupLetter.getQuantity());
        statement.setInt(6, groupLetter.getId());

        int isDone = statement.executeUpdate(); //  > 0 khi update thành công
        if (isDone > 0) return true;
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "delete from GroupLetter where id = ?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setInt(1, id);
        int isDone = statement.executeUpdate();
        if(isDone > 0) return true;
        return false;
    }
}
