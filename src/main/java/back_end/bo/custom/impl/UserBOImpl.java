package back_end.bo.custom.impl;

import back_end.bo.custom.UserBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.UserDAO;
import back_end.dto.UserDTO;
import back_end.entity.User;

public class UserBOImpl implements UserBO {
    UserDAO dao = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.USER);
    @Override
    public boolean addUser(UserDTO user) throws Exception {
        return dao.add(new User(user.getName(),user.getPassword()));
    }

    @Override
    public UserDTO getUser() throws Exception {
        return null;
    }
}
