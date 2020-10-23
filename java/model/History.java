package model;

import java.util.Date;

public class History {
    private int idApplicant;
    private int idLetter;
    private Date createdDate;
    private Date processedDate;
    private boolean deleted;

    @Override
    public String toString() {
        return "History{" +
                "idApplicant=" + idApplicant +
                ", idLetter=" + idLetter +
                ", createdDate=" + createdDate +
                ", processedDate=" + processedDate +
                ", statusLog=" + statusLog +
                ", deleted=" + deleted +
                '}';
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(int idApplicant) {
        this.idApplicant = idApplicant;
    }

    public int getIdLetter() {
        return idLetter;
    }

    public void setIdLetter(int idLetter) {
        this.idLetter = idLetter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public int getStatusLog() {
        return statusLog;
    }

    public void setStatusLog(int statusLog) {
        this.statusLog = statusLog;
    }

    private int statusLog;

}
