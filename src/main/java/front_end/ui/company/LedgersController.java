package front_end.ui.company;

import back_end.bo.BOFactory;
import back_end.bo.custom.LedgerBO;
import back_end.dto.LedgerDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.tm.LedgersTM;
import front_end.ui.dashboard.CompanyDashboardController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LedgersController implements Initializable {

    private final LedgerBO ledgerBO = (LedgerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LEDGER);

    @FXML
    private JFXButton btn_create;

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private FontAwesomeIconView icon_refresh;

    @FXML
    private FontAwesomeIconView icon_save;

    @FXML
    private Label lbl_acctName;

    @FXML
    private Label lbl_main;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<LedgersTM> tbl_ledgerAcct;

    @FXML
    private TextField txt_acctName;

    @FXML
    void btn_create_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_acctName.requestFocus();
        }
    }

    @FXML
    void btn_create_onAction(ActionEvent event) {
        create();
    }

    private void create() {
        if (txt_acctName.getText().equals("")) {
            Theme.giveBorderWarning(txt_acctName);
            Theme.giveAWarning(
                    Session.isSinhala()
                            ? "ගිණුමේ නම වලංගු නැත, කරුණාකර වෙනත් නාමයක් උත්සාහ කරන්න !"
                            : "Invalid Account Name, Please try another Name !",
                    CompanyDashboardController.windowMsg,
                    Session.admin_mainLabel,
                    Session.admin_regionBack,
                    Session.admin_regionTop,
                    Session.admin_regionBottom,
                    Session.admin_regionLeft,
                    Session.admin_regionRight
            );
        } else {
            boolean added = false;
            try {
                added = ledgerBO.addLedger(new LedgerDTO(
                        0, txt_acctName.getText()
                ));
            } catch (SQLIntegrityConstraintViolationException e) {
                Theme.giveBorderWarning(txt_acctName);
                Theme.giveAWarning(
                        Session.isSinhala()
                                ? "ලෙජර් ගිණුම දැනටමත් පවතී, කරුණාකර වෙනත් නමක් උත්සාහ කරන්න !"
                                : "Ledger Account already exists, Please try another Name !",
                        CompanyDashboardController.windowMsg,
                        Session.admin_mainLabel,
                        Session.admin_regionBack,
                        Session.admin_regionTop,
                        Session.admin_regionBottom,
                        Session.admin_regionLeft,
                        Session.admin_regionRight
                );
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            if (added) {
                Theme.successGif(CompanyDashboardController.stage);
                setTable();
            }
        }
        refresh();
    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {
        refresh();
    }

    private void refresh() {
        txt_acctName.setText("");
        txt_acctName.requestFocus();
    }

    @FXML
    void txt_acctName_onAction(ActionEvent event) {
        create();
    }

    @FXML
    void txt_acctName_onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            tbl_ledgerAcct.requestFocus();
        }
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("පරිපාලක කළමනාකරණය කිරීම");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Ledger Accounts List");
                lbl_acctName.setText("Account Name");
                txt_acctName.setPromptText("Enter Account Name");
                tbl_ledgerAcct.getColumns().get(0).setText("Account");
                tbl_ledgerAcct.getColumns().get(1).setText("Go To");
                btn_create.setText(" Create Ledger [F1]");
                btn_refresh.setText(" Refresh [F5]");
            })).start();
        }
        setTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(txt_acctName);
        runLater();
        Theme.setChangeListeners(txt_acctName);
        initTable();
    }

    private void setColors() {
        Platform.runLater(() -> {
            // background
            Theme.setBackgroundColor("background", pane);
            Theme.setBackgroundColor("success", btn_create);
            Theme.setBackgroundColor("border", btn_refresh);
            // text
            Theme.setTextFill("background", btn_refresh, btn_create);
            Theme.setTextFill("success", lbl_main);
            Theme.setTextFill("font", lbl_acctName);
            // icon
            Theme.setIconFill("background", icon_refresh, icon_save);
        });
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    create();
                } else if (event.getCode().equals(KeyCode.F5)) {
                    refresh();
                }
            });
        });
    }

    private void initTable() {
        tbl_ledgerAcct.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tbl_ledgerAcct.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tbl_ledgerAcct.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("acct"));
        tbl_ledgerAcct.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("goTo"));
        setTable();
    }

    private void setTable() {
        ArrayList<LedgerDTO> allLedgers = null;
        ArrayList<LedgersTM> rowList = new ArrayList<>();
        try {
            allLedgers = ledgerBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert allLedgers != null;
        for (LedgerDTO ledger :
                allLedgers) {
            JFXButton btn_goTo = new JFXButton(Session.isSinhala() ? "ලෙජරය විවෘත කරන්න" : "Open the Ledger");
            btn_goTo.setStyle("-fx-background-color: " + Theme.success);
            btn_goTo.setFont(Font.font("System Bold", FontWeight.BOLD, 13));
            btn_goTo.setTextFill(Paint.valueOf(Theme.background));
            btn_goTo.setPrefSize(500, 30);
            btn_goTo.setOnMouseMoved(mouseEvent -> {
                btn_goTo.arm();
            });
            btn_goTo.setOnAction((ActionEvent event) -> {
                System.out.println("LedgersController.setTable");
            });
            rowList.add(new LedgersTM(
                    ledger.getName(),
                    btn_goTo
            ));
        }
        tbl_ledgerAcct.setItems(FXCollections.observableArrayList(rowList));
    }
}
