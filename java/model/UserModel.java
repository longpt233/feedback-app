package model;

import java.sql.ResultSet;

public class UserModel {
    private int idApplicant;
    private int idCard;
    private String password;
    private String name;

    public UserModel() {
    }

    public UserModel(ResultSet resultSet) {
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "idApplicant=" + idApplicant +
                ", idCard=" + idCard +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }



    public int getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(int idApplicant) {
        this.idApplicant = idApplicant;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
