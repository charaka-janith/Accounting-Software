package back_end.bo.custom.impl;

import back_end.bo.custom.ReceiptBO;
import back_end.bo.custom.VoucherBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.ReceiptDAO;
import back_end.dao.custom.VoucherDAO;
import back_end.dto.ReceiptDTO;
import back_end.dto.VoucherDTO;
import back_end.entity.Receipt;
import back_end.entity.Voucher;

import java.sql.SQLException;
import java.time.LocalDate;

public class VoucherBOImpl implements VoucherBO {
    private final VoucherDAO dao = (VoucherDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.VOUCHER);

    @Override
    public boolean addVoucher(VoucherDTO voucher) throws SQLException, ClassNotFoundException {
        return dao.add(new Voucher(
                voucher.getNumber(),
                voucher.getLedger(),
                voucher.getDate().toString(),
                voucher.getDescription(),
                voucher.getAmount()
        ));
    }

    @Override
    public VoucherDTO searchVoucher(int number) throws SQLException, ClassNotFoundException {
        Voucher voucher = dao.search(number);
        return null != voucher ? new VoucherDTO(
                voucher.getNumber(),
                voucher.getLedger(),
                LocalDate.parse(voucher.getDate()),
                voucher.getDescription(),
                voucher.getAmount()
        ) : null;
    }

    @Override
    public boolean updateVoucher(VoucherDTO voucher) throws SQLException, ClassNotFoundException {
        return dao.update(new Voucher(
                voucher.getNumber(),
                voucher.getLedger(),
                voucher.getDate().toString(),
                voucher.getDescription(),
                voucher.getAmount()
        ));
    }
}
