package back_end.bo.custom.impl;

import back_end.bo.custom.ColorBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.ColorDAO;
import back_end.dto.ColorDTO;
import back_end.entity.Colors;
import back_end.entity.Company;

import java.sql.SQLException;

public class ColorBOImpl implements ColorBO {
    ColorDAO dao = (ColorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.COLORS);

    @Override
    public ColorDTO searchColor(String color) throws SQLException, ClassNotFoundException {
        Colors colors = dao.search(color);
        return new ColorDTO(colors.getColor(), colors.getColorCode());
    }

    @Override
    public boolean updateColor(ColorDTO color) throws SQLException, ClassNotFoundException {
        return dao.update(new Colors(color.getColor(), color.getCode()));
    }
}
