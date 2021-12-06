package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.CompanyDTO;

import java.sql.SQLException;

public interface CompanyBO extends SuperBO {
    public CompanyDTO getCompany() throws SQLException, ClassNotFoundException;
    public boolean addCompany(CompanyDTO company) throws SQLException, ClassNotFoundException;
    public boolean updateCompany(CompanyDTO company) throws SQLException, ClassNotFoundException;
}
