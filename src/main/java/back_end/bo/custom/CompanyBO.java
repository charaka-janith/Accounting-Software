package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.CompanyDTO;

public interface CompanyBO extends SuperBO {
    public CompanyDTO getCompany() throws Exception ;
}
