package dao.impl;

import connection.InitConnection;
import dao.LetterDao;
import model.Letter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LetterDaoIMPL implements LetterDao {
    private InitConnection initConnection = new InitConnection();

    public Letter getLetter(ResultSet resultSet) throws SQLException {
        String id= resultSet.getString("id");
        String content=resultSet.getString("content");
        String idApplicant= resultSet.getString("id_card_applicant");
        Date applyDate=resultSet.getDate("apply_date");
        boolean deleted=resultSet.getBoolean("deleted");
        String category=resultSet.getString("category");
        int statusLetter=resultSet.getInt("status_letter");
        String problem=resultSet.getString("problem");
        String organization=resultSet.getString("organization");

        return new Letter(id,category,problem,idApplicant,content,organization,applyDate,statusLetter,deleted);
    }

    @Override
    public List<Letter> findAll() throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false";

        List<Letter> results = new ArrayList<>();
        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public Letter findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Letter findById(String id) throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false and id=?";
        List<Letter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setString(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Letter findByCategory(String category) throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false and category=?";
        List<Letter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setString(1,category);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }


    @Override
    public Letter findByProblem(String problem) throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false and problem=?";
        List<Letter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setString(1,problem);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Letter findByIDApplicant(int id) throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false and id_card_applicant=?";
        List<Letter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Letter findByApplyDate(java.util.Date date) throws SQLException {
        java.util.Date dNow = new java.util.Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd");

        System.out.println("Current Date: " + ft.format(dNow));
        return null;
    }

    @Override
    public Letter findWithApplyDate(java.util.Date date) throws SQLException {
        return null;
    }

    @Override
    public Letter findByStatus(int status) throws SQLException {
        String sql= "SELECT * FROM Letter WHERE deleted=false and status=?";
        List<Letter> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,status);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getLetter(resultSet));
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "update Letter set deleted = true where id = ?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setString(1, id);
        int isDone = statement.executeUpdate();
        if(isDone > 0) return true;
        return false;
    }

    @Override
    public Letter insert(Letter letter) throws SQLException {
        // trả về letter đã insert
//        Letter returnLetter = null;

        String sql = "INSERT INTO Letter (id, category, problem, id_card_applicant, content,organization, apply_date, status_letter, deleted) " +
                "value (?,?,?,?,?,?,?,?,?)";
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setString(1, letter.getId());
        statement.setString(2, letter.getCategory());
        statement.setString(3, letter.getProblem());
        statement.setString(4, letter.getIdApplicant());
        statement.setString(5, letter.getContent());
        statement.setString(6, letter.getOrganization());
        statement.setDate(7, letter.getApplyDate());
        statement.setInt(8, letter.getStatusLetter());
        statement.setBoolean(9, letter.getDeleted());

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
        String sql = "UPDATE Letter set problem=?, content=?, id_card_applicant=?,organization=?, apply_date=?, deleted=? WHERE id=?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        // sẽ thêm phần là nếu là null thì sẽ truyền vào giá trị default
        statement.setString(1, letter.getProblem());
        statement.setString(2, letter.getContent());
        statement.setString(3, letter.getIdApplicant());
        statement.setString(4, letter.getOrganization());
        statement.setDate(5, letter.getApplyDate());
        statement.setBoolean(6, letter.getDeleted());
        statement.setString(7, letter.getId());

        int isDone = statement.executeUpdate(); //  > 0 khi update thành công
        if (isDone > 0) return true;
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "update Letter set deleted = true where id = ?";
        PreparedStatement statement = initConnection.prepareUpdate(sql);
        statement.setInt(1, id);
        int isDone = statement.executeUpdate();
        if(isDone > 0) return true;
        return false;
    }

}
