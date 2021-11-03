package back_end.dao.custom;

import back_end.dao.CrudDAO;
import back_end.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    public String addUser();
}
