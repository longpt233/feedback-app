package dao.impl;

import connection.InitConnection;
import dao.ApplicantDao;
import model.Applicant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicantDaoIMPL implements ApplicantDao {
    private InitConnection initConnection = new InitConnection();

    @Override
    public List<Applicant> findAll() throws SQLException {
        String sql = "SELECT * FROM applicant";
        List<Applicant> results = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String identityCard = resultSet.getString("identity_card");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                Date birth = resultSet.getDate("birth");
                int gender = resultSet.getInt("gender");
                String address = resultSet.getString("address");
                int role = resultSet.getInt("role");
                results.add(new Applicant(id, identityCard, name, phone, birth, gender, address, role));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public Applicant findById(int id) throws SQLException {
        String sql= "SELECT * FROM applicant WHERE id=?";
        List<Applicant> results = new ArrayList<>();

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new Applicant());
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Applicant insert(Applicant applicant) throws SQLException {
        Applicant applicant1 = new Applicant();

        String sql = "INSERT INTO applicant (identity_card, name, phone, birth, gender, address, role) value (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
        preparedStatement.setString(1, applicant.getIdentityCard());
        preparedStatement.setString(2, applicant.getName());
        preparedStatement.setString(3, applicant.getPhone());
        preparedStatement.setDate(4, (java.sql.Date) applicant.getBirth());
        preparedStatement.setInt(5, applicant.getGender());
        preparedStatement.setString(6, applicant.getAddress());
        preparedStatement.setInt(7, applicant.getRole());

        int isDone = preparedStatement.executeUpdate(); //  > 0 khi insert thành công
        if (isDone > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys(); // lấy ra id của bản ghi đã thêm vào
            applicant1 = findById((int) resultSet.getLong(1)); // tìm kiếm lại dderr kiểm tra
        }

        return applicant1;
    }

    @Override
    public boolean update(Applicant applicant) throws SQLException {
        String sql = "UPDATE applicant SET identity_card=?, name=?, phone=?, birth=?, gender=?, address=?, role=? WHERE id=?";
        PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
        preparedStatement.setString(1, applicant.getIdentityCard());
        preparedStatement.setString(2, applicant.getName());
        preparedStatement.setString(3, applicant.getPhone());
        preparedStatement.setDate(4, (java.sql.Date) applicant.getBirth());
        preparedStatement.setInt(5, applicant.getGender());
        preparedStatement.setString(6, applicant.getAddress());
        preparedStatement.setInt(7, applicant.getRole());

        int isDone = preparedStatement.executeUpdate(); //  > 0 khi insert thành công
        if (isDone > 0) return true;
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
