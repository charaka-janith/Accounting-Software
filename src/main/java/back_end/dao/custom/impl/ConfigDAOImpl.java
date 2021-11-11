package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.ConfigDAO;
import back_end.entity.Config;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConfigDAOImpl implements ConfigDAO {
    @Override
    public boolean add(Config config) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public boolean update(Config config) throws Exception {
        return CrudUtil.executeUpdate("UPDATE config SET language=? WHERE id=?", config.getLanguage(), config.getId());
    }

    @Override
    public Config search(Integer id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM config WHERE id=?", id);
        Config config = null;
        while (rst.next()) {
            config = new Config(rst.getInt(1), rst.getString(2));
        }
        return config;
    }

    @Override
    public ArrayList<Config> getAll() throws Exception {
        return null;
    }
}
