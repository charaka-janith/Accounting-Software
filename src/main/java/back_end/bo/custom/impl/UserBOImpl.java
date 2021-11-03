package back_end.bo.custom.impl;

import back_end.bo.custom.UserBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.CustomerDAO;
import back_end.dto.UserDTO;

public class UserBOImpl implements UserBO {
    CustomerDAO dao = (CustomerDAO) DAOFactory.getInstace().getDAO(DAOFactory.DAOFactoryTypes.CUSTOMER);
    @Override
    public boolean addUser(UserDTO user) {
        return false;
    }
}
