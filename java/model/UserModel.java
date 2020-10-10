package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    private String name;

    public UserModel(ResultSet rs) {
        try{
            name=rs.getString("username");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public UserModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
