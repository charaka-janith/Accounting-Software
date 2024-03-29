package front_end.sessions;

import back_end.bo.BOFactory;
import back_end.bo.custom.ConfigBO;
import back_end.dto.ConfigDTO;
import back_end.dto.UserDTO;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class Session {
    public static Node imageSlider;
    private static UserDTO user;
    private static boolean sinhala = false;
    static ConfigBO bo = (ConfigBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CONFIG);
    // admin dashboard error inputs
    public static Label admin_mainLabel;
    public static Region admin_regionBack;
    public static Region admin_regionTop;
    public static Region admin_regionBottom;
    public static Region admin_regionLeft;
    public static Region admin_regionRight;
    // sub pane
    private static String current_subPane;

    public static boolean isSinhala() {
        return sinhala;
    }

    public static void setSinhala(boolean language) throws SQLException, ClassNotFoundException {
        sinhala = language;
        bo.updateConfig(language ? new ConfigDTO(0, "sinhala") : new ConfigDTO(0, "english"));
    }

    public static UserDTO getUser() {
        return user;
    }

    public static void setUser(UserDTO user) {
        Session.user = user;
    }

    public static String getCurrent_subPane() {
        return current_subPane;
    }

    public static void setCurrent_subPane(String current_subPane) {
        Session.current_subPane = current_subPane;
    }
}
