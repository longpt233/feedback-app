package map;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMap implements Mapable<User> {

    @Override
    public User mapRow(ResultSet rs) {
        try{
            User example= new User();
            example.setName(rs.getString("username"));
            return example;
        }catch (SQLException e){
            return null;
        }
    }
}
