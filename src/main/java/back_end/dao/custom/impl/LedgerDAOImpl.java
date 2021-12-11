package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.LedgerDAO;
import back_end.entity.Ledgers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LedgerDAOImpl implements LedgerDAO {
    @Override
    public boolean add(Ledgers ledgers) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Ledger(Name) VALUES(?)",
                ledgers.getName()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Ledger WHERE Id=?", id);
    }

    @Override
    public boolean update(Ledgers ledgers) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Ledgers search(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Ledger WHERE Id=?",
                id
        );
        Ledgers ledgers = null;
        while (rst.next()) {
            ledgers = new Ledgers(
                    rst.getInt("Id"),
                    rst.getString("Name")
            );
        }
        return ledgers;
    }

    @Override
    public ArrayList<Ledgers> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Ledger");
        ArrayList<Ledgers> ledgers = new ArrayList<>();
        while (rst.next()) {
            ledgers.add(new Ledgers(
                    rst.getInt("Id"),
                    rst.getString("Name")
            ));
        }
        return ledgers;
    }

    @Override
    public Ledgers search_byName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Ledger WHERE Name=?",
                name
        );
        Ledgers ledgers = null;
        while (rst.next()) {
            ledgers = new Ledgers(
                    rst.getInt("Id"),
                    rst.getString("Name")
            );
        }
        return ledgers;
    }
}
