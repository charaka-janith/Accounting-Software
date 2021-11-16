package front_end.sessions;

import back_end.bo.BOFactory;
import back_end.bo.custom.ConfigBO;
import back_end.dto.ConfigDTO;
import back_end.dto.UserDTO;
import javafx.scene.Node;

public class Session {
    public static Node imageSlider;
    private static UserDTO user;
    private static boolean sinhala = false;
   static ConfigBO bo = (ConfigBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CONFIG);

    public static boolean isSinhala() {
        return sinhala;
    }

    public static void setSinhala(boolean language) throws Exception {
        sinhala = language;
        bo.updateConfig(language ? new ConfigDTO(0, "sinhala") : new ConfigDTO(0, "english"));
    }

    public static UserDTO getUser() {
        return user;
    }

    public static void setUser(UserDTO user) {
        Session.user = user;
    }
}
