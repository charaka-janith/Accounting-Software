package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.ReceiptDAO;
import back_end.entity.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiptDAOImpl implements ReceiptDAO {
    @Override
    public boolean add(Receipt receipt) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Receipt(Number, Ledger, Date, Description, Amount, Cheque) VALUES(?,?,?,?,?,?)",
                receipt.getNumber(),
                receipt.getLedger(),
                receipt.getDate(),
                receipt.getDescription(),
                receipt.getAmount(),
                receipt.getCheque_number()
        );
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Receipt receipt) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "UPDATE Receipt SET Ledger=?,Date=?,Description=?,Amount=?,Cheque=? WHERE Number=?",
                receipt.getLedger(),
                receipt.getDate(),
                receipt.getDescription(),
                receipt.getAmount(),
                receipt.getNumber(),
                receipt.getCheque_number()
        );
    }

    @Override
    public Receipt search(Integer number) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Receipt WHERE Number=?",
                number
        );
        Receipt receipt = null;
        while (rst.next()) {
            receipt = new Receipt(
                    rst.getInt("Number"),
                    rst.getInt("Ledger"),
                    rst.getString("Date"),
                    rst.getString("Description"),
                    rst.getInt("Amount"),
                    rst.getInt("Cheque")
            );
        }
        return receipt;
    }

    @Override
    public ArrayList<Receipt> getAll() {
        return null;
    }
}
