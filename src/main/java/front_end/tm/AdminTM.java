package front_end.tm;

import com.jfoenix.controls.JFXButton;

public class AdminTM {
    private String username;
    private JFXButton btn_delete;

    public AdminTM() {
    }

    public AdminTM(String username, JFXButton btn_delete) {
        this.username = username;
        this.btn_delete = btn_delete;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JFXButton getBtn_delete() {
        return btn_delete;
    }

    public void setBtn_delete(JFXButton btn_delete) {
        this.btn_delete = btn_delete;
    }
}
