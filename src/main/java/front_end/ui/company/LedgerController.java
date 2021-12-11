
package front_end.ui.company;

import back_end.bo.BOFactory;
import back_end.bo.custom.LedgerBO;
import back_end.bo.custom.QueryBO;
import back_end.dto.CashBookDTO;
import back_end.dto.LedgerDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.PaneOpenAnim;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.tm.CashBookTM;
import front_end.tm.LedgerTM;
import front_end.ui.dashboard.CompanyDashboardController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class LedgerController implements Initializable {

    public static String ledger_name;
    private final QueryBO queryBO = (QueryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.QUERY);
    private final LedgerBO ledgerBO = (LedgerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LEDGER);
    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_goBack;

    @FXML
    private JFXButton btn_search;

    @FXML
    private DatePicker datePicker_endDate;

    @FXML
    private DatePicker datePicker_startDate;

    @FXML
    private FontAwesomeIconView icon_delete;

    @FXML
    private FontAwesomeIconView icon_goBack;

    @FXML
    private FontAwesomeIconView icon_save;

    @FXML
    private Label lbl_endDate;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_startDate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<LedgerTM> tbl_ledger;

    @FXML
    void btn_delete_onAction(ActionEvent event) {
        exit_popup();
    }

    private void exit_popup() {
        AtomicBoolean b = new AtomicBoolean(false);
        Platform.runLater(() -> {
            b.set(Theme.confirmPopup(
                    Session.isSinhala() ? "ඔබට මැකීමට අවශ්ය බව විශ්වාසද ?" : "Are you sure you want to Delete ?",
                    CompanyDashboardController.stage
            ));
            if (b.get()) {
                try {
                    boolean deleted = ledgerBO.delete_ledger(ledgerBO.search_byName(ledger_name).getId());
                    if (deleted) {
                        Theme.successGif(CompanyDashboardController.stage);
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ignored) {
                            }
                            Platform.runLater(this::goBack);
                        }).start();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void btn_delete_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_goBack.requestFocus();
        }
    }

    @FXML
    void btn_goBack_onAction(ActionEvent event) {
        goBack();
    }

    private void goBack() {
        Session.getSubPane().getChildren().clear();
        try {
            Session.getSubPane().getChildren().add(FXMLLoader.load(Objects.requireNonNull(
                    LedgersController.class.getResource("Ledgers.fxml")
            )));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new PaneOpenAnim(Session.getSubPane());
    }

    @FXML
    void btn_goBack_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_search.requestFocus();
        }
    }

    @FXML
    void btn_search_onAction(ActionEvent event) {
        setTable();
    }

    @FXML
    void btn_search_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_goBack.requestFocus();
        }
    }

    @FXML
    void datePicker_endDate_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            datePicker_startDate.requestFocus();
        }
    }

    @FXML
    void datePicker_startDate_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_search.requestFocus();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(datePicker_startDate);
        runLater();
        setSupplementaryListeners();
        initTable();
    }

    private void initTable() {
        tbl_ledger.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tbl_ledger.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_ledger.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tbl_ledger.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("number"));
        tbl_ledger.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tbl_ledger.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_ledger.getColumns().get(3).setStyle("-fx-alignment: CENTER_RIGHT;");
        tbl_ledger.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("credit"));
        tbl_ledger.getColumns().get(4).setStyle("-fx-alignment: CENTER_RIGHT;");
        tbl_ledger.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("debit"));
        tbl_ledger.getColumns().get(5).setStyle("-fx-alignment: CENTER_RIGHT;");
        tbl_ledger.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("balance"));
        datePicker_startDate.setValue(YearMonth.now().atDay(1));
        datePicker_endDate.setValue(YearMonth.now().atEndOfMonth());
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            setTable();
        }).start();
    }

    private void setTable() {
        new Thread(() -> {
            Platform.runLater(() -> {
                int balance = 0;
                ArrayList<LedgerDTO> ledger = null;
                ArrayList<LedgerTM> rowList = new ArrayList<>();
                try {
                    LocalDate startDate;
                    LocalDate endDate;
                    try {
                        startDate = LocalDate.parse(datePicker_startDate.getEditor().getText(), DateTimeFormatter.ofPattern("M/d/yyyy"));
                    } catch (DateTimeException e) {
                        datePicker_startDate.requestFocus();
                        Theme.giveAWarning(
                                Session.isSinhala()
                                        ? "ආරම්භක දිනය තේරීම වලංගු නොවේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                        : "Invalid Start Date Selection, Please try again !",
                                CompanyDashboardController.windowMsg,
                                Session.admin_mainLabel,
                                Session.admin_regionBack,
                                Session.admin_regionTop,
                                Session.admin_regionBottom,
                                Session.admin_regionLeft,
                                Session.admin_regionRight
                        );
                        return;
                    }
                    try {
                        endDate = LocalDate.parse(datePicker_endDate.getEditor().getText(), DateTimeFormatter.ofPattern("M/d/yyyy"));
                    } catch (DateTimeException e) {
                        datePicker_endDate.requestFocus();
                        Theme.giveAWarning(
                                Session.isSinhala()
                                        ? "අවසාන දිනය තේරීම වලංගු නොවේ, කරුණාකර නැවත උත්සාහ කරන්න !"
                                        : "Invalid End Date Selection, Please try again !",
                                CompanyDashboardController.windowMsg,
                                Session.admin_mainLabel,
                                Session.admin_regionBack,
                                Session.admin_regionTop,
                                Session.admin_regionBottom,
                                Session.admin_regionLeft,
                                Session.admin_regionRight
                        );
                        return;
                    }
                    ledger = queryBO.get_Ledger(startDate, endDate, ledger_name);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                assert ledger != null;
                for (LedgerDTO row :
                        ledger) {
                    if (row.getTable().equals("receipt")) {
                        balance = balance + row.getAmount();
                    } else {
                        balance = balance - row.getAmount();
                    }
                    rowList.add(new LedgerTM(
                            row.getDate().toString(),
                            row.getNumber(),
                            row.getDescription(),
                            row.getTable().equals("receipt") ? Integer.toString(row.getAmount()) : "-",
                            row.getTable().equals("voucher") ? Integer.toString(row.getAmount()) : "-",
                            balance
                    ));
                }
                tbl_ledger.setItems(FXCollections.observableArrayList(rowList));
            });
        }).start();
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText(ledger_name);
                lbl_startDate.setText("ආරම්භක දිනය");
                lbl_endDate.setText("අවසාන දිනය");
                btn_search.setText(" සොයන්න [F1]");
                btn_goBack.setText(" ආපසු යන්න [F4]");
                btn_delete.setText(" ලෙජරය මකන්න");
                tbl_ledger.getColumns().get(0).setText("දිනය");
                tbl_ledger.getColumns().get(1).setText("අංකය");
                tbl_ledger.getColumns().get(2).setText("විස්තර");
                tbl_ledger.getColumns().get(3).setText("බැර");
                tbl_ledger.getColumns().get(4).setText("හර");
                tbl_ledger.getColumns().get(5).setText("ශේෂය");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText(ledger_name);
                lbl_startDate.setText("Start Date");
                lbl_endDate.setText("End Date");
                btn_search.setText(" Search [F1]");
                btn_goBack.setText(" Go Back [F4]");
                btn_delete.setText(" Delete Ledger");
                tbl_ledger.getColumns().get(0).setText("Date");
                tbl_ledger.getColumns().get(1).setText("Number");
                tbl_ledger.getColumns().get(2).setText("Description");
                tbl_ledger.getColumns().get(3).setText("Credit");
                tbl_ledger.getColumns().get(4).setText("Debit");
                tbl_ledger.getColumns().get(5).setText("Balance");
            })).start();
        }
    }

    private void setColors() {
        Platform.runLater(() -> {
            // background
            Theme.setBackgroundColor("background", pane);
            Theme.setBackgroundColor("success", btn_search);
            Theme.setBackgroundColor("warning", btn_delete);
            Theme.setBackgroundColor("border", btn_goBack);
            // text
            Theme.setTextFill("background", btn_search, btn_delete, btn_goBack);
            Theme.setTextFill("success", lbl_main);
            Theme.setTextFill("font", lbl_startDate, lbl_endDate);
            // icon
            Theme.setIconFill("background", icon_save, icon_delete, icon_goBack);
        });
    }

    private void setSupplementaryListeners() {
        datePicker_startDate.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                datePicker_endDate.requestFocus();
            }
        });
        datePicker_endDate.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                setTable();
            }
        });
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    setTable();
                } else if (event.getCode().equals(KeyCode.F4)) {
                    goBack();
                }
            });
        });
    }
}
