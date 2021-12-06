package back_end.bo.custom.impl;

import back_end.bo.custom.UserBO;
import back_end.config.TrippleDes;
import back_end.dao.DAOFactory;
import back_end.dao.custom.UserDAO;
import back_end.dto.UserDTO;
import back_end.entity.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO dao = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.USER);

    @Override
    public boolean addUser(UserDTO user) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SQLException, ClassNotFoundException {
        return dao.add(new User(
                user.getName(),
                null != user.getPassword() ? new TrippleDes().encrypt(user.getPassword()) : null,
                user.getType()
        ));
    }

    @Override
    public boolean deleteUser(String userName) throws SQLException, ClassNotFoundException {
        return dao.delete(userName);
    }

    @Override
    public UserDTO searchUser(String userName) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        User user = dao.search(userName);
        return null == user ? null : new UserDTO(
                user.getUserName(),
                null != user.getPassword() ? new TrippleDes().decrypt(user.getPassword()) : null,
                user.getUserType()
        );
    }

    @Override
    public boolean updateUser(UserDTO user) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SQLException, ClassNotFoundException {
        return dao.update(new User(
                user.getName(),
                null != user.getPassword() ? new TrippleDes().encrypt(user.getPassword()) : null,
                user.getType()
        ));
    }

    @Override
    public ArrayList<UserDTO> getAllAdmins() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        ArrayList<User> userList = dao.getAllAdmins();
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        for (User user : userList) {
            allUsers.add(new UserDTO(
                    user.getUserName(),
                    null != user.getPassword() ? new TrippleDes().decrypt(user.getPassword()) : null,
                    user.getUserType()
            ));
        }
        return allUsers;
    }
}
