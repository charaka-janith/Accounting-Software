package back_end.dao.custom;

import back_end.dao.SuperDAO;
import back_end.entity.BankBook;
import back_end.entity.CashBook;
import back_end.entity.Ledger;
import back_end.entity.TrialBalance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<CashBook> get_cashBook(String start, String end) throws SQLException, ClassNotFoundException;
    public ArrayList<BankBook> get_bankBook(String start, String end) throws SQLException, ClassNotFoundException;

    public ArrayList<Ledger> getLedger(String start, String end, String ledger_name) throws SQLException, ClassNotFoundException;

    public ArrayList<TrialBalance> getTrialBalance(String start, String end) throws SQLException, ClassNotFoundException;
}
