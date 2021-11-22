package front_end.ui.settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ChangePasswordController {

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_save;

    @FXML
    private Label lbl_currentPass;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_newPass;

    @FXML
    private Label lbl_newPass2;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txt_currentPass;

    @FXML
    private TextField txt_newPass;

    @FXML
    private TextField txt_newPass2;

    //    ..........................................Key Events........................................

    @FXML
    void txt_currentPass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_currentPass.requestFocus();
        }
    }

    @FXML
    void txt_newPass_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_currentPass.requestFocus();
        }
    }

    @FXML
    void txt_newPass2_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_newPass.requestFocus();
        }
    }

    @FXML
    void btn_save_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_newPass2.requestFocus();
        }
    }

    //    ..........................................Action Events........................................

    @FXML
    void txt_currentPass_onAction(ActionEvent event) {
        txt_newPass.requestFocus();
    }

    @FXML
    void txt_newPass_onAction(ActionEvent event) {
        txt_newPass2.requestFocus();
    }

    @FXML
    void txt_newPass2_onAction(ActionEvent event) {
//..........TODO-> Call Save password method
    }

    @FXML
    void btn_save_onAction(ActionEvent event) {
//..........TODO-> Call Save password method
    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {
        txt_currentPass.setText("");
        txt_newPass.setText("");
        txt_newPass2.setText("");
    }
}
