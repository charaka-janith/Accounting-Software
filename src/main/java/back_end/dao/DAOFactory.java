package back_end.dao;

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

        USER;
    }

    public SuperDAO getDAO(DAOFactoryTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            default:
                return null;

        }

    }

}
