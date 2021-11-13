package back_end.bo.custom.impl;

import back_end.bo.custom.UserBO;
import back_end.config.TrippleDes;
import back_end.dao.DAOFactory;
import back_end.dao.custom.UserDAO;
import back_end.dto.UserDTO;
import back_end.entity.User;

public class UserBOImpl implements UserBO {
    UserDAO dao = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.USER);
    @Override
    public boolean addUser(UserDTO user) throws Exception {
        return dao.add(new User(user.getName(), new TrippleDes().encrypt(user.getPassword()), user.getType()));
    }

    @Override
    public UserDTO searchUser(String userName) throws Exception {
        User user = dao.search(userName);
        return null == user ? null : new UserDTO(user.getUserName(), new TrippleDes().decrypt(user.getPassword()), user.getUserType());
    }
}
