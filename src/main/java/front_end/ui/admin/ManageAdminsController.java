package front_end.ui.admin;

import back_end.bo.BOFactory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.tm.AdminTM;
import front_end.ui.dashboard.AdminDashboardController;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import static front_end.ui.dashboard.AdminDashboardController.stage;

public class ManageAdminsController implements Initializable {

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    private JFXButton btn_create;

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_userName;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<AdminTM> tbl_manageAdmin;

    @FXML
    private TextField txt_userName;

    @FXML
    private FontAwesomeIconView icon_refresh;

    @FXML
    private FontAwesomeIconView icon_save;

    @FXML
    void txt_userName_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
         tbl_manageAdmin.requestFocus();
        }
    }

    @FXML
    void btn_create_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            txt_userName.requestFocus();
        }
    }

    @FXML
    void txt_userName_onAction(ActionEvent event) {
        createAdmin();
    }

    private void createAdmin() {
        if (txt_userName.getText().equals("")){
            Theme.giveBorderWarning(txt_userName);
            Theme.giveAWarning(
                    Session.isSinhala()
                            ? "වලංගු නොවන පරිශීලක නාමයකි, කරුණාකර වෙනත් පරිශීලක නාමයක් උත්සාහ කරන්න !"
                            : "Invalid Username, Please try another Username !",
                    AdminDashboardController.windowMsg,
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
                added = userBO.addUser(new UserDTO(
                        txt_userName.getText(),
                        null,
                        "admin"
                ));
            } catch (SQLIntegrityConstraintViolationException e) {
                Theme.giveBorderWarning(txt_userName);
                Theme.giveAWarning(
                        Session.isSinhala()
                                ? "පරිශීලක නාමය දැනටමත් පවතී, කරුණාකර වෙනත් පරිශීලක නාමයක් උත්සාහ කරන්න !"
                                : "Username already exists, Please try another Username !",
                        AdminDashboardController.windowMsg,
                        Session.admin_mainLabel,
                        Session.admin_regionBack,
                        Session.admin_regionTop,
                        Session.admin_regionBottom,
                        Session.admin_regionLeft,
                        Session.admin_regionRight
                );
            } catch (UnsupportedEncodingException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | SQLException e) {
                e.printStackTrace();
            }
            if (added) {
                Theme.successGif(AdminDashboardController.stage);
                setTable();
            }
        }
        refresh();
    }

    @FXML
    void btn_create_onAction(ActionEvent event) {
        createAdmin();
    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {
        refresh();
    }

    private void refresh () {
        txt_userName.setText("");
        txt_userName.requestFocus();
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("පරිපාලක කළමනාකරණය කිරීම");
                lbl_userName.setText("පරිශීලක නාමය");
                txt_userName.setPromptText("පරිශීලක නාමය ඇතුළත් කරන්න");
                tbl_manageAdmin.getColumns().get(0).setText("පරිශීලක නාමය");
                tbl_manageAdmin.getColumns().get(1).setText("මකාදැමීම්");
                btn_create.setText(" පරිපාලක නිර්මාණය කරන්න [F1]");
                btn_refresh.setText(" නැවුම් කරන්න [F5]");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Manage Admins");
                lbl_userName.setText("Username");
                txt_userName.setPromptText("Enter Username");
                tbl_manageAdmin.getColumns().get(0).setText("Username");
                tbl_manageAdmin.getColumns().get(1).setText("Delete");
                btn_create.setText(" Create Admin [F1]");
                btn_refresh.setText(" Refresh [F5]");
            })).start();
        }
        setTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(txt_userName);
        runLater();
        Theme.setChangeListeners(txt_userName);
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
                Theme.setTextFill("font", lbl_userName);
                // icon
                Theme.setIconFill("background", icon_refresh, icon_save);
            });
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    createAdmin();
                } else if (event.getCode().equals(KeyCode.F5)) {
                    refresh();
                }
            });
        });
    }

    private void initTable() {
        tbl_manageAdmin.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tbl_manageAdmin.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tbl_manageAdmin.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
        tbl_manageAdmin.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        setTable();
    }

    private void setTable() {
        ArrayList<UserDTO> allAdmins = null;
        ArrayList<AdminTM> rowList = new ArrayList<>();
        try {
            allAdmins = userBO.getAllAdmins();
        } catch (SQLException | ClassNotFoundException | UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        assert allAdmins != null;
        for (UserDTO admin :
                allAdmins) {
            JFXButton btn_delete = new JFXButton(Session.isSinhala() ? "පරිපාලක මකන්න" : "Delete Admin");
            btn_delete.setStyle("-fx-background-color: " + Theme.warning);
            btn_delete.setFont(Font.font("System Bold", FontWeight.BOLD, 13));
            btn_delete.setTextFill(Paint.valueOf(Theme.background));
            btn_delete.setPrefSize(500, 30);
            btn_delete.setOnMouseMoved(mouseEvent -> {
                btn_delete.arm();
            });
            btn_delete.setOnAction((ActionEvent event) -> {
                if (Session.getUser().getName().equals(admin.getName())) {
                    Theme.giveAWarning(
                            Session.isSinhala()
                                    ? "ඔබට ලොග් වී ඇති ගිණුම මකා දැමිය නොහැක !"
                                    : "You can't delete the logged in account !",
                            AdminDashboardController.windowMsg,
                            Session.admin_mainLabel,
                            Session.admin_regionBack,
                            Session.admin_regionTop,
                            Session.admin_regionBottom,
                            Session.admin_regionLeft,
                            Session.admin_regionRight
                    );
                } else {
                    AtomicBoolean b = new AtomicBoolean(false);
                    Platform.runLater(() -> {
                        b.set(Theme.confirmPopup(
                                Session.isSinhala()
                                        ? "ඔබට මෙම පරිපාලක මකා දැමීමට අවශ්\u200Dය බව විශ්වාසද ?"
                                        : "Are you sure you want to Delete this admin ?",
                                AdminDashboardController.stage
                        ));
                        if (b.get()) {
                            try {
                                if (userBO.deleteUser(admin.getName()))
                                    Theme.successGif(AdminDashboardController.stage);
                                setTable();
                                txt_userName.requestFocus();
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            rowList.add(new AdminTM(
                    admin.getName(),
                    btn_delete
            ));
        }
        tbl_manageAdmin.setItems(FXCollections.observableArrayList(rowList));
    }
}
