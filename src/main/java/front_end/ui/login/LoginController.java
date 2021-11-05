/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package front_end.ui.login;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import front_end.anim.Theme;
import front_end.sessions.DataHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Stage stage;
    private final String window_name = "Welcome !";

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
        setColors();
    }

    private void setColors() {
        /*try {
            JsonParser parser = new JsonParser();
            JsonElement parse = parser.parse(new FileReader(Theme.class.getResource("theme.json").getFile()));
            Theme.colorBG = parse.getAsJsonObject().get("color_BG").getAsString();
            Theme.color1 = parse.getAsJsonObject().get("color_1").getAsString();
        } catch (FileNotFoundException e) {
      Theme.giveAWarning(lblMain, e.getMessage(), "Welcome !");
        }
        Theme.setBackgroundColor("BG", pane);
        Theme.setBackgroundColor("1", lblMain);
        Theme.setTextFill("BG", lblMain);
        Theme.setTextFill("1"
                , lblRL
                , lblTabMode
                , btnDesktopMode
                , lblUserName
                , lblPassword
                , linkForgetPassword
                , btnSignIn
        );
        btnDesktopMode.setToggleColor(Paint.valueOf("white"));
        btnDesktopMode.setUnToggleColor(Paint.valueOf("white"));
        btnDesktopMode.setToggleLineColor(Paint.valueOf(Theme.color1));
        btnDesktopMode.setUnToggleLineColor(Paint.valueOf(Theme.color1));
        Theme.setTextFill("Warning", btnExit);
        Theme.setBorderColor("1", btnSignIn);
        Theme.setBorderColor("Warning", btnExit);*/
    }
}
