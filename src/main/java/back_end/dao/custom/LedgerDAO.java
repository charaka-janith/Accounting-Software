package back_end.dao.custom;

import back_end.dao.CrudDAO;
import back_end.entity.Ledger;

import java.sql.SQLException;

public interface LedgerDAO extends CrudDAO<Ledger, Integer> {
    public Ledger search_byName (String name) throws SQLException, ClassNotFoundException;
}
