package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.UserDTO;

public interface UserBO extends SuperBO {
    public boolean addUser(UserDTO user) throws Exception;
}
