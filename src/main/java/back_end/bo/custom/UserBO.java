package back_end.bo.custom;

import back_end.bo.SuperBO;
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

public interface UserBO extends SuperBO {
    public boolean addUser(UserDTO user) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SQLException, ClassNotFoundException;

    public boolean deleteUser (String userName) throws SQLException, ClassNotFoundException;

    public UserDTO searchUser(String userName) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

    public boolean updateUser(UserDTO user) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SQLException, ClassNotFoundException;

    public ArrayList<UserDTO> getAllAdmins() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
}
