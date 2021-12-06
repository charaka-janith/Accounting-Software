package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.ColorDTO;
import back_end.dto.ConfigDTO;

import java.sql.SQLException;

public interface ColorBO extends SuperBO {
    public ColorDTO searchColor(String color) throws SQLException, ClassNotFoundException;
}
