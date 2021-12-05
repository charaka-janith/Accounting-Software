package back_end.dao.custom;

import back_end.dao.CrudDAO;
import back_end.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User, String> {
    public ArrayList<User> getAllAdmins() throws SQLException, ClassNotFoundException;
}
