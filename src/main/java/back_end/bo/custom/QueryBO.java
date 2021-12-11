package back_end.bo.custom;

import back_end.bo.SuperBO;
import back_end.dto.CashBookDTO;
import back_end.dto.LedgerDTO;
import back_end.dto.UserDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface QueryBO extends SuperBO {
    public ArrayList<CashBookDTO> get_Cashbook(LocalDate start, LocalDate end) throws SQLException, ClassNotFoundException;

    public ArrayList<LedgerDTO> get_Ledger(LocalDate start, LocalDate end, String ledger_name) throws SQLException, ClassNotFoundException;
}
