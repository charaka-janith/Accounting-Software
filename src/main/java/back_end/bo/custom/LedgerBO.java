package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.LedgerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LedgerBO extends SuperBO {
    public boolean addLedger (LedgerDTO ledger) throws SQLException, ClassNotFoundException;
    public LedgerDTO search_byName(String name) throws SQLException, ClassNotFoundException;
    public LedgerDTO search_byId(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<LedgerDTO> getAll () throws SQLException, ClassNotFoundException;
}
