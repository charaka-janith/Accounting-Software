package back_end.dao;

import back_end.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOFactoryTypes {
        USER, CONFIG, COLORS, COMPANY, RECEIPT, VOUCHER, LEDGER;
    }

    public SuperDAO getDAO(DAOFactoryTypes types) {
        return switch (types) {
            case USER -> new UserDAOImpl();
            case CONFIG -> new ConfigDAOImpl();
            case COLORS -> new ColorDAOImpl();
            case COMPANY -> new CompanyDAOImpl();
            case RECEIPT -> new ReceiptDAOImpl();
            case VOUCHER -> new VoucherDAOImpl();
            case LEDGER -> new LedgerDAOImpl();
        };

    }

}
