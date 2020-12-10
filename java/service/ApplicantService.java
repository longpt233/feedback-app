package service;

import model.Applicant;
import model.Applicant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ApplicantService extends BaseService<Applicant>{
    public Applicant findByIdentityCard(String identityCard) throws SQLException;
}
