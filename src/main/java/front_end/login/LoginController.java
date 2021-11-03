package front_end.login;

import back_end.DBConnector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  public static Stage stage;

  @FXML
  private AnchorPane pane;

  @FXML
  private Label lblMain;

  @FXML
  private Label lblUserName;

  @FXML
  private Label lblTabMode;

  @FXML
  private Label lblRL;

  @FXML
  private TextField txtUserName;

  @FXML
  private Label lblPassword;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private JFXButton btnSignIn;

  @FXML
  private JFXButton btnExit;

  @FXML
  private Hyperlink linkForgetPassword;

  @FXML
  private JFXToggleButton btnDesktopMode;

  @FXML
  private JFXSpinner loaderAnim;

  @FXML
  void btnDesktopModeKeyReleased(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      txtUserName.requestFocus();
    } else if (event.getCode().equals(KeyCode.ESCAPE)) {
      linkForgetPassword.requestFocus();
    }
  }

  @FXML
  void btnDesktopModeMouseClicked(MouseEvent event) {
    txtUserName.requestFocus();
  }

  @FXML
  void btnExitKeyReleased(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      System.exit(0);
    } else if (event.getCode().equals(KeyCode.ESCAPE)) {
      linkForgetPassword.requestFocus();
    }
  }

  @FXML
  void btnExitMouseClicked(MouseEvent event) {
    System.exit(0);
  }

  @FXML
  void btnSignInKeyReleased(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      login(event);
    } else if (event.getCode().equals(KeyCode.ESCAPE)) {
      txtPassword.requestFocus();
    }
  }

  @FXML
  void btnSignInMouseClicked(MouseEvent event) {
    login(event);
  }

  @FXML
  void linkForgetPasswordKeyReleased(KeyEvent event) {

  }

  @FXML
  void linkForgetPasswordMouseClicked(MouseEvent event) {
    loaderAnim.setOpacity(100);
  }

  @FXML
  void txtPasswordKeyReleased(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      login(event);
    } else if (event.getCode().equals(KeyCode.ESCAPE)) {
      txtUserName.requestFocus();
    }
  }

  @FXML
  void txtUserNameKeyReleased(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      txtPassword.requestFocus();
    } else if (event.getCode().equals(KeyCode.ESCAPE)) {
      btnDesktopMode.requestFocus();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  private void login(Event event) {
    DBConnector.getData("test", "password");
  }
}
