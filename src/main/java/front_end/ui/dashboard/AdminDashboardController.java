package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    }
}
