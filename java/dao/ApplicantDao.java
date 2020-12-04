package dao;

import model.Applicant;


import java.sql.SQLException;

public interface ApplicantDao extends BaseDao<Applicant> {
    Applicant findByName(String name) throws SQLException;
    Applicant findByIdentityCard(String identityCard) throws SQLException;
}
