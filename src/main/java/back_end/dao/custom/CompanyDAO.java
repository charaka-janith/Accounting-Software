package back_end.dao.custom;

import back_end.dao.CrudDAO;
import back_end.entity.Company;

import java.sql.SQLException;

public interface CompanyDAO extends CrudDAO<Company, String> {
    public Company get() throws SQLException, ClassNotFoundException;
    public boolean updateCompany(Company company, String oldUsername) throws SQLException, ClassNotFoundException;
}
