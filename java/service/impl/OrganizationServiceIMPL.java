package service.impl;

import dao.OrganizationDao;
import dao.impl.OrganizationDaoIMPL;
import model.Organization;
import service.OrganizationService;

import java.sql.SQLException;
import java.util.List;

public class OrganizationServiceIMPL implements OrganizationService {
    private OrganizationDao organizationDao = new OrganizationDaoIMPL();

    @Override
    public List<Organization> findAll() throws SQLException {
        // trả về toàn bộ danh sách
        return organizationDao.findAll();
    }

    @Override
    public Organization findById(int id) throws SQLException {
        // trả về đối tượng đã tìm kiếm
        if (id>0){
            return organizationDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(Organization organization) throws SQLException {
        // trả về true nếu insert thành công
        if(organizationDao.insert(organization) != null) return true;
        return false;
    }

    @Override
    public boolean update(Organization organization) throws SQLException {
        // trả về true nếu cập nhật thành công
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return organizationDao.update(organization);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // trả về true nếu xóa thành công
        return (id > 0) ? organizationDao.delete(id) : false;
    }
}
