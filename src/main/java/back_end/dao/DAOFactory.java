package back_end.dao;

import back_end.dao.custom.impl.ColorDAOImpl;
import back_end.dao.custom.impl.CompanyDAOImpl;
import back_end.dao.custom.impl.ConfigDAOImpl;
import back_end.dao.custom.impl.UserDAOImpl;

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
        USER, CONFIG, COLORS, COMPANY;
    }

    public SuperDAO getDAO(DAOFactoryTypes types) {
        return switch (types) {
            case USER -> new UserDAOImpl();
            case CONFIG -> new ConfigDAOImpl();
            case COLORS -> new ColorDAOImpl();
            case COMPANY -> new CompanyDAOImpl();
        };

    }

}
