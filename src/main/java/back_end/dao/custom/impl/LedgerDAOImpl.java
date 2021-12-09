package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.LedgerDAO;
import back_end.dto.LedgerDTO;
import back_end.entity.Ledger;
import back_end.entity.User;
import com.sun.javafx.charts.Legend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LedgerDAOImpl implements LedgerDAO {
    @Override
    public boolean add(Ledger ledger) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Ledger(Name) VALUES(?)",
                ledger.getName()
        );
    }

    @Override
    public boolean delete(Integer s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Ledger ledger) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Ledger search(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Ledger WHERE Id=?",
                id
        );
        Ledger ledger = null;
        while (rst.next()) {
            ledger = new Ledger(
                    rst.getInt("Id"),
                    rst.getString("Name")
            );
        }
        return ledger;
    }

    @Override
    public ArrayList<Ledger> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Ledger");
        ArrayList<Ledger> ledgers = new ArrayList<>();
        while (rst.next()) {
            ledgers.add(new Ledger(
                    rst.getInt("Id"),
                    rst.getString("Name")
            ));
        }
        return ledgers;
    }

    @Override
    public Ledger search_byName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Ledger WHERE Name=?",
                name
        );
        Ledger ledger = null;
        while (rst.next()) {
            ledger = new Ledger(
                    rst.getInt("Id"),
                    rst.getString("Name")
            );
        }
        return ledger;
    }
}
