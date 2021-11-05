package front_end.sessions;

import back_end.dto.UserDTO;

public class Session {
    private static UserDTO user;
    private static boolean Sinhala;

    public static boolean isSinhala() {
        return Sinhala;
    }

    public static void setSinhala(boolean sinhala) {
        Sinhala = sinhala;
    }

    public static UserDTO getUser() {
        return user;
    }

    public static void setUser(UserDTO user) {
        Session.user = user;
    }
}
