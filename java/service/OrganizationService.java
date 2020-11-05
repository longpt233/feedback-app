package service;

import model.Organization;

import java.sql.SQLException;
import java.util.List;

public interface OrganizationService {
    List<Organization> findAll() throws SQLException;
    Organization findById(int id) throws SQLException;
    boolean insert(Organization organization) throws SQLException, ClassNotFoundException;
    boolean update(Organization organization) throws SQLException;
    boolean delete(int id) throws SQLException;
}
