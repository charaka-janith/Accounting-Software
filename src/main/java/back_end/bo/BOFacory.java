package back_end.bo;

import back_end.bo.custom.impl.ColorBOImpl;
import back_end.bo.custom.impl.ConfigBOImpl;
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
        USER, CONFIG, COLOR;
    }

    public SuperBO getBO(BOTypes types) {
        return switch (types) {
            case USER -> new UserBOImpl();
            case CONFIG -> new ConfigBOImpl();
            case COLOR -> new ColorBOImpl();
        };
    }

}
