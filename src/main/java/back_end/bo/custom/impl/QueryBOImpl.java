package back_end.bo.custom.impl;

import back_end.bo.custom.QueryBO;
import back_end.config.TrippleDes;
import back_end.dao.DAOFactory;
import back_end.dao.custom.QueryDAO;
import back_end.dto.BankBookDTO;
import back_end.dto.CashBookDTO;
import back_end.dto.LedgerDTO;
import back_end.dto.UserDTO;
import back_end.entity.BankBook;
import back_end.entity.CashBook;
import back_end.entity.Ledger;
import back_end.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryBOImpl implements QueryBO {
    private final QueryDAO dao = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.QUERY);

    @Override
    public ArrayList<CashBookDTO> get_Cashbook(LocalDate start, LocalDate end) throws SQLException, ClassNotFoundException {
        ArrayList<CashBook> dao_cashBook = dao.get_cashBook(start.toString(), end.toString());
        ArrayList<CashBookDTO> cashBook = new ArrayList<>();
        for (CashBook book : dao_cashBook) {
            cashBook.add(new CashBookDTO(
                    LocalDate.parse(book.getDate()),
                    book.getNumber(),
                    book.getDescription(),
                    book.getAmount(),
                    book.getLedger(),
                    book.getTable()
            ));
        }
        return cashBook;
    }

    @Override
    public ArrayList<BankBookDTO> get_Bankbook(LocalDate start, LocalDate end) throws SQLException, ClassNotFoundException {
        ArrayList<BankBook> dao_bankBook = dao.get_bankBook(start.toString(), end.toString());
        ArrayList<BankBookDTO> bankBook = new ArrayList<>();
        for (BankBook book : dao_bankBook) {
            bankBook.add(new BankBookDTO(
                    LocalDate.parse(book.getDate()),
                    book.getNumber(),
                    book.getDescription(),
                    book.getAmount(),
                    book.getLedger(),
                    book.getTable(),
                    book.getCheque()
            ));
        }
        return bankBook;
    }

    @Override
    public ArrayList<LedgerDTO> get_Ledger(LocalDate start, LocalDate end, String ledger_name) throws SQLException, ClassNotFoundException {
        ArrayList<Ledger> dao_ledger = dao.getLedger(start.toString(), end.toString(), ledger_name);
        ArrayList<LedgerDTO> ledger = new ArrayList<>();
        for (Ledger row : dao_ledger) {
            ledger.add(new LedgerDTO(
                    LocalDate.parse(row.getDate()),
                    row.getNumber(),
                    row.getDescription(),
                    row.getAmount(),
                    row.getTable()
            ));
        }
        return ledger;
    }
}
