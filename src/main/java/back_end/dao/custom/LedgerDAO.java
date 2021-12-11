package back_end.dao.custom;

import back_end.dao.CrudDAO;
import back_end.entity.Ledgers;

import java.sql.SQLException;

public interface LedgerDAO extends CrudDAO<Ledgers, Integer> {
    public Ledgers search_byName(String name) throws SQLException, ClassNotFoundException;
}
