package back_end.bo.custom.impl;

import back_end.bo.custom.ConfigBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.ConfigDAO;
import back_end.dto.ConfigDTO;
import back_end.entity.Config;

import java.sql.SQLException;

public class ConfigBOImpl implements ConfigBO {
    ConfigDAO dao = (ConfigDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.CONFIG);

    @Override
    public ConfigDTO searchConfig(int id) throws SQLException, ClassNotFoundException {
        Config config = dao.search(id);
        return new ConfigDTO(config.getId(), config.getLanguage());
    }

    @Override
    public boolean updateConfig(ConfigDTO config) throws SQLException, ClassNotFoundException {
        return dao.update(new Config(config.getId(), config.getLanguage()));
    }
}
