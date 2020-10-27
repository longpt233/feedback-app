package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Letter {
    private int id;
    private String title;
    private String content;
    private int idApplicant;
    private Date applyDate;
    private boolean deleted;
    private String category;
    private int statusLetter;

    public Letter(){
    }

    public Letter(int id, String title, String content, int idApplicant, Date applyDate, boolean deleted, String category, int statusLetter) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.idApplicant = idApplicant;
        this.applyDate = applyDate;
        this.deleted = deleted;
        this.category = category;
        this.statusLetter = statusLetter;
    }

    @Override
    public String toString() {
        return "LetterModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", idApplicant=" + idApplicant +
                ", applyDate=" + applyDate +
                ", category='" + category + '\'' +
                ", statusLetter=" + statusLetter +
                ", deleted=" + deleted +
                '}';
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(int idApplicant) {
        this.idApplicant = idApplicant;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatusLetter() {
        return statusLetter;
    }

    public void setStatusLetter(int statusLetter) {
        this.statusLetter = statusLetter;
    }




}
