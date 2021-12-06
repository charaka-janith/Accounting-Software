package back_end.bo.custom.impl;

import back_end.bo.custom.CompanyBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.CompanyDAO;
import back_end.dto.CompanyDTO;
import back_end.entity.Company;

import java.sql.SQLException;

public class CompanyBOImpl implements CompanyBO {
    CompanyDAO dao = (CompanyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.COMPANY);

    @Override
    public CompanyDTO getCompany() throws SQLException, ClassNotFoundException {
        Company company = dao.get();
        if (company == null) {
            return null;
        } else {
            return new CompanyDTO(
                    company.getName(),
                    company.getUserName(),
                    company.getAddress(),
                    company.getPhoneNumber(),
                    company.getEmail(),
                    company.getWebSite(),
                    company.getBrn()
            );
        }
    }

    @Override
    public boolean addCompany(CompanyDTO company) throws SQLException, ClassNotFoundException {
        return dao.add(new Company(
                company.getName(),
                company.getUserName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getWebSite(),
                company.getBrn()
        ));
    }

    @Override
    public boolean updateCompany(CompanyDTO company) throws SQLException, ClassNotFoundException {
        return dao.update(new Company(
                company.getName(),
                company.getUserName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getWebSite(),
                company.getBrn()
        ));
    }
}
