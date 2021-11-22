package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import front_end.anim.PaneOpenAnim;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.admin.ManageAdminsController;
import front_end.ui.admin.ManageCompanyController;
import front_end.ui.login.LoginController;
import front_end.ui.settings.ChangePasswordController;
import front_end.ui.settings.ChangeThemeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    public static Stage stage;
    int count;

    @FXML
    private JFXButton btn_changePass;

    @FXML
    private JFXButton btn_changeTheme;

    @FXML
    private JFXButton btn_dashboard;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_lock;

    @FXML
    private JFXButton btn_manageAdmins;

    @FXML
    private JFXButton btn_manageCompany;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lbl_date;

    @FXML
    public Label lbl_main;

    @FXML
    private Label lbl_shortcuts;

    @FXML
    private Label lbl_time;

    @FXML
    private Label lbl_welcome;

    @FXML
    private Label lbl_userName;

    @FXML
    private AnchorPane pane;

    @FXML
    public Region region_back;

    @FXML
    public Region region_bottom;

    @FXML
    private Region region_menu;

    @FXML
    public Region region_left;

    @FXML
    public Region region_right;

    @FXML
    public Region region_top;

    @FXML
    private AnchorPane subPane;

    @FXML
    private JFXToggleButton toggleBtn_language;

    @FXML
    void toggleBtn_language_keyReleased() {

    }

    @FXML
    void toggleBtn_language_onAction() {
        new Thread(() -> {
            try {
                Session.setSinhala(!Session.isSinhala());
                setLanguage();
            } catch (SQLException e) {
                Theme.giveAWarning("Database config invalid", "", lbl_main, region_back, region_top, region_bottom, region_left, region_right);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setErrorInputs() {
        Session.admin_mainLabel = lbl_main;
        Session.admin_regionBack = region_back;
        Session.admin_regionTop = region_top;
        Session.admin_regionBottom = region_bottom;
        Session.admin_regionLeft = region_left;
        Session.admin_regionRight = region_right;
    }

    @FXML
    void btn_changePass_onAction() {
        handleAllButtons("changePass");
    }

    @FXML
    void btn_changeTheme_onAction() {
        handleAllButtons("changeTheme");
    }

    @FXML
    void btn_dashboard_onAction() {
        handleAllButtons("dashboard");
    }

    @FXML
    void btn_exit_onAction() {
        System.exit(0);
    }

    @FXML
    void btn_lock_onAction() {
        try {
            LoginController.backToLogin(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_manageAdmins_onAction() {
        handleAllButtons("manageAdmins");
    }

    @FXML
    void btn_manageCompany_onAction() {
        handleAllButtons("manageCompany");
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_welcome.setText(Session.getUser().getName() + " සාදරයෙන් පිළිගනිමු !");
                lbl_main.setText("සුභ දිනයක් වේවා !");
                btn_dashboard.setText("උපකරණ පුවරුව [F2]");
                btn_manageCompany.setText("සමාගම කළමනාකරණය කිරීම [F3]");
                btn_manageAdmins.setText("පරිපාලක කළමනාකරණය කිරීම [F6]");
                btn_changeTheme.setText("තේමාව වෙනස් කිරීම [F7]");
                btn_changePass.setText("මුරපදය වෙනස් කිරීම [F9]");
                btn_lock.setText("අගුල [F8]");
                btn_exit.setText("පිටවීම");
            })).start();
            toggleBtn_language.setSelected(true);
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_welcome.setText("Welcome " + Session.getUser().getName() + " !");
                lbl_main.setText("Have A Great Day !");
                btn_dashboard.setText("Dashboard [F2]");
                btn_manageCompany.setText("Manage Company [F3]");
                btn_manageAdmins.setText("Manage Admins [F6]");
                btn_changeTheme.setText("Change Theme [F7]");
                btn_changePass.setText("Change Password [F9]");
                btn_lock.setText("Lock [F8]");
                btn_exit.setText("Exit");
            })).start();
            toggleBtn_language.setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        lbl_userName.setText(Session.getUser().getName());
        setColors();
        Theme.setTimeDate(lbl_date, lbl_time);
        Theme.scale(pane, true);
        runLater();
        Session.imageSlider = subPane.getChildren().get(0);
        new RunLater(btn_dashboard);
        setErrorInputs();
        Platform.runLater(() -> subPane.setStyle("-fx-effect: dropshadow(three-pass-box,#C9C9C98D, 20.0, 0.0, 0.0, 10.0);"));
    }

    private void setColors() {
        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    Theme.setBackgroundColor("background", pane, region_menu);
                    Theme.setBackgroundColor("success", region_back);
                    Theme.setBackgroundColor("border", region_top, region_bottom, region_left, region_right);
                    Theme.setTextFill("background", lbl_welcome, lbl_main, lbl_date, lbl_time);
                    Theme.setTextFill("border", lbl_userName, btn_dashboard, btn_manageCompany, btn_manageAdmins, btn_changeTheme, btn_changePass, btn_lock);
                    Theme.setTextFill("warning", btn_exit);
                    Theme.setTextFill("font", lbl_shortcuts);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void slideShow() {
        ArrayList<Image> images;
        images = new ArrayList<>();
        images.add(new Image("front_end/img/dashboard/adminBackground.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground2.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground3.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            imageView.setImage(images.get(count));
            count++;
            if (count == 3)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void handleAllButtons(String btnName) {
        subPane.getChildren().clear();
        switch (btnName) {
            case "dashboard" -> {
                btn_dashboard.requestFocus();
                subPane.getChildren().add(Session.imageSlider);
            }
            case "manageCompany" -> {
                btn_manageCompany.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(ManageCompanyController.class.getResource("ManageCompany.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "manageAdmins" -> {
                btn_manageAdmins.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(ManageAdminsController.class.getResource("ManageAdmins.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "changeTheme" -> {
                btn_changeTheme.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(ChangeThemeController.class.getResource("ChangeTheme.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "changePass" -> {
                btn_changePass.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(ChangePasswordController.class.getResource("ChangePassword.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        new PaneOpenAnim(subPane);
    }

    private void runLater() {
        Platform.runLater(() -> {
            btn_dashboard.requestFocus();
            slideShow();
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F2)) {
                    handleAllButtons("dashboard");
                } else if (event.getCode().equals(KeyCode.F3)) {
                    handleAllButtons("manageCompany");
                } else if (event.getCode().equals(KeyCode.F6)) {
                    handleAllButtons("manageAdmins");
                } else if (event.getCode().equals(KeyCode.F7)) {
                    handleAllButtons("changeTheme");
                } else if (event.getCode().equals(KeyCode.F8)) {
                    try {
                        LoginController.backToLogin(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().equals(KeyCode.F9)) {
                    handleAllButtons("changePass");
                }
            });
        });
    }
}