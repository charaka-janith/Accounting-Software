package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.ReceiptDAO;
import back_end.entity.Receipt;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiptDAOImpl implements ReceiptDAO {
    @Override
    public boolean add(Receipt receipt) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Receipt(Number, Date, Description, Amount) VALUES(?,?,?,?)",
                receipt.getNumber(),
                receipt.getDate(),
                receipt.getDescription(),
                receipt.getAmount()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Receipt receipt) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Receipt search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Receipt> getAll() {
        return null;
    }
}
