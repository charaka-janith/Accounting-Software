package front_end.ui.dashboard;

import back_end.bo.BOFactory;
import back_end.bo.custom.CompanyBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.PaneOpenAnim;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.company.CashBookController;
import front_end.ui.company.LedgersController;
import front_end.ui.company.ReceiptController;
import front_end.ui.company.VoucherController;
import front_end.ui.login.LoginController;
import front_end.ui.settings.ChangePasswordController;
import front_end.ui.settings.ChangeThemeController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompanyDashboardController implements Initializable {

    public static Stage stage;
    public static String windowMsg;
    // bo's
    private final CompanyBO companyBO = (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);

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
        handle_buttons("ledger");
    }

    @FXML
    void btn_balance_onAction(ActionEvent event) {

    }

    @FXML
    void btn_bankBook_onAction(ActionEvent event) {

    }

    @FXML
    void btn_cashBook_onAction(ActionEvent event) {
        handle_buttons("cashBook");
    }

    @FXML
    void btn_changePass_onAction(ActionEvent event) {
        handle_buttons("changePass");
    }

    @FXML
    void btn_changeTheme_onAction(ActionEvent event) {
        handle_buttons("changeTheme");
    }

    @FXML
    void btn_dashboard_onAction(ActionEvent event) {
        handle_buttons("dashboard");
    }

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
    void btn_lock_onAction(ActionEvent event) {
        try {
            LoginController.backToLogin(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            toggleBtn_language.requestFocus();
        }
    }

    @FXML
    void btn_profitLoss_onAction(ActionEvent event) {

    }

    @FXML
    void btn_receipt_onAction(ActionEvent event) {
        handle_buttons("receipt");
    }

    @FXML
    void btn_voucher_onAction(ActionEvent event) {
        handle_buttons("voucher");
    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {
        new Thread(() -> {
            try {
                Session.setSinhala(!Session.isSinhala());
                setLanguage();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
        handle_buttons(Session.getCurrent_subPane());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        Theme.setTimeDate(lbl_date, lbl_time);
        lbl_userName.setText(Session.getUser().getName());
        Theme.scale(pane, true);
        new RunLater(btn_dashboard);
        runLater();
        new PaneOpenAnim(pane);
        setErrorInputs();
        Session.setSubPane(subPane);
    }

    private void handle_buttons(String btnName) {
        subPane.getChildren().clear();
        switch (btnName) {
            case "dashboard" -> {
                btn_dashboard.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(CompanyChartsController.class.getResource("CompanyCharts.fxml"))));
                    Session.setCurrent_subPane("dashboard");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "receipt" -> {
                btn_receipt.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                            ReceiptController.class.getResource("Receipt.fxml")
                    )));
                    Session.setCurrent_subPane("receipt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "voucher" -> {
                btn_voucher.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                            VoucherController.class.getResource("Voucher.fxml")
                    )));
                    Session.setCurrent_subPane("voucher");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "cashBook" -> {
                btn_cashBook.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                            CashBookController.class.getResource("CashBook.fxml")
                    )));
                    Session.setCurrent_subPane("cashBook");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "ledger" -> {
                btn_accounts.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                            LedgersController.class.getResource("Ledgers.fxml")
                    )));
                    Session.setCurrent_subPane("ledger");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "changeTheme" -> {
                btn_changeTheme.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                            ChangeThemeController.class.getResource("ChangeTheme.fxml")
                    )));
                    Session.setCurrent_subPane("changeTheme");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "changePass" -> {
                btn_changePass.requestFocus();
                try {
                    subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(ChangePasswordController.class.getResource("ChangePassword.fxml"))));
                    Session.setCurrent_subPane("changePass");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Platform.runLater(() -> {
            if (Session.isSinhala()) {
                switch (Session.getCurrent_subPane()) {
                    case "dashboard" -> lbl_shortcuts.setText("උපකරණ පුවරුව = F2 / සමාගම් කළමනාකරණ = F3 / පරිපාලක කළමනාකරණ = F6 / තේමාව වෙනස් කිරීම = F7 / මුරපදය වෙනස් කිරීම = F9 / අගුල = F8");
                    case "manageAdmins" -> lbl_shortcuts.setText("ඊළඟ = Enter / ඊළඟ පේළිය = Down / පෙර පේළිය = Up / සුරකින්න = F1 / නැවුම් කරන්න = F5");
                    case "changeTheme" -> lbl_shortcuts.setText("සුරකින්න = F1 / යළි පිහිටුවන්න = F5");
                    case "changePass" -> lbl_shortcuts.setText("ඊළඟ = Enter / ආපසු = Esc / සුරකින්න = F1 / නැවුම් කරන්න = F5");
                }
            } else {
                switch (Session.getCurrent_subPane()) {
                    case "dashboard" -> lbl_shortcuts.setText("Dashboard = F2 / Manage Company = F3 / Manage Admins = F6 / Change Theme = F7 / Change Password = F9 / Lock = F8");
                    case "manageAdmins" -> lbl_shortcuts.setText("Next = Enter / Next Row = Down / Previous Row = Up / Save = F1 / Refresh = F5");
                    case "changeTheme" -> lbl_shortcuts.setText("Save = F1 / Reset = F5");
                    case "changePass" -> lbl_shortcuts.setText("Next = Enter / Back = Esc / Save = F1 / Refresh = F5");
                }
            }
        });
        new PaneOpenAnim(subPane);
    }

    private void setColors() {
        Platform.runLater(() -> {
            subPane.setStyle("-fx-effect: dropshadow(three-pass-box," + Theme.background + ", 20.0, 0.0, 0.0, 10.0);");
            // background
            Theme.setBackgroundColor("background", pane, region_menu);
            Theme.setBackgroundColor("success", region_back);
            Theme.setBackgroundColor("border", region_top, region_bottom, region_left, region_right);
            // text
            Theme.setTextFill("background", lbl_welcome, lbl_main, lbl_date, lbl_time);
            Theme.setTextFill("border",
                    lbl_userName,
                    btn_dashboard,
                    btn_receipt,
                    btn_voucher,
                    btn_cashBook,
                    btn_bankBook,
                    btn_accounts,
                    btn_profitLoss,
                    btn_balance,
                    btn_changeTheme,
                    btn_changePass,
                    btn_lock
            );
            Theme.setTextFill("warning", btn_exit);
            Theme.setTextFill("font", lbl_shortcuts, toggleBtn_language);
            // toggle button
            Theme.setToggleColor("success", "background", "border", "font", "background", toggleBtn_language);
            // icon
            Theme.setIconFill("background", icon_date, icon_time);
            Theme.setIconFill("border",
                    icon_dashboard,
                    icon_receipt,
                    icon_voucher,
                    icon_cashBook,
                    icon_bankBook,
                    icon_ledgerAccts,
                    icon_profitLoss,
                    icon_balance,
                    icon_changeTheme,
                    icon_changePassword,
                    icon_lock
            );
            Theme.setIconFill("warning", icon_exit);
        });
    }

    private void setLanguage() {
        try {
            lbl_welcome.setText(companyBO.getCompany().getName());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("සුභ දිනයක් වේවා !");
                btn_dashboard.setText(" උපකරණ පුවරුව");
                btn_receipt.setText(" කුවිතාන්සි [F2]");
                btn_voucher.setText(" වවුචර් [F3]");
                btn_cashBook.setText(" මුදල් පොත [F6]");
                btn_bankBook.setText(" Bank Book [F7]");
                btn_accounts.setText(" ලෙජර් ගිණුම් [F9]");
                btn_profitLoss.setText(" Profit and Loss [F10]");
                btn_balance.setText(" Trial Balance [F11]");
                btn_changeTheme.setText(" තේමාව වෙනස් කිරීම [F12]");
                btn_changePass.setText(" මුරපදය වෙනස් කිරීම");
                btn_lock.setText(" අගුල [F8]");
                btn_exit.setText(" පිටවීම");
            })).start();
            toggleBtn_language.setSelected(true);
            windowMsg = "සුභ දිනයක් වේවා !";
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Have A Great Day !");
                btn_dashboard.setText(" Dashboard");
                btn_receipt.setText(" Receipt [F2]");
                btn_voucher.setText(" Voucher [F3]");
                btn_cashBook.setText(" Cash Book [F6]");
                btn_bankBook.setText(" Bank Book [F7]");
                btn_accounts.setText(" Ledger Accounts [F9]");
                btn_profitLoss.setText(" Profit and Loss [F10]");
                btn_balance.setText(" Trial Balance [F11]");
                btn_changeTheme.setText(" Change Theme [F12]");
                btn_changePass.setText(" Change Password");
                btn_lock.setText(" Lock [F8]");
                btn_exit.setText(" Exit");
            })).start();
            toggleBtn_language.setSelected(false);
            windowMsg = "Have A Great Day !";
        }
    }

    private void setErrorInputs() {
        Session.admin_mainLabel = lbl_main;
        Session.admin_regionBack = region_back;
        Session.admin_regionTop = region_top;
        Session.admin_regionBottom = region_bottom;
        Session.admin_regionLeft = region_left;
        Session.admin_regionRight = region_right;
    }

    private void runLater() {
        Platform.runLater(() -> {
            try {
                subPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(CompanyChartsController.class.getResource("CompanyCharts.fxml"))));
                Session.setCurrent_subPane("dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
            pane.getScene().setOnKeyReleased(event -> {
                if (event.getCode().equals(KeyCode.F2)) {
                    handle_buttons("receipt");
                } else if (event.getCode().equals(KeyCode.F3)) {
                    handle_buttons("voucher");
                } else if (event.getCode().equals(KeyCode.F6)) {
                    handle_buttons("cashBook");
                } else if (event.getCode().equals(KeyCode.F8)) {
                    try {
                        LoginController.backToLogin(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().equals(KeyCode.F9)) {
                    handle_buttons("ledger");
                } else if (event.getCode().equals(KeyCode.F12)) {
                    handle_buttons("changeTheme");
                }
            });
        });
    }
}
