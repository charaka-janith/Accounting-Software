package back_end.bo.custom.impl;

import back_end.bo.custom.LedgerBO;
import back_end.config.TrippleDes;
import back_end.dao.DAOFactory;
import back_end.dao.custom.ColorDAO;
import back_end.dao.custom.LedgerDAO;
import back_end.dto.ConfigDTO;
import back_end.dto.LedgerDTO;
import back_end.dto.UserDTO;
import back_end.entity.Config;
import back_end.entity.Ledger;
import back_end.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class LedgerBOImpl implements LedgerBO {
    private final LedgerDAO dao = (LedgerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.LEDGER);

    @Override
    public boolean addLedger(LedgerDTO ledger) throws SQLException, ClassNotFoundException {
        return dao.add(new Ledger(
                ledger.getId(), ledger.getName()
        ));
    }

    @Override
    public LedgerDTO search_byName(String name) throws SQLException, ClassNotFoundException {
        Ledger ledger = dao.search_byName(name);
        return new LedgerDTO(ledger.getId(), ledger.getName());
    }

    @Override
    public LedgerDTO search_byId(int id) throws SQLException, ClassNotFoundException {
        Ledger ledger = dao.search(id);
        return new LedgerDTO(ledger.getId(), ledger.getName());
    }

    @Override
    public ArrayList<LedgerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Ledger> ledgerList = dao.getAll();
        ArrayList<LedgerDTO> allLedgers = new ArrayList<>();
        for (Ledger ledger : ledgerList) {
            allLedgers.add(new LedgerDTO(
                    ledger.getId(),
                    ledger.getName()
            ));
        }
        return allLedgers;
    }
}
