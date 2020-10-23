package model;

import java.util.Date;

public class Applicant {
    private int id;
    private String identityCard;
    private String name;
    private String phone;
    private Date birth;
    private int gender;
    private String address;
    private int role;

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", identityCard='" + identityCard + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", birth=" + birth +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", role=" + role +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}
