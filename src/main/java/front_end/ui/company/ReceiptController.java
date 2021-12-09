package front_end.ui.company;

import back_end.bo.BOFactory;
import back_end.bo.custom.LedgerBO;
import back_end.bo.custom.ReceiptBO;
import back_end.dto.LedgerDTO;
import back_end.dto.ReceiptDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.regex.RegexManager;
import front_end.sessions.Session;
import front_end.ui.dashboard.CompanyDashboardController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReceiptController implements Initializable {

    private final ReceiptBO receiptBO = (ReceiptBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RECEIPT);
    private final LedgerBO ledgerBO = (LedgerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LEDGER);

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_save;

    @FXML
    private ComboBox<String> cmb_ledger;

    @FXML
    private DatePicker datePicker_date;

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
    private Label lbl_ledger;

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
        txt_number.requestFocus();
        datePicker_date.setValue(LocalDate.now());
        txt_description.setText("");
        txt_amount.setText("");
        cmb_ledger.getSelectionModel().clearSelection();
        cmb_ledger.setPromptText(Session.isSinhala() ? "ලෙජර් ගිණුම" : "Ledger Account");
    }

    @FXML
    void btn_save_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_refresh.requestFocus();
        }
    }

    @FXML
    void btn_save_onAction(ActionEvent event) {
        submit();
    }

    private void submit() {
        if (RegexManager.verify_number(txt_number.getText())) {
            try {
                LocalDate parsedDate = LocalDate.parse(datePicker_date.getEditor().getText(), DateTimeFormatter.ofPattern("M/d/yyyy"));
                if (null != cmb_ledger.getSelectionModel().getSelectedItem()) {
                    if (RegexManager.verify_amount(txt_amount.getText())) {
                        try {
                            boolean saved;
                            if (null != receiptBO.searchReceipt(Integer.parseInt(txt_number.getText()))) {
                                saved = receiptBO.updateReceipt(new ReceiptDTO(
                                        Integer.parseInt(txt_number.getText()),
                                        ledgerBO.search_byName(cmb_ledger.getValue()).getId(),
                                        parsedDate,
                                        txt_description.getText(),
                                        Integer.parseInt(txt_amount.getText())
                                ));
                            } else {
                                saved = receiptBO.addReceipt(new ReceiptDTO(
                                        Integer.parseInt(txt_number.getText()),
                                        ledgerBO.search_byName(cmb_ledger.getValue()).getId(),
                                        parsedDate,
                                        txt_description.getText(),
                                        Integer.parseInt(txt_amount.getText())
                                ));
                            }
                            if (saved) {
                                Theme.successGif(CompanyDashboardController.stage);
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ignored) {}
                                    Platform.runLater(this::refresh);
                                }).start();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Theme.giveBorderWarning(txt_amount);
                        txt_amount.requestFocus();
                        Theme.giveAWarning(
                                Session.isSinhala()
                                        ? "මුදල වලංගු නොවේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                        : "Invalid Amount, Please try again !",
                                CompanyDashboardController.windowMsg,
                                Session.admin_mainLabel,
                                Session.admin_regionBack,
                                Session.admin_regionTop,
                                Session.admin_regionBottom,
                                Session.admin_regionLeft,
                                Session.admin_regionRight
                        );
                    }
                } else {
                    Theme.giveBorderWarning(cmb_ledger);
                    cmb_ledger.requestFocus();
                    Theme.giveAWarning(
                            Session.isSinhala()
                                    ? "ලෙජර් ගිණුම් තේරීම වලංගු නොවේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                    : "Invalid Ledger Account Selection, Please try again !",
                            CompanyDashboardController.windowMsg,
                            Session.admin_mainLabel,
                            Session.admin_regionBack,
                            Session.admin_regionTop,
                            Session.admin_regionBottom,
                            Session.admin_regionLeft,
                            Session.admin_regionRight
                    );
                }
            } catch (DateTimeException e) {
                datePicker_date.requestFocus();
                Theme.giveAWarning(
                        Session.isSinhala()
                                ? "දිනය තේරීම වලංගු නොවේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                : "Invalid Date Selection, Please try again !",
                        CompanyDashboardController.windowMsg,
                        Session.admin_mainLabel,
                        Session.admin_regionBack,
                        Session.admin_regionTop,
                        Session.admin_regionBottom,
                        Session.admin_regionLeft,
                        Session.admin_regionRight
                );
            }
        } else {
            Theme.giveBorderWarning(txt_number);
            txt_number.setText("");
            txt_number.requestFocus();
            Theme.giveAWarning(
                    Session.isSinhala()
                            ? "කුවිතාන්සි අංකය වලංගු නැත, කරුණාකර නැවත උත්සාහ කරන්න !"
                            : "Invalid Receipt Number, Please try again !",
                    CompanyDashboardController.windowMsg,
                    Session.admin_mainLabel,
                    Session.admin_regionBack,
                    Session.admin_regionTop,
                    Session.admin_regionBottom,
                    Session.admin_regionLeft,
                    Session.admin_regionRight
            );
        }
    }

    @FXML
    void cmb_ledger_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_description.requestFocus();
        }
    }

    @FXML
    void datePicker_date_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_number.requestFocus();
        }
    }

    @FXML
    void txt_amount_onAction(ActionEvent event) {
        submit();
    }

    @FXML
    void txt_amount_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            cmb_ledger.requestFocus();
        }
    }

    @FXML
    void txt_description_onAction(ActionEvent event) {
        cmb_ledger.requestFocus();
    }

    @FXML
    void txt_description_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            datePicker_date.requestFocus();
        }
    }

    @FXML
    void txt_number_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_save.requestFocus();
        } else if (event.getCode().equals(KeyCode.ENTER)){
            datePicker_date.requestFocus();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(txt_number);
        runLater();
        Theme.setChangeListeners(txt_number, txt_description, txt_amount);
        Theme.setChangeListeners(cmb_ledger);
        setFocusListeners();
        setSupplementaryListeners();
        setCmb();
    }

    private void setCmb() {
        new Thread(() -> {
            Platform.runLater(() -> {
                cmb_ledger.getItems().clear();
                ArrayList<LedgerDTO> allLedgers = null;
                try {
                    allLedgers = ledgerBO.getAll();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                assert allLedgers != null;
                if (0 == allLedgers.size()) {
                    Theme.giveBorderWarning(cmb_ledger);
                    Theme.giveAWarning(
                            Session.isSinhala()
                                    ? "ලෙජර් ගිණුම් නැත, කරුණාකර පළමුව ලෙජර් ගිණුම් එක් කරන්න !"
                                    : "No Ledger Accounts, Please add Ledger Accounts first !",
                            CompanyDashboardController.windowMsg,
                            Session.admin_mainLabel,
                            Session.admin_regionBack,
                            Session.admin_regionTop,
                            Session.admin_regionBottom,
                            Session.admin_regionLeft,
                            Session.admin_regionRight
                    );
                } else {
                    for (LedgerDTO ledger :
                            allLedgers) {
                        cmb_ledger.getItems().add(ledger.getName());
                    }
                }
            });
        }).start();
    }

    private void setFocusListeners() {
        datePicker_date.focusedProperty().addListener((__, ___, isFocused) ->
        {
            if (isFocused) {
                datePicker_date.show();
            }
        });
        cmb_ledger.focusedProperty().addListener((__, ___, isFocused) ->
        {
            if (isFocused) {
                cmb_ledger.show();
                if (null == cmb_ledger.getSelectionModel().getSelectedItem())
                    cmb_ledger.getSelectionModel().selectFirst();
            }
        });
        txt_number.focusedProperty().addListener((__, ___, isFocused) ->
        {
            if (!isFocused && !txt_number.getText().equals("")) {
                if (RegexManager.verify_number(txt_number.getText())) {
                    try {
                        ReceiptDTO receipt = receiptBO.searchReceipt(Integer.parseInt(txt_number.getText()));
                        if (null != receipt) {
                            datePicker_date.setValue(receipt.getDate());
                            txt_description.setText(receipt.getDescription());
                            txt_amount.setText(Integer.toString(receipt.getAmount()));
                            cmb_ledger.getSelectionModel().select(ledgerBO.search_byId(receipt.getLedger()).getName());
                            lbl_main.setText(Session.isSinhala()
                                    ? "කුවිතාන්සි යාවත්කාලීන කිරීම"
                                    : "Update Receipt"
                            );
                            btn_save.setText(Session.isSinhala()
                                    ? " යාවත්කාලීන කරන්න [F1]"
                                    : " Update [F1]"
                            );
                        } else {
                            datePicker_date.setValue(LocalDate.now());
                            txt_description.setText("");
                            txt_amount.setText("");
                            cmb_ledger.getSelectionModel().clearSelection();
                            lbl_main.setText(Session.isSinhala()
                                    ? "කුවිතාන්සි එකතු කිරීම"
                                    : "Add Receipt"
                            );
                            btn_save.setText(Session.isSinhala()
                                    ? " ඇතුල් කරන්න [F1]"
                                    : " Add [F1]"
                            );
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Theme.giveBorderWarning(txt_number);
                    txt_number.setText("");
                    Theme.giveAWarning(
                            Session.isSinhala()
                                    ? "කුවිතාන්සි අංකය වලංගු නැත, කරුණාකර නැවත උත්සාහ කරන්න !"
                                    : "Invalid Receipt Number, Please try again !",
                            CompanyDashboardController.windowMsg,
                            Session.admin_mainLabel,
                            Session.admin_regionBack,
                            Session.admin_regionTop,
                            Session.admin_regionBottom,
                            Session.admin_regionLeft,
                            Session.admin_regionRight
                    );
                }
            }
        });
    }

    private void setSupplementaryListeners() {
        datePicker_date.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txt_description.requestFocus();
            }
        });
        datePicker_date.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                txt_description.requestFocus();
        });
        cmb_ledger.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> cell = new ListCell<String>() {
                            @Override
                            public void updateItem(String item,
                                                   boolean empty) {
                                super.updateItem(item, empty);
                                setText(item);
                            }
                        };
                        param.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                            txt_amount.requestFocus();
                        });
                        return cell;
                    }
                });

        cmb_ledger.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txt_amount.requestFocus();
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
                lbl_ledger.setText("ලෙජර් ගිණුම");
                cmb_ledger.setPromptText("ලෙජර් ගිණුම");
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
                lbl_ledger.setText("Ledger Account");
                cmb_ledger.setPromptText("Ledger Account");
                btn_save.setText(" Add [F1]");
                btn_refresh.setText(" Refresh [F5]");
            })).start();
        }
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    submit();
                } else if (event.getCode().equals(KeyCode.F5)) {
                    refresh();
                }
            });
        });
        refresh();
    }
}
