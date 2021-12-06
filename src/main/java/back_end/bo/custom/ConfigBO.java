package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.ConfigDTO;
import back_end.dto.UserDTO;
import back_end.entity.Config;

import java.sql.SQLException;

public interface ConfigBO extends SuperBO {
    public ConfigDTO searchConfig(int id) throws SQLException, ClassNotFoundException;
    public boolean updateConfig(ConfigDTO config) throws SQLException, ClassNotFoundException;
}
