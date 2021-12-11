package back_end.bo.custom.impl;

import back_end.bo.custom.LedgerBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.LedgerDAO;
import back_end.dto.LedgersDTO;
import back_end.entity.Ledgers;

import java.sql.SQLException;
import java.util.ArrayList;

public class LedgerBOImpl implements LedgerBO {
    private final LedgerDAO dao = (LedgerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.LEDGER);

    @Override
    public boolean addLedger(LedgersDTO ledger) throws SQLException, ClassNotFoundException {
        return dao.add(new Ledgers(
                ledger.getId(), ledger.getName()
        ));
    }

    @Override
    public LedgersDTO search_byName(String name) throws SQLException, ClassNotFoundException {
        Ledgers ledgers = dao.search_byName(name);
        return new LedgersDTO(ledgers.getId(), ledgers.getName());
    }

    @Override
    public LedgersDTO search_byId(int id) throws SQLException, ClassNotFoundException {
        Ledgers ledgers = dao.search(id);
        return new LedgersDTO(ledgers.getId(), ledgers.getName());
    }

    @Override
    public ArrayList<LedgersDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Ledgers> ledgersList = dao.getAll();
        ArrayList<LedgersDTO> allLedgers = new ArrayList<>();
        for (Ledgers ledgers : ledgersList) {
            allLedgers.add(new LedgersDTO(
                    ledgers.getId(),
                    ledgers.getName()
            ));
        }
        return allLedgers;
    }

    @Override
    public boolean delete_ledger(int id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }
}
