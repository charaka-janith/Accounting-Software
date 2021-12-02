package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.ColorDAO;
import back_end.entity.Colors;
import back_end.entity.Config;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ColorDAOImpl implements ColorDAO {
    @Override
    public boolean add(Colors colors){
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Colors colors) throws Exception {
        return false;
    }

    @Override
    public Colors search(String color) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Colors WHERE Color=?",
                color
        );
        Colors colors = null;
        while (rst.next()) {
            colors = new Colors(
                    rst.getString("Color"),
                    rst.getString("ColorCode")
            );
        }
        return colors;
    }

    @Override
    public ArrayList<Colors> getAll() throws Exception {
        return null;
    }
}
