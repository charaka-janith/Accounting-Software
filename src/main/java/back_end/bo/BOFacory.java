package back_end.bo;

import back_end.bo.custom.impl.CustomerBOImpl;

public class BOFacory {

    private static BOFacory boFactory;

    private BOFacory() {
    }

    public static BOFacory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFacory();
        }
        return boFactory;
    }

    public enum BOTypes {

        CUSTOMER, ITEM, PO;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;

        }
    }

}
