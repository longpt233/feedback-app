package service.impl;

import dao.ApplicantDao;
import dao.ApplicantDao;
import dao.impl.ApplicantDaoIMPL;
import dao.impl.ApplicantDaoIMPL;
import model.Applicant;
import model.Applicant;
import service.ApplicantService;

import java.sql.SQLException;
import java.util.List;

public class ApplicantServiceIMPL implements ApplicantService {
    private ApplicantDao applicantDao = new ApplicantDaoIMPL();

    @Override
    public List<Applicant> findAll() throws SQLException {
        // trả về toàn bộ danh sách
        return applicantDao.findAll();
    }

    @Override
    public Applicant findById(int id) throws SQLException {
        // trả về đối tượng đã tìm kiếm
        if (id>0){
            return applicantDao.findById(id);
        }
        System.out.println("notification");
        return null;
    }

    @Override
    public boolean insert(Applicant applicant) throws SQLException {
        // trả về true nếu insert thành công
        if(applicantDao.insert(applicant) != null) return true;
        return false;
    }

    @Override
    public boolean update(Applicant applicant) throws SQLException {
        // trả về true nếu cập nhật thành công
        // xu li logic xem co dang dang nhap la admin khong thi moi cho xoa
        return applicantDao.update(applicant);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // trả về true nếu xóa thành công
        return (id > 0) ? applicantDao.delete(id) : false;
    }

    @Override
    public Applicant findByIdentityCard(String identityCard) throws SQLException {
        return applicantDao.findByIdentityCard(identityCard);
    }
}
