package front_end.ui.settings;

import com.jfoenix.controls.JFXButton;
import front_end.sessions.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_save;

    @FXML
    private Label lbl_currentPass;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_newPass;

    @FXML
    private Label lbl_newPass2;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txt_currentPass;

    @FXML
    private TextField txt_newPass;

    @FXML
    private TextField txt_newPass2;

    //    ..........................................Key Events........................................

    @FXML
    void txt_currentPass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_currentPass.requestFocus();
        }
    }

    @FXML
    void txt_newPass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_currentPass.requestFocus();
        }
    }

    @FXML
    void txt_newPass2_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_newPass.requestFocus();
        }
    }

    @FXML
    void btn_save_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_newPass2.requestFocus();
        }
    }

    //    ..........................................Action Events........................................

    @FXML
    void txt_currentPass_onAction() {
        txt_newPass.requestFocus();
    }

    @FXML
    void txt_newPass_onAction() {
        txt_newPass2.requestFocus();
    }

    @FXML
    void txt_newPass2_onAction() {
//..........TODO-> Call Save password method
    }

    @FXML
    void btn_save_onAction() {
//..........TODO-> Call Save password method
    }

    @FXML
    void btn_refresh_onAction() {
        txt_currentPass.setText("");
        txt_newPass.setText("");
        txt_newPass2.setText("");
    }

    public void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("පරිශීලක මුරපදය වෙනස් කිරීම");
                lbl_currentPass.setText("වත්මන් මුර පදය");
                txt_currentPass.setPromptText("වත්මන් මුරපදය ඇතුළත් කරන්න");
                lbl_newPass.setText("නව මුරපදය");
                txt_newPass.setPromptText("නව මුරපදය ඇතුළත් කරන්න");
                lbl_newPass2.setText("නැවතත් නව මුරපදය");
                txt_newPass2.setPromptText("නැවත නව මුරපදය ඇතුළත් කරන්න");
                btn_save.setText("සුරකින්න");
                btn_refresh.setText("නැවුම් කරන්න");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Change User Password");
                lbl_currentPass.setText("Current Password");
                txt_currentPass.setPromptText("Enter Current Password");
                lbl_newPass.setText("New Password");
                txt_newPass.setPromptText("Enter New Password");
                lbl_newPass2.setText("Repeat New Password");
                txt_newPass2.setPromptText("Enter New Password Again");
                btn_save.setText("Save [F1]");
                btn_refresh.setText("Refresh [F5]");
            })).start();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        txt_currentPass.requestFocus();
    }
}
