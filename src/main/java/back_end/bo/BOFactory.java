package back_end.bo;

import back_end.bo.custom.impl.ColorBOImpl;
import back_end.bo.custom.impl.CompanyBOImpl;
import back_end.bo.custom.impl.ConfigBOImpl;
import back_end.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        USER, CONFIG, COLOR, COMPANY;
    }

    public SuperBO getBO(BOTypes types) {
        return switch (types) {
            case USER -> new UserBOImpl();
            case CONFIG -> new ConfigBOImpl();
            case COLOR -> new ColorBOImpl();
            case COMPANY -> new CompanyBOImpl();
        };
    }

}
