package back_end.dao;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstace() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOFactoryTypes {

        CUSTOMER, ITEM, ORDER, ORDERDETAILS,QUERY;
    }

    public SuperDAO getDAO(DAOFactoryTypes types) {
        switch (types) {
            case CUSTOMER:
//                return new CustomerDAOImpl();
            default:
                return null;

        }

    }

}
