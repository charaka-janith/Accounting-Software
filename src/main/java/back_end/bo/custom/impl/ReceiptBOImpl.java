package back_end.bo.custom.impl;

import back_end.bo.custom.ReceiptBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.CompanyDAO;
import back_end.dao.custom.ReceiptDAO;
import back_end.dto.ReceiptDTO;
import back_end.entity.Receipt;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReceiptBOImpl implements ReceiptBO {
    private final ReceiptDAO dao = (ReceiptDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.RECEIPT);

    @Override
    public boolean addReceipt(ReceiptDTO receipt) throws SQLException, ClassNotFoundException {
        return dao.add(new Receipt(
                receipt.getNumber(),
                receipt.getDate().toString(),
                receipt.getDescription(),
                receipt.getAmount()
        ));
    }
}
