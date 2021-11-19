package front_end.ui.admin;

import back_end.bo.BOFactory;
import back_end.bo.custom.CompanyBO;
import back_end.dto.CompanyDTO;
import com.jfoenix.controls.JFXButton;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

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

    //    ..........................................Action Events........................................
    @FXML
    void txt_name_onAction(ActionEvent event) {
        txt_userName.requestFocus();
    }

    @FXML
    void txt_userName_onAction(ActionEvent event) {
        txt_address.requestFocus();
    }

    @FXML
    void txt_address_onAction(MouseEvent event) {
        txt_phoneNumber.requestFocus();
    }

    @FXML
    void txt_phoneNumber_onAction(ActionEvent event) {
        txt_email.requestFocus();
    }

    @FXML
    void txt_email_onAction(ActionEvent event) {
        txt_website.requestFocus();
    }

    @FXML
    void txt_website_onAction(ActionEvent event) {
        txt_businessRegistrationNumber.requestFocus();
    }

    @FXML
    void txt_businessRegistrationNumber_onAction(ActionEvent event) {
        //.....TODO----->Call Save or update method
        setCompanyDetails();
    }

    @FXML
    void btn_save_onAction(ActionEvent event) {
        //.....TODO----->Call Save or update method
        setCompanyDetails();
    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {
        setCompanyDetails();
    }

    private void setCompanyDetails() {
        try {
            CompanyDTO newCompany = companyBO.getCompany();
            lbl_main.setText("Update Company");
            btn_save.setText("Update [F1]");
            txt_name.setText(newCompany.getName());
            txt_address.setText(newCompany.getAddress());
            txt_phoneNumber.setText(newCompany.getPhoneNumber());
            txt_email.setText(newCompany.getEmail());
            txt_website.setText(newCompany.getWebSite());
            txt_businessRegistrationNumber.setText(newCompany.getBrn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new RunLater(txt_name);
        setColors();
        Theme.setChangeListeners(txt_name, txt_userName, txt_phoneNumber, txt_email, txt_website, txt_businessRegistrationNumber);
        Platform.runLater(() -> {
            txt_address.textProperty().addListener((observableValue, s, t1) -> txt_address.setStyle("-fx-border-color: transparent"));
        });
        new Thread(() -> {
            try {
                CompanyDTO company = companyBO.getCompany();
                if (null != company) {
                    Platform.runLater(() -> {
                        setCompanyDetails();
                    });
                } else {
                    Platform.runLater(() -> {
                        lbl_main.setText("Create Company");
                        btn_save.setText("Create [F1]");
                        txt_name.setText("");
                        txt_address.setText("");
                        txt_phoneNumber.setText("");
                        txt_email.setText("");
                        txt_website.setText("");
                        txt_businessRegistrationNumber.setText("");
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
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