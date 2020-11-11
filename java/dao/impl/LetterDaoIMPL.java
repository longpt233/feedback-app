package dao.impl;

import connection.InitConnection;
import dao.LetterDao;
import model.Letter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LetterDaoIMPL implements LetterDao {
    private InitConnection initConnection = new InitConnection();

    @Override
    public List<Letter> findAll() throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false";

        List<Letter> results = new ArrayList<>();
        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("find!");
                int id= resultSet.getInt("id");
                String title=resultSet.getString("title");
                String content=resultSet.getString("content");
                int idApplicant= resultSet.getInt("id_applicant");
                Date applyDate=resultSet.getDate("apply_date");
                boolean deleted=resultSet.getBoolean("deleted");
                String category=resultSet.getString("category");
                int statusLetter=resultSet.getInt("status_letter");
                results.add(new Letter(id,title,content,idApplicant,applyDate,deleted,category,statusLetter));
            }
        } catch (SQLException e) {
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public Letter findById(int id) throws SQLException {
        String sql= "SELECT * FROM letter WHERE deleted=false and id=?";
        List<Letter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id2= resultSet.getInt("id");
                String title=resultSet.getString("title");
                String content=resultSet.getString("content");
                int idApplicant= resultSet.getInt("id_applicant");
                Date applyDate=resultSet.getDate("apply_date");
                boolean deleted=resultSet.getBoolean("deleted");
                String category=resultSet.getString("category");
                int statusLetter=resultSet.getInt("status_letter");
                results.add(new Letter(id2,title,content,idApplicant,applyDate,deleted,category,statusLetter));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Letter insert(Letter letter) throws SQLException {
        // trả về letter đã insert
//        Letter returnLetter = null;

        String sql = "INSERT INTO letter (title, content, id_applicant, apply_date, deleted, category) value (?,?,?,?,?,?)";
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setString(1, letter.getTitle());
        statement.setString(2, letter.getContent());
        statement.setInt(3, letter.getIdApplicant());
        statement.setDate(4, letter.getApplyDate());
        statement.setBoolean(5, letter.getDeleted());
        statement.setString(6, letter.getCategory());

        int isDone = statement.executeUpdate(); //  > 0 khi insert thành công
        System.out.println("isDone"+isDone);
        if (isDone > 0){
            return  letter;
//            ResultSet resultSet = statement.getGeneratedKeys(); // lấy ra id của bản ghi đã thêm vào
//            returnLetter = findById((int) resultSet.getLong(1)); // tìm kiếm lại dderr kiểm tra
        }else{
            return  null;
        }

    }

    @Override
    public boolean update(Letter letter) throws SQLException {
        String sql = "UPDATE letter set title=?, content=?, id_applicant=?, apply_date=?, deleted=? WHERE id=?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        statement.setString(1, letter.getTitle());
        statement.setString(2, letter.getContent());
        statement.setInt(3, letter.getIdApplicant());
        statement.setDate(4, letter.getApplyDate());
        statement.setBoolean(5, letter.getDeleted());
        statement.setInt(6, letter.getId());

        int isDone = statement.executeUpdate(); //  > 0 khi update thành công
        if (isDone > 0) return true;
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "update letter set deleted = true where id = ?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setInt(1, id);
        int isDone = statement.executeUpdate();
        if(isDone > 0) return true;
        return false;
    }

    @Override
    public List<Letter> sortById(int id) throws SQLException {
        return null;
    }
}
