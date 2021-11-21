package front_end.ui.admin;

import back_end.bo.BOFactory;
import back_end.bo.custom.CompanyBO;
import back_end.dto.CompanyDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static front_end.ui.dashboard.AdminDashboardController.*;

public class ManageCompanyController implements Initializable {

    CompanyBO companyBO = (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private Label lbl_address;

    @FXML
    private Label lbl_businessRegistrationNumber;

    @FXML
    private Label lbl_email;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_phoneNumber;

    @FXML
    private Label lbl_userName;

    @FXML
    private Label lbl_website;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXToggleButton toggleBtn_language;

    @FXML
    private TextArea txt_address;

    @FXML
    private TextField txt_businessRegistrationNumber;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_phoneNumber;

    @FXML
    private TextField txt_userName;

    @FXML
    private TextField txt_website;

    //    ..........................................Key Events........................................
    @FXML
    void txt_name_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_name.requestFocus();
        }
    }

    @FXML
    void txt_userName_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_name.requestFocus();
        }
    }

    @FXML
    void txt_address_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_userName.requestFocus();
        }
    }

    @FXML
    void txt_phoneNumber_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_address.requestFocus();
        }
    }

    @FXML
    void txt_email_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_phoneNumber.requestFocus();
        }
    }

    @FXML
    void txt_website_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_email.requestFocus();
        }
    }

    @FXML
    void txt_businessRegistrationNumber_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_website.requestFocus();
        }
    }

    @FXML
    void btn_save_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_businessRegistrationNumber.requestFocus();
        }
    }

    @FXML
    void toggleBtn_language_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
         btn_refresh.requestFocus();
        }
    }

    //    ..........................................Action Events........................................
    @FXML
    void txt_name_onAction() {
        txt_userName.requestFocus();
    }

    @FXML
    void txt_userName_onAction() {
        txt_address.requestFocus();
    }

    @FXML
    void txt_address_onAction() {
        txt_phoneNumber.requestFocus();
    }

    @FXML
    void txt_phoneNumber_onAction() {
        txt_email.requestFocus();
    }

    @FXML
    void txt_email_onAction() {
        txt_website.requestFocus();
    }

    @FXML
    void txt_website_onAction() {
        txt_businessRegistrationNumber.requestFocus();
    }

    @FXML
    void txt_businessRegistrationNumber_onAction() {
        addOrUpdateCompanyDetails();
        clearTextFields();
        btn_save.setDisable(true);
        btn_refresh.requestFocus();
    }

    @FXML
    void btn_save_onAction() {
        Theme.giveAWarning("Database config invalid", "Have A Great Day !", Session.admin_mainLabel, Session.admin_regionBack, Session.admin_regionTop, Session.admin_regionBottom, Session.admin_regionLeft, Session.admin_regionRight);
        addOrUpdateCompanyDetails();
        clearTextFields();
        btn_save.setDisable(true);
        btn_refresh.requestFocus();
    }

    @FXML
    void btn_refresh_onAction() {
        setCompanyDetails();
        btn_save.setDisable(false);
    }

    @FXML
    void toggleBtn_language_onAction(ActionEvent event) {
        txt_name.requestFocus();
        new Thread(() -> {
            try {
                Session.setSinhala(!Session.isSinhala());
                setLanguage();
            } catch (SQLException e) {
                Theme.giveAWarning("Database config invalid", "Have A Great Day !", Session.admin_mainLabel, Session.admin_regionBack, Session.admin_regionTop, Session.admin_regionBottom, Session.admin_regionLeft, Session.admin_regionRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> {
                Platform.runLater(() -> {

                });
            }).start();
            toggleBtn_language.setSelected(true);
        } else {
            new Thread(() -> {
                Platform.runLater(() -> {

                });
            }).start();
            toggleBtn_language.setSelected(false);
        }
    }

    private void addOrUpdateCompanyDetails() {
        try {
            CompanyDTO company = companyBO.getCompany();
            if (null != company) {
                companyBO.updateCompany(new CompanyDTO(txt_name.getText(), txt_address.getText(), txt_phoneNumber.getText(), txt_email.getText(), txt_website.getText(), txt_businessRegistrationNumber.getText()));
            } else {
                companyBO.addCompany(new CompanyDTO(txt_name.getText(), txt_address.getText(), txt_phoneNumber.getText(), txt_email.getText(), txt_website.getText(), txt_businessRegistrationNumber.getText()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCompanyDetails() {
        try {
            CompanyDTO company = companyBO.getCompany();
            if (null != company) {
                Platform.runLater(() -> {
                    lbl_main.setText("Update Company");
                    btn_save.setText("Update [F1]");
                    txt_name.setText(company.getName());
                    txt_address.setText(company.getAddress());
                    txt_phoneNumber.setText(company.getPhoneNumber());
                    txt_email.setText(company.getEmail());
                    txt_website.setText(company.getWebSite());
                    txt_businessRegistrationNumber.setText(company.getBrn());
                    txt_name.requestFocus();
                });
            } else {
                Platform.runLater(() -> {
                    lbl_main.setText("Create Company");
                    btn_save.setText("Create [F1]");
                    clearTextFields();
                    txt_name.requestFocus();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTextFields() {
        txt_name.setText("");
        txt_address.setText("");
        txt_phoneNumber.setText("");
        txt_email.setText("");
        txt_website.setText("");
        txt_businessRegistrationNumber.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new RunLater(txt_name);
        setColors();
        setLanguage();
        Theme.setChangeListeners(txt_name, txt_userName, txt_phoneNumber, txt_email, txt_website, txt_businessRegistrationNumber);
        Platform.runLater(() -> txt_address.textProperty().addListener((observableValue, s, t1) -> txt_address.setStyle("-fx-border-color: transparent")));
        new Thread(this::setCompanyDetails).start();
    }

    private void setColors() {
        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    /*Theme.setBackgroundColor("background", pane, region_menu);
                    Theme.setBackgroundColor("success", region_back);
                    Theme.setBackgroundColor("border", region_top, region_bottom, region_left, region_right);
                    Theme.setTextFill("background", lbl_welcome, lbl_main, lbl_date, lbl_time);
                    Theme.setTextFill("border", lbl_userName, btn_dashboard, btn_manageCompany, btn_manageAdmins, btn_changeTheme, btn_changePass, btn_lock);
                    Theme.setTextFill("warning", btn_exit);
                    Theme.setTextFill("font", lbl_shortcuts);*/
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}