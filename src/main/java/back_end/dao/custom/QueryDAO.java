package back_end.dao.custom;

import back_end.dao.SuperDAO;
import back_end.entity.CashBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<CashBook> get_cashBook(String start, String end) throws SQLException, ClassNotFoundException;
}
