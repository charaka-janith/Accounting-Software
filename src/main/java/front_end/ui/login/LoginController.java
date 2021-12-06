package front_end.ui.login;

import back_end.bo.BOFactory;
import back_end.bo.custom.ConfigBO;
import back_end.bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.PaneOpenAnim;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import front_end.ui.dashboard.CompanyDashboardController;
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
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginController implements Initializable {

    public static Stage stage;
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    ConfigBO configBO = (ConfigBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CONFIG);

    @FXML
    private FontAwesomeIconView icon_date;

    @FXML
    private FontAwesomeIconView icon_exit;

    @FXML
    private FontAwesomeIconView icon_login;

    @FXML
    private FontAwesomeIconView icon_signIn;

    @FXML
    private FontAwesomeIconView icon_pass;

    @FXML
    private FontAwesomeIconView icon_time;

    @FXML
    private FontAwesomeIconView icon_username;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_login;

    @FXML
    private Label lbl_date;

    @FXML
    private Label lbl_login;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_pass;

    @FXML
    private Label lbl_shortcuts;

    @FXML
    private Label lbl_time;

    @FXML
    private Label lbl_userName;

    @FXML
    private Label lbl_welcome;

    @FXML
    private AnchorPane pane;

    @FXML
    private Region region_back;

    @FXML
    private Region region_bottom;

    @FXML
    private Region region_front;

    @FXML
    private Region region_left;

    @FXML
    private Region region_right;

    @FXML
    private Region region_top;

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
        exit_popup();
    }

    private void exit_popup () {
        AtomicBoolean b = new AtomicBoolean(false);
        Platform.runLater(() -> {
            b.set(Theme.confirmPopup(
                    Session.isSinhala() ? "ඔබට පිටවීමට අවශ්‍ය බව විශ්වාසද ?" : "Are you sure you want to Exit ?",
                    stage
            ));
            if (b.get()) {
                System.exit(0);
            }
        });
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
            if (null == Session.getUser()) {
                loginError();
            } else {
                if (txt_pass.getText().equals("") && null == Session.getUser().getPassword()){
                    Parent root = FXMLLoader.load(Objects.requireNonNull(SetPasswordController.class.getResource("SetPassword.fxml")));
                    Scene scene = new Scene(root);
                    SetPasswordController.stage = new Stage();
                    SetPasswordController.stage.setScene(scene);
                    SetPasswordController.stage.setMaximized(true);
                    SetPasswordController.stage.setResizable(false);
                    SetPasswordController.stage.initStyle(StageStyle.UNDECORATED);
                    SetPasswordController.stage.show();
                    Theme.setShade(SetPasswordController.stage);
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ignored) {
                        }
                        Platform.runLater(() -> {
                            stage.close();
                        });
                    }).start();
                } else if (txt_pass.getText().equals(Session.getUser().getPassword())) {
                    try {
                        if (Session.getUser().getType().equals("admin")) {
                            Parent root = FXMLLoader.load(Objects.requireNonNull(AdminDashboardController.class.getResource("AdminDashboard.fxml")));
                            Scene scene = new Scene(root);
                            AdminDashboardController.stage = new Stage();
                            AdminDashboardController.stage.setScene(scene);
                            AdminDashboardController.stage.setMaximized(true);
                            AdminDashboardController.stage.setResizable(false);
                            AdminDashboardController.stage.initStyle(StageStyle.UNDECORATED);
                            AdminDashboardController.stage.show();
                            Theme.setShade(AdminDashboardController.stage);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ignored) {
                                }
                                Platform.runLater(() -> {
                                    stage.close();
                                });
                            }).start();
                        } else {
                            Parent root = FXMLLoader.load(Objects.requireNonNull(CompanyDashboardController.class.getResource("CompanyDashboard.fxml")));
                            Scene scene = new Scene(root);
                            CompanyDashboardController.stage = new Stage();
                            CompanyDashboardController.stage.setScene(scene);
                            CompanyDashboardController.stage.setMaximized(true);
                            CompanyDashboardController.stage.setResizable(false);
                            CompanyDashboardController.stage.initStyle(StageStyle.UNDECORATED);
                            CompanyDashboardController.stage.show();
                            Theme.setShade(CompanyDashboardController.stage);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ignored) {
                                }
                                Platform.runLater(() -> {
                                    stage.close();
                                });
                            }).start();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loginError();
                }
            }
        } catch (NullPointerException e) {
            Session.setUser(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginError () {
        Platform.runLater(() -> {
            Theme.giveAWarning(
                    Session.isSinhala()
                            ? "පරිශීලක නාමය හෝ මුරපදය වලංගු නොවේ"
                            : "Invalid Credentials",
                    "",
                    lbl_main,
                    region_back,
                    region_top,
                    region_bottom,
                    region_left,
                    region_right
            );
            txt_pass.setText("");
            txt_userName.setText("");
            txt_userName.requestFocus();
            Theme.giveBorderWarning(txt_userName);
            Theme.giveBorderWarning(txt_pass);
        });
    }

    @FXML
    void toggleBtn_language_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_exit.requestFocus();
        }
    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {
        txt_userName.requestFocus();
        new Thread(() -> {
            try {
                Session.setSinhala(!Session.isSinhala());
                setLanguage();
            } catch (SQLException e) {
                Theme.giveAWarning(Session.isSinhala() ? "දත්ත සමුදා වින්‍යාසය වලංගු නැත !" : "Database config invalid !", "", lbl_main, region_back, region_top, region_bottom, region_left, region_right);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> {
                Platform.runLater(() -> {
                    lbl_login.setText(" පිවිසුම");
                    lbl_welcome.setText("ආයුබෝවන් !");
                    lbl_userName.setText(" පරිශීලක නාමය");
                    txt_userName.setPromptText("පරිශීලක නාමය ඇතුළත් කරන්න");
                    lbl_pass.setText(" මුරපදය");
                    txt_pass.setPromptText("මුරපදය ඇතුළත් කරන්න");
                    btn_exit.setText(" අවලංගු කරන්න");
                    btn_login.setText(" පුරන්න");
                    lbl_shortcuts.setText("ඊළඟ = Enter / ආපසු = Esc / පිටවීම = F5");
                });
            }).start();
            toggleBtn_language.setSelected(true);
        } else {
            new Thread(() -> {
                Platform.runLater(() -> {
                    lbl_login.setText(" Login");
                    lbl_welcome.setText("Welcome !");
                    lbl_userName.setText(" User Name");
                    txt_userName.setPromptText("Enter User Name");
                    lbl_pass.setText(" Password");
                    txt_pass.setPromptText("Enter Password");
                    btn_exit.setText(" Exit");
                    btn_login.setText(" Login");
                    lbl_shortcuts.setText("Next = Enter / Back = Esc / Exit = F5");
                });
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
        Theme.setTimeDate(lbl_date, lbl_time);
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
            } catch (SQLException e) {
                Theme.giveAWarning(Session.isSinhala() ? "දත්ත සමුදා වින්‍යාසය වලංගු නැත !" : "Database config invalid !", "", lbl_main, region_back, region_top, region_bottom, region_left, region_right);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setFocusListeners() {
        txt_userName.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                txt_pass.setText("");
                lbl_main.setText(txt_userName.getText());
            }
        });
    }

    private void setColors() {
        new Thread(() -> {
            try {
                Theme.initialize();
                Platform.runLater(() -> {
                    // background
                    Theme.setBackgroundColor("background", pane, region_ui);
                    Theme.setBackgroundColor("success", region_back, btn_login);
                    Theme.setBackgroundColor("border", region_front, region_top, region_bottom, region_left, region_right);
                    Theme.setBackgroundColor("warning", btn_exit);
                    // text
                    Theme.setTextFill("font", toggleBtn_language, lbl_userName, lbl_pass, lbl_shortcuts);
                    Theme.setTextFill("background", lbl_welcome, lbl_main, btn_login, btn_exit, lbl_date, lbl_time);
                    Theme.setTextFill("border", lbl_login);
                    // toggle button
                    Theme.setToggleColor("success", "background", "border", "font", "background", toggleBtn_language);
                    // icon
                    Theme.setIconFill("background", icon_date, icon_time, icon_signIn, icon_exit);
                    Theme.setIconFill("border", icon_login);
                    Theme.setIconFill("font", icon_username, icon_pass);
                });
            } catch (SQLException e) {
                Theme.giveAWarning(Session.isSinhala() ? "දත්ත සමුදා වින්‍යාසය වලංගු නැත !" : "Database config invalid !", "", lbl_main, region_back, region_top, region_bottom, region_left, region_right);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
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