package front_end.ui.admin;

import back_end.bo.BOFactory;
import back_end.bo.custom.CompanyBO;
import back_end.dto.CompanyDTO;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageCompanyController implements Initializable {

    CompanyBO bo = (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);

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

    @FXML
    void btn_save_keyReleased(KeyEvent event) {

    }

    @FXML
    void btn_save_onAction(ActionEvent event) {

    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {

    }

    @FXML
    void txt_address_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_address_onAction(MouseEvent event) {

    }

    @FXML
    void txt_businessRegistrationNumber_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_businessRegistrationNumber_onAction(ActionEvent event) {

    }

    @FXML
    void txt_email_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_email_onAction(ActionEvent event) {

    }

    @FXML
    void txt_name_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_name_onAction(ActionEvent event) {

    }

    @FXML
    void txt_phoneNumber_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_phoneNumber_onAction(ActionEvent event) {

    }

    @FXML
    void txt_userName_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_userName_onAction(ActionEvent event) {

    }

    @FXML
    void txt_website_keyReleased(KeyEvent event) {

    }

    @FXML
    void txt_website_onAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                CompanyDTO company = bo.getCompany();
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
}