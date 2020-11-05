package model;

import java.sql.ResultSet;

public class User {
    private int idApplicant;
    private String idCard;
    private String password;
    private boolean deleted;

    public User() {
    }

    public User(int resultSet) {
    }

    public User(int idApplicant, String idCard, String password, boolean deleted) {
        this.idApplicant = idApplicant;
        this.idCard = idCard;
        this.password = password;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "idApplicant=" + idApplicant +
                ", idCard='" + idCard + '\'' +
                ", password='" + password + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public int getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(int idApplicant) {
        this.idApplicant = idApplicant;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
