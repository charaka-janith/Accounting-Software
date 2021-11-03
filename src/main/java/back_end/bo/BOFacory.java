package back_end.bo;

import back_end.bo.custom.impl.UserBOImpl;

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

        USER;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case USER:
                return new UserBOImpl();
            default:
                return null;

        }
    }

}
