package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetterModel {
    private int id;
    private String category;
    private int status;

    @Override
    public String toString() {
        return "LetterModel{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", status=" + status +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LetterModel(ResultSet rs) {
        try{
            id=rs.getInt("id");
            category=rs.getString("category");
            status=rs.getInt("status_letter");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
