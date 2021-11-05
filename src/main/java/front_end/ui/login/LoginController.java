/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package front_end.ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Stage stage;

    @FXML // fx:id="btn_exit"
    private JFXButton btn_exit; // Value injected by FXMLLoader

    @FXML // fx:id="btn_login"
    private JFXButton btn_login; // Value injected by FXMLLoader

    @FXML // fx:id="lbl_pass"
    private Label lbl_pass; // Value injected by FXMLLoader

    @FXML // fx:id="lbl_shortcuts"
    private Label lbl_shortcuts; // Value injected by FXMLLoader

    @FXML // fx:id="lbl_userName"
    private Label lbl_userName; // Value injected by FXMLLoader

    @FXML // fx:id="lbl_welcome"
    private Label lbl_welcome; // Value injected by FXMLLoader

    @FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML // fx:id="toggleBtn_language"
    private JFXToggleButton toggleBtn_language; // Value injected by FXMLLoader

    @FXML // fx:id="txt_pass"
    private PasswordField txt_pass; // Value injected by FXMLLoader

    @FXML // fx:id="txt_userName"
    private TextField txt_userName; // Value injected by FXMLLoader

    @FXML
    void btn_exit_keyReleased(KeyEvent event) {

    }

    @FXML
    void btn_exit_onAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_login_keyReleased(KeyEvent event) {

    }

    @FXML
    void btn_login_onAction(ActionEvent event) {

    }

    @FXML
    void toggleBtn_language_keyReleased(KeyEvent event) {

    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {

    }

    @FXML
    void txt_pass_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_pass_onAction(ActionEvent event) {

    }

    @FXML
    void txt_userName_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_userName_onAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
