package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.ConfigDAO;
import back_end.entity.Config;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConfigDAOImpl implements ConfigDAO {
    @Override
    public boolean add(Config config) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Config config) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "UPDATE Config SET Language=? WHERE Id=?",
                config.getLanguage(),
                config.getId()
        );
    }

    @Override
    public Config search(Integer id) throws SQLException, ClassNotFoundException {
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
    public ArrayList<Config> getAll() {
        return null;
    }
}
