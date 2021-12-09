package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.CompanyDTO;
import back_end.dto.LedgerDTO;
import back_end.dto.ReceiptDTO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface ReceiptBO extends SuperBO {
    public boolean addReceipt(ReceiptDTO receipt) throws SQLException, ClassNotFoundException;
    public ReceiptDTO searchReceipt (int number) throws SQLException, ClassNotFoundException;
    public boolean updateReceipt(ReceiptDTO receipt) throws SQLException, ClassNotFoundException;
}
