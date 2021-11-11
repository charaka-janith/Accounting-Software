package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.ConfigDTO;
import back_end.dto.UserDTO;
import back_end.entity.Config;

public interface ConfigBO extends SuperBO {
    public ConfigDTO searchConfig(int id) throws Exception ;
    public boolean updateConfig(ConfigDTO config) throws Exception;
}
