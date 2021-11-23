package front_end.ui.admin;

import com.jfoenix.controls.JFXButton;
import front_end.sessions.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageAdminsController implements Initializable {

    @FXML
    private JFXButton btn_create;

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_save;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_userName;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tbl_manageAdmin;

    @FXML
    private TextField txt_userName;

    @FXML
    void txt_userName_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_refresh.requestFocus();
        }
    }

    @FXML
    void btn_create_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_userName.requestFocus();
        }
    }

    @FXML
    void btn_save_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_userName_onAction(ActionEvent event) {

    }

    @FXML
    void btn_create_onAction(ActionEvent event) {

    }

    @FXML
    void btn_save_onAction(ActionEvent event) {

    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {

    }

    public void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("පරිපාලක කළමනාකරණය කිරීම");
                lbl_userName.setText("පරිශීලක නාමය");
                txt_userName.setPromptText("පරිශීලක නාමය ඇතුළත් කරන්න");
                btn_create.setText("පරිපාලක නිර්මාණය කරන්න [F10]");
                btn_save.setText("සුරකින්න [F1]");
                btn_refresh.setText("නැවුම් කරන්න [F5]");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Manage Admins");
                lbl_userName.setText("Username");
                txt_userName.setPromptText("Enter Username");
                btn_create.setText("Create Admin [F10]");
                btn_save.setText("Save [F1]");
                btn_refresh.setText("Refresh [F5]");
            })).start();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        txt_userName.requestFocus();
    }
}
