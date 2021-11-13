package back_end.bo.custom.impl;

import back_end.bo.custom.CompanyBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.CompanyDAO;
import back_end.dto.CompanyDTO;
import back_end.entity.Company;

public class CompanyBOImpl implements CompanyBO {
    CompanyDAO dao = (CompanyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.COMPANY);

    @Override
    public CompanyDTO getCompany() throws Exception {
        Company company = dao.get();
        return new CompanyDTO(company.getName(), company.getAddress(), company.getPhoneNumber(), company.getEmail(), company.getWebSite(), company.getBrn());
    }
}
