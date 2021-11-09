package front_end.sessions;

import back_end.dto.UserDTO;
import javafx.application.Platform;
import javafx.scene.layout.Region;

import static front_end.anim.Theme.errorThread;

public class Session {
    private static UserDTO user;
    private static boolean sinhala;

    public static boolean isSinhala() {
        return sinhala;
    }

    public static void setSinhala(boolean language) {
        sinhala = language;
    }

    public static UserDTO getUser() {
        return user;
    }

    public static void setUser(UserDTO user) {
        Session.user = user;
    }
}
