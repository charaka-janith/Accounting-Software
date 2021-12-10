package back_end.bo.custom.impl;

import back_end.bo.custom.QueryBO;
import back_end.config.TrippleDes;
import back_end.dao.DAOFactory;
import back_end.dao.custom.QueryDAO;
import back_end.dto.CashBookDTO;
import back_end.dto.UserDTO;
import back_end.entity.CashBook;
import back_end.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryBOImpl implements QueryBO {
    private final QueryDAO dao = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.QUERY);

    @Override
    public ArrayList<CashBookDTO> get_Cashbook(LocalDate start, LocalDate end) throws SQLException, ClassNotFoundException {
        ArrayList<CashBook> dao_cashBook = dao.get_cashBook(start.toString(), end.toString());
        ArrayList<CashBookDTO> cashBook = new ArrayList<>();
        for (CashBook book : dao_cashBook) {
            cashBook.add(new CashBookDTO(
                    LocalDate.parse(book.getDate()),
                    book.getNumber(),
                    book.getDescription(),
                    book.getAmount(),
                    book.getLedger(),
                    book.getTable()
            ));
        }
        return cashBook;
    }
}
