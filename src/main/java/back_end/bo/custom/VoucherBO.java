package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.ReceiptDTO;
import back_end.dto.VoucherDTO;

import java.sql.SQLException;

public interface VoucherBO extends SuperBO {
    public boolean addVoucher(VoucherDTO voucher) throws SQLException, ClassNotFoundException;
    public VoucherDTO searchVoucher (int number) throws SQLException, ClassNotFoundException;
    public boolean updateVoucher(VoucherDTO voucher) throws SQLException, ClassNotFoundException;
}
