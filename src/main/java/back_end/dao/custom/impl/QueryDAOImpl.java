package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.DAOFactory;
import back_end.dao.custom.LedgerDAO;
import back_end.dao.custom.QueryDAO;
import back_end.dao.custom.UserDAO;
import back_end.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    private final LedgerDAO ledgerDAO = (LedgerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.LEDGER);

    @Override
    public ArrayList<CashBook> get_cashBook(String start, String end) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT Date, Number, Description, Amount, Name, tbl\n" +
                        "FROM (\n" +
                        "         SELECT Date, Number, Description, Amount, Ledger, 'receipt' as tbl\n" +
                        "         FROM Receipt WHERE (Date BETWEEN ? AND ?)\n" +
                        "         UNION ALL\n" +
                        "         SELECT Date, Number, Description, Amount, Ledger, 'voucher' as tbl\n" +
                        "         FROM Voucher WHERE (Date BETWEEN ? AND ?)\n" +
                        "     ) as RV, Ledger\n" +
                        "WHERE Ledger.Id = RV.Ledger\n" +
                        "ORDER BY Date, Number",
                start, end, start, end
        );
        ArrayList<CashBook> bookList = new ArrayList<>();
        while (rst.next()) {
            bookList.add(new CashBook(
                    rst.getString("Date"),
                    rst.getInt("Number"),
                    rst.getString("Description"),
                    rst.getInt("Amount"),
                    rst.getString("Name"),
                    rst.getString("tbl")
            ));
        }
        return bookList;
    }

    @Override
    public ArrayList<Ledger> getLedger(String start, String end, String ledger_name) throws SQLException, ClassNotFoundException {
        Ledgers ledgers = ledgerDAO.search_byName(ledger_name);
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT Date, Number, Description, Amount, tbl\n" +
                        "FROM (\n" +
                        "         SELECT Date, Number, Description, Amount, Ledger, 'receipt' as tbl\n" +
                        "         FROM Receipt WHERE (Date BETWEEN ? AND ?) && Ledger = ?\n" +
                        "         UNION ALL\n" +
                        "         SELECT Date, Number, Description, Amount, Ledger, 'voucher' as tbl\n" +
                        "         FROM Voucher WHERE (Date BETWEEN ? AND ?) && Ledger = ?\n" +
                        "     ) as RV\n" +
                        "ORDER BY Date, Number",
                start, end, ledgers.getId(), start, end, ledgers.getId()
        );
        ArrayList<Ledger> ledgerList = new ArrayList<>();
        while (rst.next()) {
            ledgerList.add(new Ledger(
                    rst.getString("Date"),
                    rst.getInt("Number"),
                    rst.getString("Description"),
                    rst.getInt("Amount"),
                    rst.getString("tbl")
            ));
        }
        return ledgerList;
    }
}
