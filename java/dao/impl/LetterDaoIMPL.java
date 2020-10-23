package dao.impl;

import connection.InitConnection;
import dao.LetterDao;
import model.LetterModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LetterDaoIMPL implements LetterDao {
    private InitConnection initConnection = new InitConnection();

    @Override
    public List<LetterModel> findAll() throws SQLException {
        String sql= "SELECT * FROM letter WHERE deleted=false";

        List<LetterModel> results = new ArrayList<>();
        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new LetterModel(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results;
    }

    @Override
    public LetterModel findById(int id) throws SQLException {
        String sql= "SELECT * FROM letter WHERE deleted=false and id=?";
        List<LetterModel> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new LetterModel(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public LetterModel insert(LetterModel letter) throws SQLException {
        // trả về letter đã insert
        LetterModel returnLetter = null;

        String sql = "INSERT INTO letter (title, content, id_applicant, applyDate, deleted) value (?,?,?,?,?)";
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setString(1, letter.getTitle());
        statement.setString(2, letter.getContent());
        statement.setInt(3, letter.getIdApplicant());
        statement.setDate(4, letter.getApplyDate());
        statement.setBoolean(5, letter.getDeleted());

        int isDone = statement.executeUpdate(); //  > 0 khi insert thành công
        if (isDone > 0){
            ResultSet resultSet = statement.getGeneratedKeys(); // lấy ra id của bản ghi đã thêm vào
            returnLetter = findById((int) resultSet.getLong(1)); // tìm kiếm lại dderr kiểm tra
        }

        return returnLetter;
    }

    @Override
    public boolean update(LetterModel letter) throws SQLException {
        String sql = "UPDATE letter set title=? set content=? set id_applicant=? set apply_date=? set deleted=? WHERE id=?";
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
    public List<LetterModel> sortById(int id) throws SQLException {
        return null;
    }
}
