package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.UserDAO;
import back_end.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "INSERT INTO User(UserName,Password,UserType) VALUES(?,?,?)",
                user.getUserName(),
                user.getPassword(),
                user.getUserType()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(User user) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE User SET Password=?,UserType=? WHERE UserName=?",
                user.getPassword(),
                user.getUserType(),
                user.getUserName()
        );
    }

    @Override
    public User search(String userName) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM User WHERE UserName=?",
                userName
        );
        User user = null;
        while (rst.next()) {
            user = new User(
                    rst.getString("UserName"),
                    rst.getString("Password"),
                    rst.getString("UserType")
            );
        }
        return user;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<User> getAllAdmins() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM User WHERE UserType = 'admin'");
        ArrayList<User> userList = new ArrayList<>();
        while (rst.next()) {
            userList.add(new User(
                    rst.getString("UserName"),
                    rst.getString("Password"),
                    rst.getString("UserType")
            ));
        }
        return userList;
    }
}
