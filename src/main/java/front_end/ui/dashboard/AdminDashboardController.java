package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
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
    private Label lbl_main;

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
    private Region region_back;

    @FXML
    private Region region_bottom;

    @FXML
    private Region region_menu;

    @FXML
    private Region region_left;

    @FXML
    private Region region_right;

    @FXML
    private Region region_top;

    @FXML
    private AnchorPane subPane;

    @FXML
    void btn_changePass_onAction(ActionEvent event) {
        handleAllButtons("changePass");
    }

    @FXML
    void btn_changeTheme_onAction(ActionEvent event) {
        handleAllButtons("changeTheme");
    }

    @FXML
    void btn_dashboard_onAction(ActionEvent event) {
        handleAllButtons("dashboard");
    }

    @FXML
    void btn_exit_onAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_lock_onAction(ActionEvent event) {
        try {
            LoginController.backToLogin(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_manageAdmins_onAction(ActionEvent event) {
        handleAllButtons("manageAdmins");
    }

    @FXML
    void btn_manageCompany_onAction(ActionEvent event) {
        handleAllButtons("manageCompany");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_welcome.setText("Welcome " + Session.getUser().getName() + " !");
        lbl_userName.setText(Session.getUser().getName());
        setColors();
        Theme.setTimeDate(lbl_date, lbl_time);
        Theme.scale(pane, true);
        runLater();
        Session.imageSlider = subPane.getChildren().get(0);
        new RunLater(btn_dashboard);
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