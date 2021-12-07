package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.CompanyDTO;
import back_end.dto.ReceiptDTO;

import java.sql.SQLException;
import java.text.ParseException;

public interface ReceiptBO extends SuperBO {
    public boolean addReceipt(ReceiptDTO receipt) throws ParseException, SQLException, ClassNotFoundException;
}
