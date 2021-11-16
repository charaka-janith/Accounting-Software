package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import front_end.anim.PaneOpenAnim;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.admin.ManageCompanyController;
import front_end.ui.login.LoginController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    Parent root;
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
    private Label lbl_main;

    @FXML
    private Label lbl_shortcuts;

    @FXML
    private Label lbl_welcome;

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

    @FXML
    void btn_changePass_onAction(ActionEvent event) {

    }

    @FXML
    void btn_changeTheme_onAction(ActionEvent event) {

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
            Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
        }
    }

    @FXML
    void btn_manageAdmins_onAction(ActionEvent event) {

    }

    @FXML
    void btn_manageCompany_onAction(ActionEvent event) {
        handleAllButtons("manageCompany");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowName = "Welcome Back, " + Session.getUser().getName() + " !";
        lbl_main.setText(windowName);
        Theme.scale(pane, true);
        runLater();
        Session.imageSlider = subPane.getChildren().get(0);
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
                    Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
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
                }
            });
        });
    }
}