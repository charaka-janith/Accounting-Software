package front_end.ui.admin;

import back_end.bo.BOFactory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.tm.AdminTM;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    void txt_userName_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_refresh.requestFocus();
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
        try {
            createAdmin();
        } catch (SQLException e) {
            Theme.giveAWarning("Database config invalid", "Have A Great Day !", Session.admin_mainLabel, Session.admin_regionBack, Session.admin_regionTop, Session.admin_regionBottom, Session.admin_regionLeft, Session.admin_regionRight);
        } catch (ClassNotFoundException | UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    private void createAdmin () throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        boolean added = userBO.addUser(new UserDTO(
                txt_userName.getText(),
                null,
                "admin"
        ));
        if (added){
setTable();
        }
    }

    @FXML
    void btn_create_onAction(ActionEvent event) {
        try{
createAdmin();
        } catch (SQLException e) {
            Theme.giveAWarning("Database config invalid", "Have A Great Day !", Session.admin_mainLabel, Session.admin_regionBack, Session.admin_regionTop, Session.admin_regionBottom, Session.admin_regionLeft, Session.admin_regionRight);
        } catch (ClassNotFoundException | UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_refresh_onAction(ActionEvent event) {

    }

    public void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("පරිපාලක කළමනාකරණය කිරීම");
                lbl_userName.setText("පරිශීලක නාමය");
                txt_userName.setPromptText("පරිශීලක නාමය ඇතුළත් කරන්න");
                btn_create.setText("පරිපාලක නිර්මාණය කරන්න [F1]");
                btn_refresh.setText("නැවුම් කරන්න [F5]");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Manage Admins");
                lbl_userName.setText("Username");
                txt_userName.setPromptText("Enter Username");
                btn_create.setText("Create Admin [F1]");
                btn_refresh.setText("Refresh [F5]");
            })).start();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        new RunLater(txt_userName);
        initTable();
    }

    private void initTable (){
        tbl_manageAdmin.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tbl_manageAdmin.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tbl_manageAdmin.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
        tbl_manageAdmin.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
setTable();
    }

    private void setTable (){
        ArrayList<UserDTO> allAdmins = null;
        ArrayList<AdminTM> rowList = new ArrayList<>();
        try {
            allAdmins = userBO.getAllAdmins();
        } catch (SQLException e) {
            Theme.giveAWarning("Database config invalid", "Have A Great Day !", Session.admin_mainLabel, Session.admin_regionBack, Session.admin_regionTop, Session.admin_regionBottom, Session.admin_regionLeft, Session.admin_regionRight);
        } catch (ClassNotFoundException | UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        assert allAdmins != null;
        for (UserDTO admin :
                allAdmins) {
            JFXButton btn_delete = new JFXButton("Delete Admin");
            btn_delete.setStyle("-fx-background-color: " + Theme.warning);
            btn_delete.setTextFill(Paint.valueOf(Theme.font));
            btn_delete.setOnMouseMoved(mouseEvent -> {
                btn_delete.arm();
            });
            rowList.add(new AdminTM(
                    admin.getName(),
                    btn_delete
            ));
        }
        tbl_manageAdmin.setItems(FXCollections.observableArrayList(rowList));
    }
}
