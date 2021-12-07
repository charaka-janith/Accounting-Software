package front_end.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.ui.company.VoucherController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CompanyDashboardController implements Initializable {

    public static Stage stage;

    @FXML
    private JFXButton btn_accounts;

    @FXML
    private JFXButton btn_balance;

    @FXML
    private JFXButton btn_bankBook;

    @FXML
    private JFXButton btn_cashBook;

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
    private JFXButton btn_profitLoss;

    @FXML
    private JFXButton btn_receipt;

    @FXML
    private JFXButton btn_voucher;

    @FXML
    private FontAwesomeIconView icon_balance;

    @FXML
    private FontAwesomeIconView icon_bankBook;

    @FXML
    private FontAwesomeIconView icon_cashBook;

    @FXML
    private FontAwesomeIconView icon_changePassword;

    @FXML
    private FontAwesomeIconView icon_changeTheme;

    @FXML
    private FontAwesomeIconView icon_dashboard;

    @FXML
    private FontAwesomeIconView icon_date;

    @FXML
    private FontAwesomeIconView icon_exit;

    @FXML
    private FontAwesomeIconView icon_ledgerAccts;

    @FXML
    private FontAwesomeIconView icon_lock;

    @FXML
    private FontAwesomeIconView icon_profitLoss;

    @FXML
    private FontAwesomeIconView icon_receipt;

    @FXML
    private FontAwesomeIconView icon_time;

    @FXML
    private FontAwesomeIconView icon_voucher;

    @FXML
    private Label lbl_date;

    @FXML
    private Label lbl_main;

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
    private Region region_left;

    @FXML
    private Region region_menu;

    @FXML
    private Region region_right;

    @FXML
    private Region region_top;

    @FXML
    private AnchorPane subPane;

    @FXML
    private JFXToggleButton toggleBtn_language;

    @FXML
    void btn_accounts_onAction(ActionEvent event) {

    }

    @FXML
    void btn_balance_onAction(ActionEvent event) {

    }

    @FXML
    void btn_bankBook_onAction(ActionEvent event) {

    }

    @FXML
    void btn_cashBook_onAction(ActionEvent event) {

    }

    @FXML
    void btn_changePass_onAction(ActionEvent event) {

    }

    @FXML
    void btn_changeTheme_onAction(ActionEvent event) {

    }

    @FXML
    void btn_dashboard_onAction(ActionEvent event) {

    }

    @FXML
    void btn_exit_onAction(ActionEvent event) {

    }

    @FXML
    void btn_lock_onAction(ActionEvent event) {

    }

    @FXML
    void btn_onKeyReleased(KeyEvent event) {

    }

    @FXML
    void btn_profitLoss_onAction(ActionEvent event) {

    }

    @FXML
    void btn_receipt_onAction(ActionEvent event) {

    }

    @FXML
    void btn_voucher_onAction(ActionEvent event) {
        subPane.getChildren().clear();
        try {
            subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                    VoucherController.class.getResource("Voucher.fxml")
            )));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
