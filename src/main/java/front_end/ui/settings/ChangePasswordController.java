package front_end.ui.settings;

import back_end.bo.BOFactory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import front_end.ui.dashboard.CompanyDashboardController;
import front_end.ui.login.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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

public class ChangePasswordController implements Initializable {
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_save;

    @FXML
    private FontAwesomeIconView icon_refresh;

    @FXML
    private FontAwesomeIconView icon_save;

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
    private PasswordField txt_currentPass;

    @FXML
    private PasswordField txt_newPass;

    @FXML
    private PasswordField txt_newPass2;

    @FXML
    void txt_currentPass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_refresh.requestFocus();
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
        updatePassword();
    }

    @FXML
    void btn_save_onAction() {
        updatePassword();
    }

    private boolean checkCurrentPassword() {
        UserDTO userDTO = null;
        try {
            userDTO = userBO.searchUser(Session.getUser().getName());
        } catch (SQLException | ClassNotFoundException | UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        assert userDTO != null;
        if (userDTO.getPassword().equals(txt_currentPass.getText())) {
            return true;
        } else {
            Theme.giveBorderWarning(txt_currentPass);
            Theme.giveAWarning(
                    Session.isSinhala()
                            ? "වැරදි මුරපදයක්, කරුණාකර නැවත උත්සාහ කරන්න !"
                            : "Incorrect Password, Please try again !",
                    Session.getUser().getType().equals("admin") ? AdminDashboardController.windowMsg : CompanyDashboardController.windowMsg,
                    Session.admin_mainLabel,
                    Session.admin_regionBack,
                    Session.admin_regionTop,
                    Session.admin_regionBottom,
                    Session.admin_regionLeft,
                    Session.admin_regionRight
            );
            clearAll();
            return false;
        }
    }

    private void updatePassword() {
        if (checkCurrentPassword()) {
            if (!txt_newPass.getText().equals(txt_currentPass.getText())) {
                if (!txt_newPass.getText().equals("")) {
                    if (txt_newPass.getText().equals(txt_newPass2.getText())) {
                        UserDTO userDTO;
                        try {
                            userDTO = userBO.searchUser(Session.getUser().getName());
                            userDTO.setPassword(txt_newPass.getText());
                            boolean updated = userBO.updateUser(userDTO);
                            if (updated) {
                                Theme.successGif(Session.getUser().getType().equals("admin") ? AdminDashboardController.stage : CompanyDashboardController.stage);
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ignored) {
                                    }
                                    Platform.runLater(() -> {
                                        try {
                                            LoginController.backToLogin(Session.getUser().getType().equals("admin") ? AdminDashboardController.stage : CompanyDashboardController.stage);
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
                        Theme.giveBorderWarning(txt_newPass);
                        Theme.giveBorderWarning(txt_newPass2);
                        Theme.giveAWarning(
                                Session.isSinhala()
                                        ? "නව මුරපද නොගැලපේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                        : "New Passwords don't match, Please try again !",
                                Session.getUser().getType().equals("admin") ? AdminDashboardController.windowMsg : CompanyDashboardController.windowMsg,
                                Session.admin_mainLabel,
                                Session.admin_regionBack,
                                Session.admin_regionTop,
                                Session.admin_regionBottom,
                                Session.admin_regionLeft,
                                Session.admin_regionRight
                        );
                        clearAll();
                        txt_currentPass.requestFocus();
                    }
                } else {
                    Theme.giveBorderWarning(txt_newPass);
                    Theme.giveBorderWarning(txt_newPass2);
                    Theme.giveAWarning(
                            Session.isSinhala()
                                    ? "නව මුරපද හිස් නොවිය යුතුය, කරුණාකර නැවත උත්සාහ කරන්න !"
                                    : "New Passwords shouldn't be empty, Please try again !",
                            Session.getUser().getType().equals("admin") ? AdminDashboardController.windowMsg : CompanyDashboardController.windowMsg,
                            Session.admin_mainLabel,
                            Session.admin_regionBack,
                            Session.admin_regionTop,
                            Session.admin_regionBottom,
                            Session.admin_regionLeft,
                            Session.admin_regionRight
                    );
                    clearAll();
                    txt_currentPass.requestFocus();
                }
            } else {
                Theme.giveBorderWarning(txt_newPass);
                Theme.giveBorderWarning(txt_newPass2);
                Theme.giveAWarning(
                        Session.isSinhala()
                                ? "නව මුරපද වත්මන් මුරපදයට සමාන නොවිය යුතුය, කරුණාකර නැවත උත්සාහ කරන්න !"
                                : "New Passwords shouldn't be the same as current password, Please try again !",
                        Session.getUser().getType().equals("admin") ? AdminDashboardController.windowMsg : CompanyDashboardController.windowMsg,
                        Session.admin_mainLabel,
                        Session.admin_regionBack,
                        Session.admin_regionTop,
                        Session.admin_regionBottom,
                        Session.admin_regionLeft,
                        Session.admin_regionRight
                );
                clearAll();
                txt_currentPass.requestFocus();
            }
        }
    }

    @FXML
    void btn_refresh_onAction() {
        clearAll();
    }

    private void clearAll() {
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
                btn_save.setText(" සුරකින්න [F1]");
                btn_refresh.setText(" නැවුම් කරන්න [F5]");
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
                btn_save.setText(" Save [F1]");
                btn_refresh.setText(" Refresh [F5]");
            })).start();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(txt_currentPass);
        runLater();
        Theme.setChangeListeners(txt_currentPass, txt_newPass, txt_newPass2);
        setFocusListeners();
    }

    private void setFocusListeners() {
        txt_currentPass.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused && !txt_currentPass.getText().equals("")) {
                checkCurrentPassword();
            }
        });
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                   updatePassword();
                } else if (event.getCode().equals(KeyCode.F5)) {
                    clearAll();
                }
            });
        });
    }

    private void setColors() {
        Platform.runLater(() -> {
            // background
            Theme.setBackgroundColor("background", pane);
            Theme.setBackgroundColor("success", btn_save);
            Theme.setBackgroundColor("border", btn_refresh);
            // text
            Theme.setTextFill("background", btn_refresh, btn_save);
            Theme.setTextFill("success", lbl_main);
            Theme.setTextFill("font", lbl_currentPass, lbl_newPass, lbl_newPass2);
            // icon
            Theme.setIconFill("background", icon_refresh, icon_save);
        });
    }
}
