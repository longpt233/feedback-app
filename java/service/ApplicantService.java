package service;

import model.Applicant;
import model.Applicant;

import java.sql.SQLException;
import java.util.List;

public interface ApplicantService {
    List<Applicant> findAll() throws SQLException;
    Applicant findById(int id) throws SQLException;
    boolean insert(Applicant applicant) throws SQLException, ClassNotFoundException;
    boolean update(Applicant applicant) throws SQLException;
    boolean delete(int id) throws SQLException;
}
