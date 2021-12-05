package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.ConfigDAO;
import back_end.entity.Config;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConfigDAOImpl implements ConfigDAO {
    @Override
    public boolean add(Config config) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public boolean update(Config config) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Config SET Language=? WHERE Id=?",
                config.getLanguage(),
                config.getId()
        );
    }

    @Override
    public Config search(Integer id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Config WHERE Id=?",
                id
        );
        Config config = null;
        while (rst.next()) {
            config = new Config(
                    rst.getInt("Id"),
                    rst.getString("Language")
            );
        }
        return config;
    }

    @Override
    public ArrayList<Config> getAll() throws Exception {
        return null;
    }
}
