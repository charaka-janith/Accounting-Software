package front_end.ui.login;

import back_end.bo.BOFacory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import front_end.anim.PaneOpenAnim;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Stage stage;
    private String windowName;
    UserBO bo = (UserBO) BOFacory.getInstance().getBO(BOFacory.BOTypes.USER);

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

    @FXML // fx:id="lbl_main"
    private Label lbl_main; // Value injected by FXMLLoader

    @FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML // fx:id="toggleBtn_language"
    private JFXToggleButton toggleBtn_language; // Value injected by FXMLLoader

    @FXML // fx:id="txt_pass"
    private PasswordField txt_pass; // Value injected by FXMLLoader

    @FXML // fx:id="region_left"
    public Region region_left; // Value injected by FXMLLoader

    @FXML // fx:id="region_right"
    public Region region_right; // Value injected by FXMLLoader

    @FXML // fx:id="region_bottom"
    public Region region_bottom; // Value injected by FXMLLoader

    @FXML // fx:id="region_top"
    public Region region_top; // Value injected by FXMLLoader

    @FXML // fx:id="txt_userName"
    private TextField txt_userName; // Value injected by FXMLLoader

    @FXML
    void btn_exit_onAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_login_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_pass.requestFocus();
        }
    }

    @FXML
    void btn_login_onAction(ActionEvent event) {
        login();
    }

    @FXML
    void toggleBtn_language_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_exit.requestFocus();
        } else if (event.getCode().equals(KeyCode.ENTER)) {
            txt_userName.requestFocus();
        }
    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {
        Session.setSinhala(!Session.isSinhala());
        setLanguage();
    }

    @FXML
    void txt_pass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_userName.requestFocus();
        }
    }

    @FXML
    void txt_pass_onAction(ActionEvent event) {
        login();
    }

    @FXML
    void txt_userName_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            toggleBtn_language.requestFocus();
        }
    }

    @FXML
    void txt_userName_onAction(ActionEvent event) {
        txt_pass.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        Theme.scale(pane, false);
        Theme.setChangeListeners(txt_userName, txt_pass);
        new RunLater(txt_userName);
        new PaneOpenAnim(pane);
        setFocusListeners();
    }

    private void setLanguage() {
        if(Session.isSinhala()){
            lbl_userName.setText("පරිශීලක නාමය");
            txt_userName.setPromptText("පරිශීලක නාමය ඇතුළත් කරන්න");
            lbl_pass.setText("මුරපදය");
            txt_pass.setPromptText("මුරපදය ඇතුළත් කරන්න");
            btn_exit.setText("අවලංගු කරන්න");
            btn_login.setText("පුරන්න");
            lbl_shortcuts.setText("ඊළඟ = Enter / ආපසු = Esc / පිටවීම = F5");
            try {
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    windowName = "ආයුබෝවන්!";
                    lbl_main.setText(windowName);
                });
            } catch (InterruptedException ignored) {
            }
        }else{
            lbl_userName.setText("User Name");
            txt_userName.setPromptText("Enter User Name");
            lbl_pass.setText("Password");
            txt_pass.setPromptText("Enter Password");
            btn_exit.setText("Exit");
            btn_login.setText("Login");
            lbl_shortcuts.setText("Next = Enter / Back = Esc / Exit = F5");
            try {
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    windowName = "Welcome !";
                    lbl_main.setText(windowName);
                });
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void setColors() {
        try {
            JsonParser parser = new JsonParser();
            JsonElement parse = parser.parse(new FileReader(Objects.requireNonNull(Theme.class.getResource("theme.json")).getFile()));
            Theme.colorBG = parse.getAsJsonObject().get("color_BG").getAsString();
            Theme.color1 = parse.getAsJsonObject().get("color_1").getAsString();
            Theme.colorWarning = parse.getAsJsonObject().get("color_warning").getAsString();
        } catch (FileNotFoundException e) {
//            Theme.giveAWarning(pane, lbl_main, e.getMessage(), window_name);
        }
        /*Theme.setBackgroundColor("BG", pane);
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

    private void setFocusListeners() {
        txt_userName.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                checkUserId();
            }
        });
    }

    private void checkUserId() {
        String text = txt_userName.getText();
        txt_pass.setText("");
        if (text.equals("")) {
            Theme.giveBorderWarning(txt_userName);
            Theme.giveAWarning(Session.isSinhala() ? "sinhala error" : "Invalid Username", windowName, lbl_main, region_left, region_right, region_bottom, region_top);
            btn_login.setDisable(true);
        } else {
            new Thread(() -> {
                try {
                    Session.setUser(bo.searchUser(txt_userName.getText()));
                    btn_login.setDisable(false);
                    assert Session.getUser() != null;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lbl_main.setText(Session.isSinhala() ? "ආයුබෝවන් " + Session.getUser().getName() + " !" : "Welcome " + Session.getUser().getName() + " !");
                        }
                    });
                } catch (NullPointerException e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Theme.giveBorderWarning(txt_userName);
                            Theme.giveAWarning("Username not found", windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                            btn_login.setDisable(true);
                        }
                    });
                } catch (Exception e) {
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                }
            }).start();
        }
    }

    private void login() {
        try {
            UserDTO user = bo.searchUser(txt_userName.getText());
            System.out.println(user);
            Theme.giveAWarning("user logged in", windowName, lbl_main, region_left, region_right, region_bottom, region_top);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
