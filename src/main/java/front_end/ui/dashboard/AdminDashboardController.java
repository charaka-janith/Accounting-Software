package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.admin.CreateCompanyController;
import front_end.ui.login.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    public static Stage stage;
    private String windowName;

    @FXML
    private JFXButton btn_changePass;

    @FXML
    private JFXButton btn_changeTheme;

    @FXML
    private JFXButton btn_createCompany;

    @FXML
    private JFXButton btn_editCompany;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_lock;

    @FXML
    private JFXButton btn_manageAdmins;

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

    @FXML
    void btn_changePass_onAction(ActionEvent event) {

    }

    @FXML
    void btn_changeTheme_onAction(ActionEvent event) {

    }

    @FXML
    void btn_createCompany_onAction(ActionEvent event) {
goToCreateCompany();
    }

    public void goToCreateCompany() {
        try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(CreateCompanyController.class.getResource("CreateCompany.fxml")));
                subPane.getChildren().clear();
                subPane.getChildren().add(root);
        } catch (IOException e) {
            Theme.giveAWarning(e.getMessage(), windowName, lbl_main, region_left, region_right, region_bottom, region_top);
        }
    }

    @FXML
    void btn_editCompany_onAction(ActionEvent event) {

    }

    @FXML
    void btn_exit_onAction(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowName = "Welcome " + Session.getUser().getName() + " !";
        lbl_main.setText(windowName);
        Theme.scale(pane, true);
        runLater();
    }

    private void runLater() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btn_createCompany.requestFocus();
                pane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent event) {
                        if (event.getCode().equals(KeyCode.F1)) {
                            // TODO dilini -> create company
                        } else if (event.getCode().equals(KeyCode.F2)) {
                            // TODO dilini
                        }
                    }
                });
            }
        });
    }
}
