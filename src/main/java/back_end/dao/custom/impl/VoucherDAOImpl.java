package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.VoucherDAO;
import back_end.entity.Receipt;
import back_end.entity.Voucher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoucherDAOImpl implements VoucherDAO {

    @Override
    public boolean add(Voucher voucher) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Voucher(Number, Ledger, Date, Description, Amount) VALUES(?,?,?,?,?)",
                voucher.getNumber(),
                voucher.getLedger(),
                voucher.getDate(),
                voucher.getDescription(),
                voucher.getAmount()
        );
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Voucher voucher) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "UPDATE Voucher SET Ledger=?,Date=?,Description=?,Amount=? WHERE Number=?",
                voucher.getLedger(),
                voucher.getDate(),
                voucher.getDescription(),
                voucher.getAmount(),
                voucher.getNumber()
        );
    }

    @Override
    public Voucher search(Integer number) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Voucher WHERE Number=?",
                number
        );
        Voucher voucher = null;
        while (rst.next()) {
            voucher = new Voucher(
                    rst.getInt("Number"),
                    rst.getInt("Ledger"),
                    rst.getString("Date"),
                    rst.getString("Description"),
                    rst.getInt("Amount")
            );
        }
        return voucher;
    }

    @Override
    public ArrayList<Voucher> getAll() {
        return null;
    }
}
