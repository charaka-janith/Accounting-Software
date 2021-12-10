package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.QueryDAO;
import back_end.entity.CashBook;
import back_end.entity.Company;
import back_end.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

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
}
