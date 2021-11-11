package front_end.ui.login;

import back_end.bo.BOFacory;
import back_end.bo.custom.ConfigBO;
import back_end.bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import front_end.anim.PaneOpenAnim;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Stage stage;
    UserBO userBO = (UserBO) BOFacory.getInstance().getBO(BOFacory.BOTypes.USER);
    ConfigBO configBO = (ConfigBO) BOFacory.getInstance().getBO(BOFacory.BOTypes.CONFIG);

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_login;

    @FXML
    private Label lbl_login;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_pass;

    @FXML
    private Label lbl_shortcuts;

    @FXML
    private Label lbl_userName;

    @FXML
    private Label lbl_welcome;

    @FXML
    private AnchorPane pane;

    @FXML
    private Region region_back;

    @FXML
    private Region region_front;

    @FXML
    private Region region_ui;

    @FXML
    private JFXToggleButton toggleBtn_language;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private TextField txt_userName;

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

    private void login() {
        try {
            Session.setUser(userBO.searchUser(txt_userName.getText()));
        } catch (NullPointerException e) {
            Session.setUser(null);
        } catch (Exception e) {
            Theme.giveAWarning(e.getMessage(), "", lbl_main, region_front);
        }
        if (null == Session.getUser()) {
            Platform.runLater(() -> {
                Theme.giveBorderWarning(txt_userName);
                Theme.giveBorderWarning(txt_pass);
                Theme.giveAWarning(Session.isSinhala() ? "පරිශීලක නාමය හෝ මුරපදය වලංගු නොවේ" : "Invalid Credentials", "", lbl_main, region_front);
            });
        } else {
            if (txt_pass.getText().equals(Session.getUser().getPassword())) {
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(AdminDashboardController.class.getResource("AdminDashboard.fxml")));
                    Scene scene = new Scene(root);
                    AdminDashboardController.stage = new Stage();
                    AdminDashboardController.stage.setScene(scene);
                    AdminDashboardController.stage.setMaximized(true);
                    AdminDashboardController.stage.setResizable(false);
                    AdminDashboardController.stage.initStyle(StageStyle.UNDECORATED);
                    AdminDashboardController.stage.show();
                    Theme.setShade(AdminDashboardController.stage);
                    stage.close();
                } catch (IOException e) {
                    Theme.giveAWarning(e.getMessage(), "", lbl_main, region_front);
                }
            } else {
                Theme.giveBorderWarning(txt_userName);
                Theme.giveBorderWarning(txt_pass);
                Theme.giveAWarning(Session.isSinhala() ? "පරිශීලක නාමය හෝ මුරපදය වලංගු නොවේ" : "Invalid Credentials", "", lbl_main, region_front);
            }
        }
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
        new Thread(() -> {
            try {
                Session.setSinhala(!Session.isSinhala());
                setLanguage();
            } catch (Exception e) {
                Theme.giveAWarning(e.getMessage(), "", lbl_main, region_front);
            }
        }).start();
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        lbl_welcome.setText("ආයුබෝවන් !");
                        lbl_userName.setText("පරිශීලක නාමය");
                        txt_userName.setPromptText("පරිශීලක නාමය ඇතුළත් කරන්න");
                        lbl_pass.setText("මුරපදය");
                        txt_pass.setPromptText("මුරපදය ඇතුළත් කරන්න");
                        btn_exit.setText("අවලංගු කරන්න");
                        btn_login.setText("පුරන්න");
                        lbl_shortcuts.setText("ඊළඟ = Enter / ආපසු = Esc / පිටවීම = F5");
                    });
                } catch (InterruptedException ignored) {
                }
            }).start();
            toggleBtn_language.setSelected(true);
        } else {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        lbl_welcome.setText("Welcome !");
                        lbl_userName.setText("User Name");
                        txt_userName.setPromptText("Enter User Name");
                        lbl_pass.setText("Password");
                        txt_pass.setPromptText("Enter Password");
                        btn_exit.setText("Exit");
                        btn_login.setText("Login");
                        lbl_shortcuts.setText("Next = Enter / Back = Esc / Exit = F5");
                    });
                } catch (InterruptedException ignored) {
                }
            }).start();
            toggleBtn_language.setSelected(false);
        }
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
        if (null != Session.getUser()) {
            txt_userName.setText(Session.getUser().getName());
            Session.setUser(null);
        }
        Theme.setChangeListeners(txt_userName, txt_pass);
        Theme.scale(pane, true);
        new RunLater(txt_userName);
        new PaneOpenAnim(pane);
        setFocusListeners();
        new Thread(() -> {
            try {
                Session.setSinhala(configBO.searchConfig(0).getLanguage().equals("sinhala"));
                setLanguage();
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
    }

    private void setFocusListeners() {
        txt_userName.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                txt_pass.setText("");
            }
        });
    }

    private void setColors() {
        new Thread(() -> {
            try {
                Theme.initialize();
                Platform.runLater(() -> {
                    Theme.setBackgroundColor("background", pane);
                    Theme.setBackgroundColor("success", region_back, btn_login);
                    Theme.setBackgroundColor("border", region_front);
                    Theme.setBackgroundColor("warning", btn_exit);
                    Theme.setTextFill("font", toggleBtn_language, lbl_userName, lbl_pass, lbl_shortcuts);
                    Theme.setTextFill("background", lbl_welcome, lbl_main, btn_login, btn_exit);
                    Theme.setTextFill("border", lbl_login);
                    Theme.setToggleColor("success", "background", "border", "font", toggleBtn_language);
                });
            } catch (Exception e) {
                Theme.giveAWarning(e.getMessage(), "", lbl_main, region_front);
            }
        }).start();
        /*
        btnDesktopMode.setToggleColor(Paint.valueOf("white"));
        btnDesktopMode.setUnToggleColor(Paint.valueOf("white"));
        btnDesktopMode.setToggleLineColor(Paint.valueOf(Theme.color1));
        btnDesktopMode.setUnToggleLineColor(Paint.valueOf(Theme.color1));*/
    }

    public static void backToLogin(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("Login.fxml")));
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.requestFocus();
        stage.show();
        Theme.setShade(stage);
        primaryStage.close();
    }
}
