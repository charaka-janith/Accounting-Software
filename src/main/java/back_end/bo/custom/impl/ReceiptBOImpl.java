package back_end.bo.custom.impl;

import back_end.bo.custom.ReceiptBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.ReceiptDAO;
import back_end.dto.ReceiptDTO;
import back_end.entity.Receipt;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReceiptBOImpl implements ReceiptBO {
    private final ReceiptDAO dao = (ReceiptDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.RECEIPT);

    @Override
    public boolean addReceipt(ReceiptDTO receipt) throws SQLException, ClassNotFoundException {
        return dao.add(new Receipt(
                receipt.getNumber(),
                receipt.getLedger(),
                receipt.getDate().toString(),
                receipt.getDescription(),
                receipt.getAmount()
        ));
    }

    @Override
    public ReceiptDTO searchReceipt(int number) throws SQLException, ClassNotFoundException {
        Receipt receipt = dao.search(number);
        return null != receipt ? new ReceiptDTO(
                receipt.getNumber(),
                receipt.getLedger(),
                LocalDate.parse(receipt.getDate()),
                receipt.getDescription(),
                receipt.getAmount()
        ) : null;
    }

    @Override
    public boolean updateReceipt(ReceiptDTO receipt) throws SQLException, ClassNotFoundException {
        return dao.update(new Receipt(
                receipt.getNumber(),
                receipt.getLedger(),
                receipt.getDate().toString(),
                receipt.getDescription(),
                receipt.getAmount()
        ));
    }
}
