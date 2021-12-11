package front_end.ui.login;

import back_end.bo.BOFactory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class SetPasswordController implements Initializable {

    public static Stage stage;
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_login;

    @FXML
    private FontAwesomeIconView icon_date;

    @FXML
    private FontAwesomeIconView icon_exit;

    @FXML
    private FontAwesomeIconView icon_login;

    @FXML
    private FontAwesomeIconView icon_pass;

    @FXML
    private FontAwesomeIconView icon_signIn;

    @FXML
    private FontAwesomeIconView icon_time;

    @FXML
    private FontAwesomeIconView icon_username;

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
    private PasswordField txt_pass2;

    @FXML
    void btn_exit_onAction(ActionEvent event) {
        exit_popup();
    }

    private void exit_popup() {
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
            txt_pass2.requestFocus();
        }
    }

    @FXML
    void btn_login_onAction(ActionEvent event) {
        proceed();
    }

    private void proceed() {
        if (!txt_pass.getText().equals("")) {
            if (txt_pass.getText().equals(txt_pass2.getText())) {
                UserDTO userDTO;
                try {
                    userDTO = userBO.searchUser(Session.getUser().getName());
                    userDTO.setPassword(txt_pass.getText());
                    boolean updated = userBO.updateUser(userDTO);
                    if (updated) {
                        Theme.successGif(stage);
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ignored) {
                            }
                            Platform.runLater(() -> {
                                try {
                                    LoginController.backToLogin(stage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }).start();
                    }
                } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | InvalidKeyException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                Theme.giveBorderWarning(txt_pass);
                Theme.giveBorderWarning(txt_pass2);
                Theme.giveAWarning(
                        Session.isSinhala()
                                ? "නව මුරපද නොගැලපේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                : "New Passwords don't match, Please try again !",
                        Session.getUser().getName(),
                        lbl_main,
                        region_back,
                        region_top,
                        region_bottom,
                        region_left,
                        region_right
                );
                txt_pass.setText("");
                txt_pass2.setText("");
                txt_pass.requestFocus();
            }
        } else {
            Theme.giveBorderWarning(txt_pass);
            Theme.giveBorderWarning(txt_pass2);
            Theme.giveAWarning(
                    Session.isSinhala()
                            ? "නව මුරපද හිස් නොවිය යුතුය, කරුණාකර නැවත උත්සාහ කරන්න !"
                            : "New Passwords shouldn't be empty, Please try again !",
                    Session.getUser().getName(),
                    lbl_main,
                    region_back,
                    region_top,
                    region_bottom,
                    region_left,
                    region_right
            );
            txt_pass.setText("");
            txt_pass2.setText("");
            txt_pass.requestFocus();
        }
    }

    @FXML
    void toggleBtn_language_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_exit.requestFocus();
        }
    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {
        txt_pass.requestFocus();
        new Thread(() -> {
            try {
                Session.setSinhala(!Session.isSinhala());
                setLanguage();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    void txt_pass2_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_pass.requestFocus();
        }
    }

    @FXML
    void txt_pass2_onAction(ActionEvent event) {
        proceed();
    }

    @FXML
    void txt_pass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            toggleBtn_language.requestFocus();
        }
    }

    @FXML
    void txt_pass_onAction(ActionEvent event) {
        txt_pass2.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        Theme.setTimeDate(lbl_date, lbl_time);
        Theme.setChangeListeners(txt_pass, txt_pass2);
        Theme.scale(pane, true);
        new RunLater(txt_pass);
        runLater();
        new PaneOpenAnim(pane);
        setFocusListeners();
        lbl_main.setText(Session.getUser().getName());
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> {
                Platform.runLater(() -> {
                    lbl_login.setText(" මුරපදය සකසන්න");
                    lbl_welcome.setText("ආයුබෝවන් !");
                    lbl_userName.setText(" නව මුරපදය");
                    txt_pass.setPromptText("නව මුරපදය ඇතුළත් කරන්න");
                    lbl_pass.setText(" නව මුරපදය නැවත");
                    txt_pass2.setPromptText("නව මුරපදය නැවත ඇතුළත් කරන්න");
                    btn_exit.setText(" අවලංගු කරන්න");
                    btn_login.setText(" ඉදිරියට යන්න [F1]");
                    lbl_shortcuts.setText("ඊළඟ = Enter / ආපසු = Esc / ඉදිරියට යන්න = F1");
                });
            }).start();
            toggleBtn_language.setSelected(true);
        } else {
            new Thread(() -> {
                Platform.runLater(() -> {
                    lbl_login.setText(" Set Password");
                    lbl_welcome.setText("Welcome !");
                    lbl_userName.setText(" New Password");
                    txt_pass.setPromptText("Enter New Password");
                    lbl_pass.setText(" Repeat New Password");
                    txt_pass2.setPromptText("Re - Enter New Password");
                    btn_exit.setText(" Exit");
                    btn_login.setText(" Proceed [F1]");
                    lbl_shortcuts.setText("Next = Enter / Back = Esc / Proceed = F1");
                });
            }).start();
            toggleBtn_language.setSelected(false);
        }
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

    private void setFocusListeners() {
        txt_pass.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                txt_pass2.setText("");
            }
        });
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyReleased(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    proceed();
                }
            });
        });
    }
}
