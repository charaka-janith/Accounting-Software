package front_end.ui.company;

import back_end.bo.BOFactory;
import back_end.bo.custom.QueryBO;
import back_end.dto.BankBookDTO;
import back_end.dto.CashBookDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.tm.BankBookTM;
import front_end.tm.CashBookTM;
import front_end.ui.dashboard.CompanyDashboardController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BankBookController implements Initializable {

    private final QueryBO queryBO = (QueryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.QUERY);

    @FXML
    private JFXButton btn_search;

    @FXML
    private DatePicker datePicker_endDate;

    @FXML
    private DatePicker datePicker_startDate;

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
    private TableView<BankBookTM> tbl_bankBook;

    @FXML
    void btn_search_onAction(ActionEvent event) {
        setTable();
    }

    @FXML
    void btn_search_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            tbl_bankBook.requestFocus();
        }
    }

    @FXML
    void datePicker_startDate_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_search.requestFocus();
        }
    }

    @FXML
    void datePicker_endDate_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            datePicker_startDate.requestFocus();
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
        tbl_bankBook.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tbl_bankBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_bankBook.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tbl_bankBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("number"));
        tbl_bankBook.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tbl_bankBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cheque"));
        tbl_bankBook.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tbl_bankBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_bankBook.getColumns().get(4).setStyle("-fx-alignment: CENTER_RIGHT;");
        tbl_bankBook.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("credit"));
        tbl_bankBook.getColumns().get(5).setStyle("-fx-alignment: CENTER_RIGHT;");
        tbl_bankBook.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("debit"));
        tbl_bankBook.getColumns().get(6).setStyle("-fx-alignment: CENTER_RIGHT;");
        tbl_bankBook.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("balance"));
        tbl_bankBook.getColumns().get(7).setStyle("-fx-alignment: CENTER;");
        tbl_bankBook.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("ledger"));
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
                ArrayList<BankBookDTO> bankBook = null;
                ArrayList<BankBookTM> rowList = new ArrayList<>();
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
                    bankBook = queryBO.get_Bankbook(startDate, endDate);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                assert bankBook != null;
                for (BankBookDTO book :
                        bankBook) {
                    if (book.getTable().equals("receipt")) {
                        balance = balance + book.getAmount();
                    } else {
                        balance = balance - book.getAmount();
                    }
                    rowList.add(new BankBookTM(
                            book.getDate().toString(),
                            book.getNumber(),
                            book.getCheque(),
                            book.getDescription(),
                            book.getTable().equals("receipt") ? Integer.toString(book.getAmount()) : "-",
                            book.getTable().equals("voucher") ? Integer.toString(book.getAmount()) : "-",
                            balance,
                            book.getLedger()
                    ));
                }
                tbl_bankBook.setItems(FXCollections.observableArrayList(rowList));
            });
        }).start();
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("බැංකු පොත");
                lbl_startDate.setText("ආරම්භක දිනය");
                lbl_endDate.setText("අවසාන දිනය");
                btn_search.setText(" සොයන්න [F1]");
                tbl_bankBook.getColumns().get(0).setText("දිනය");
                tbl_bankBook.getColumns().get(1).setText("අංකය");
                tbl_bankBook.getColumns().get(2).setText("චෙක්පත");
                tbl_bankBook.getColumns().get(3).setText("විස්තර");
                tbl_bankBook.getColumns().get(4).setText("බැර");
                tbl_bankBook.getColumns().get(5).setText("හර");
                tbl_bankBook.getColumns().get(6).setText("ශේෂය");
                tbl_bankBook.getColumns().get(7).setText("ලෙජරය");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Bank Book");
                lbl_startDate.setText("Start Date");
                lbl_endDate.setText("End Date");
                btn_search.setText(" Search [F1]");
                tbl_bankBook.getColumns().get(0).setText("Date");
                tbl_bankBook.getColumns().get(1).setText("Number");
                tbl_bankBook.getColumns().get(2).setText("Cheque");
                tbl_bankBook.getColumns().get(3).setText("Description");
                tbl_bankBook.getColumns().get(4).setText("Credit");
                tbl_bankBook.getColumns().get(5).setText("Debit");
                tbl_bankBook.getColumns().get(6).setText("Balance");
                tbl_bankBook.getColumns().get(7).setText("Ledger");
            })).start();
        }
    }

    private void setColors() {
        Platform.runLater(() -> {
            // background
            Theme.setBackgroundColor("background", pane);
            Theme.setBackgroundColor("success", btn_search);
            // text
            Theme.setTextFill("background", btn_search);
            Theme.setTextFill("success", lbl_main);
            Theme.setTextFill("font", lbl_startDate, lbl_endDate);
            // icon
            Theme.setIconFill("background", icon_save);
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
                }
            });
        });
    }
}
