package dao.impl;

import dao.OrganizationDao;
import model.Organization;

import java.sql.SQLException;
import java.util.List;

public class OrganizationDaoIMPL implements OrganizationDao {
    @Override
    public List<Organization> findAll() throws SQLException {
        return null;
    }

    @Override
    public Organization findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Organization insert(Organization organization) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Organization organization) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
