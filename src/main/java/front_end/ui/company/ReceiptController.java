package front_end.ui.company;

import back_end.bo.BOFactory;
import back_end.bo.custom.ReceiptBO;
import back_end.dto.ReceiptDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.regex.RegexManager;
import front_end.sessions.Session;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceiptController implements Initializable {

    private final ReceiptBO receiptBO = (ReceiptBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RECEIPT);

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_save;

    @FXML
    private DatePicker datePicker_date;
    private final ChangeListener<? super Boolean> datePicker_date_focused = (__, ___, isFocused) ->
    {
        if (isFocused) {
            datePicker_date.show();
        }
    };
    @FXML
    private FontAwesomeIconView icon_refresh;
    @FXML
    private FontAwesomeIconView icon_save;
    @FXML
    private Label lbl_amount;
    @FXML
    private Label lbl_date;
    @FXML
    private Label lbl_description;
    @FXML
    private Label lbl_main;
    @FXML
    private Label lbl_number;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txt_amount;
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_number;

    @FXML
    void btn_refresh_onAction(ActionEvent event) {
        refresh();
    }

    private void refresh() {
        txt_number.setText("");
        datePicker_date.setValue(LocalDate.now());
        txt_description.setText("");
        txt_amount.setText("");
    }

    @FXML
    void btn_save_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_refresh.requestFocus();
        }
    }

    @FXML
    void btn_save_onAction(ActionEvent event) {
        submitReceipt();
    }

    private void submitReceipt() {
        if (RegexManager.verify_number(txt_number.getText())) {
            try {
                receiptBO.addReceipt(new ReceiptDTO(
                        Integer.parseInt(txt_number.getText()),
                        datePicker_date.getValue(),
                        txt_description.getText(),
                        Integer.parseInt(txt_amount.getText())
                ));
            } catch (ParseException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    @FXML
    void datePicker_date_onAction(ActionEvent event) {
        txt_description.requestFocus();
    }

    @FXML
    void datePicker_date_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_number.requestFocus();
        }
    }

    @FXML
    void txt_amount_onAction(ActionEvent event) {
        submitReceipt();
    }

    @FXML
    void txt_amount_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_description.requestFocus();
        }
    }

    @FXML
    void txt_description_onAction(ActionEvent event) {
        txt_amount.requestFocus();
    }

    @FXML
    void txt_description_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            datePicker_date.requestFocus();
        }
    }

    @FXML
    void txt_number_onAction(ActionEvent event) {
        datePicker_date.requestFocus();
    }

    @FXML
    void txt_number_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_save.requestFocus();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(txt_number);
        runLater();
        Theme.setChangeListeners(txt_number, txt_description, txt_amount);
        setFocusListeners();
    }

    private void setFocusListeners() {
        datePicker_date.focusedProperty().addListener(datePicker_date_focused);
        datePicker_date.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txt_description.requestFocus();
            }
        });
    }

    private void setColors() {
        Platform.runLater(() -> {
            // background
            Theme.setBackgroundColor("background", pane);
            Theme.setBackgroundColor("success", btn_save);
            Theme.setBackgroundColor("border", btn_refresh);
            // text
            Theme.setTextFill("background", btn_refresh, btn_save);
            Theme.setTextFill("success", lbl_main);
            Theme.setTextFill("font",
                    lbl_number,
                    lbl_date,
                    lbl_description,
                    lbl_amount
            );
            // icon
            Theme.setIconFill("background", icon_refresh, icon_save);
        });
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("කුවිතාන්සි එකතු කිරීම");
                lbl_number.setText("අංකය");
                txt_number.setPromptText("අංකය ඇතුලත් කරන්න");
                lbl_date.setText("දිනය");
                lbl_description.setText("විස්තර");
                txt_description.setPromptText("විස්තර ඇතුලත් කරන්න");
                lbl_amount.setText("මුදල");
                txt_amount.setPromptText("මුදල ඇතුල් කරන්න");
                btn_save.setText(" ඇතුල් කරන්න [F1]");
                btn_refresh.setText(" නැවුම් කරන්න [F5]");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Add Receipt");
                lbl_number.setText("Number");
                txt_number.setPromptText("Enter Number");
                lbl_date.setText("Date");
                lbl_description.setText("Description");
                txt_description.setPromptText("Enter Description");
                lbl_amount.setText("Amount");
                txt_amount.setPromptText("Enter Amount");
                btn_save.setText(" Add [F1]");
                btn_refresh.setText(" Refresh [F5]");
            })).start();
        }
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    submitReceipt();
                } else if (event.getCode().equals(KeyCode.F5)) {
                    refresh();
                }
            });
        });
        refresh();
    }
}
