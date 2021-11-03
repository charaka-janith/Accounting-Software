package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.UserDAO;
import back_end.entity.User;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User user) throws Exception {
        return CrudUtil.executeUpdate("insert into user values(?,?)", user.getName(), user.getPassword());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(User user) throws Exception {
        return false;
    }

    @Override
    public User search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        return null;
    }
}
