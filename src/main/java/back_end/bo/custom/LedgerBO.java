package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.LedgersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LedgerBO extends SuperBO {
    public boolean addLedger(LedgersDTO ledger) throws SQLException, ClassNotFoundException;

    public LedgersDTO search_byName(String name) throws SQLException, ClassNotFoundException;

    public LedgersDTO search_byId(int id) throws SQLException, ClassNotFoundException;

    public ArrayList<LedgersDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean delete_ledger(int id) throws SQLException, ClassNotFoundException;
}
