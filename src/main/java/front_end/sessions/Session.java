package front_end.sessions;

import back_end.bo.BOFacory;
import back_end.bo.custom.ConfigBO;
import back_end.dto.ConfigDTO;
import back_end.dto.UserDTO;
import javafx.application.Platform;
import javafx.scene.layout.Region;

import static front_end.anim.Theme.errorThread;

public class Session {
    private static UserDTO user;
    private static boolean sinhala = false;
   static ConfigBO bo = (ConfigBO) BOFacory.getInstance().getBO(BOFacory.BOTypes.CONFIG);

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
