package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.admin.CreateCompanyController;
import front_end.ui.login.LoginController;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private String windowName;

    @FXML
    private JFXButton btn_createCompany;

    @FXML
    private JFXButton btn_editCompany;

    @FXML
    private JFXButton btn_manageAdmins;

    @FXML
    private JFXButton btn_changeTheme;

    @FXML
    private JFXButton btn_changePass;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_lock;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_shortcuts;

    @FXML
    private AnchorPane pane;

    @FXML
    private Region region_bottom;

    @FXML
    private Region region_left;

    @FXML
    private Region region_right;

    @FXML
    private Region region_top;

    @FXML
    private AnchorPane subPane;

    Parent root;
    int count;

    public void slideShow() {
        ArrayList<Image> images;
        images = new ArrayList<>();
        images.add(new Image("front_end/img/dashboard/adminBackground.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground2.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground3.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground4.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        /*........................fading slide show..........................
        public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {
            FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
            ft.setFromValue(fromValue);
            ft.setToValue(toValue);
            return ft;
        }

        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("front_end/img/dashboard/adminBackground.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground2.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground3.jpg"));
        images.add(new Image("front_end/img/dashboard/adminBackground4.jpg"));

        SequentialTransition slideshow = new SequentialTransition();

        for (Image image : images) {
            imageView.setImage(image);
            SequentialTransition sequentialTransition = new SequentialTransition();

            FadeTransition fadeIn = getFadeTransition(imageView, 0.0, 1.0, 2000);
            PauseTransition stayOn = new PauseTransition(Duration.millis(2000));
            FadeTransition fadeOut = getFadeTransition(imageView, 1.0, 0.0, 2000);

            sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
            imageView.setOpacity(0);
            imageView.setImage(slide);
            slideshow.getChildren().add(sequentialTransition);

        }
        slideshow.setCycleCount(Timeline.INDEFINITE);
        slideshow.play();*/
    }

    public void handleAllButtons(String btnName) {
        switch (btnName) {
            case "createCompany":
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(CreateCompanyController.class.getResource("CreateCompany.fxml")));
                } catch (IOException e) {
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                }
                break;
            case "editCompany":
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(CreateCompanyController.class.getResource("EditCompany.fxml")));
                } catch (IOException e) {
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                }
                break;
            case "manageAdmins":
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(CreateCompanyController.class.getResource("ManageAdmins.fxml")));
                } catch (IOException e) {
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                }
                break;
            case "changeTheme":
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(CreateCompanyController.class.getResource("ChangeTheme.fxml")));
                } catch (IOException e) {
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                }
                break;
            case "changePassword":
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(CreateCompanyController.class.getResource("ChangePassword.fxml")));
                } catch (IOException e) {
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
                }
                break;
            default:
                System.out.println("............nothing");
                break;
        }
        subPane.getChildren().clear();
        subPane.getChildren().add(root);
    }

    @FXML
    void btn_createCompany_onAction() {
        handleAllButtons("createCompany");
    }

    @FXML
    void btn_editCompany_onAction() {
        handleAllButtons("editCompany");
    }

    @FXML
    void btn_manageAdmins_onAction() {
        handleAllButtons("manageAdmins");
    }

    @FXML
    void btn_changeTheme_onAction() {
        handleAllButtons("changeTheme");
    }

    @FXML
    void btn_changePass_onAction() {
        handleAllButtons("changePassword");
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
            Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowName = "Welcome Back, " + Session.getUser().getName() + " !";
        lbl_main.setText(windowName);
        Theme.scale(pane, true);
        runLater();
    }

    private void runLater() {
        Platform.runLater(() -> {
            btn_createCompany.requestFocus();
            slideShow();
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    handleAllButtons("createCompany");
                } else if (event.getCode().equals(KeyCode.F2)) {
                    handleAllButtons("editCompany");
                } else if (event.getCode().equals(KeyCode.F3)) {
                    handleAllButtons("manageAdmins");
                } else if (event.getCode().equals(KeyCode.F5)) {
                    handleAllButtons("changeTheme");
                } else if (event.getCode().equals(KeyCode.F6)) {
                    handleAllButtons("changePassword");
                }
            });
        });
    }
}
