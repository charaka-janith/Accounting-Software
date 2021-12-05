package front_end.ui.settings;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeThemeController implements Initializable {

    @FXML
    private FontAwesomeIconView icon_refresh;

    @FXML
    private FontAwesomeIconView icon_save;

    @FXML
    private JFXButton btn_border;

    @FXML
    private JFXButton btn_defaults;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_success;

    @FXML
    private JFXButton btn_warning;

    @FXML
    private ColorPicker colorPicker_colorBg;

    @FXML
    private ColorPicker colorPicker_colorBorder;

    @FXML
    private ColorPicker colorPicker_colorFont;

    @FXML
    private ColorPicker colorPicker_colorSuccess;

    @FXML
    private ColorPicker colorPicker_colorWarning;

    @FXML
    private Label lbl_colorBg;

    @FXML
    private Label lbl_colorBorder;

    @FXML
    private Label lbl_colorFont;

    @FXML
    private Label lbl_colorSuccess;

    @FXML
    private Label lbl_colorWarning;

    @FXML
    private Label lbl_font;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_success;

    @FXML
    private AnchorPane pane;

    @FXML
    private Region region_bg;

    @FXML
    private Region region_border;

    @FXML
    void btn_save_keyReleased() {

    }

    @FXML
    void colorPicker_colorSuccess_keyReleased() {

    }

    @FXML
    void colorPicker_colorBg_keyReleased() {

    }

    @FXML
    void colorPicker_colorFont_keyReleased() {

    }

    @FXML
    void colorPicker_colorBorder_keyReleased() {

    }

    @FXML
    void colorPicker_colorWarning_keyReleased() {

    }


    @FXML
    void btn_defaults_onAction() {

    }

    @FXML
    void btn_save_onAction() {

    }

    @FXML
    void colorButtons_onAction() {

    }

    @FXML
    void colorPicker_colorBg_onAction() {
        colorPicker_colorBorder.requestFocus();
    }

    @FXML
    void colorPicker_colorBorder_onAction() {
        colorPicker_colorFont.requestFocus();
    }

    @FXML
    void colorPicker_colorFont_onAction() {
        colorPicker_colorSuccess.requestFocus();
    }

    @FXML
    void colorPicker_colorSuccess_onAction() {
        colorPicker_colorWarning.requestFocus();
    }

    @FXML
    void colorPicker_colorWarning_onAction() {
        btn_save.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
        setColors();
        new RunLater(colorPicker_colorBg);
        setFocusListeners();
    }

    private void setFocusListeners() {
        colorPicker_colorBg.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                String color = (String.valueOf(colorPicker_colorBg.getValue())).substring(2);
                region_bg.setStyle("-fx-background-color:" + "#" + color);
            }
        });

        colorPicker_colorBorder.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                String color = (String.valueOf(colorPicker_colorBorder.getValue())).substring(2);
                region_border.setStyle("-fx-background-color:" + "#" + color);
                btn_border.setStyle("-fx-background-color:" + "#" + color);
            }
        });

        colorPicker_colorFont.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                String color = (String.valueOf(colorPicker_colorFont.getValue())).substring(2);
                lbl_font.setTextFill(Paint.valueOf("#" + color));
            }
        });

        colorPicker_colorSuccess.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                String color = (String.valueOf(colorPicker_colorSuccess.getValue())).substring(2);
                btn_success.setStyle("-fx-background-color:" + "#" + color);
            }
        });

        colorPicker_colorWarning.focusedProperty().addListener((observableValue, aBoolean, focused) -> {
            if (!focused) {
                String color = (String.valueOf(colorPicker_colorWarning.getValue())).substring(2);
                btn_warning.setStyle("-fx-background-color:" + "#" + color);
            }
        });
    }

    private void setLanguage() {
        if (Session.isSinhala()) {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("තේමාව වෙනස් කිරීම");
                lbl_colorBg.setText("පසුබිම් වර්ණය");
                lbl_colorBorder.setText("මායිම් වර්ණය");
                lbl_colorFont.setText("අකුරු වර්ණය");
                lbl_colorSuccess.setText("සාර්ථක වර්ණය");
                lbl_colorWarning.setText("අනතුරු ඇඟවීමේ වර්ණය");
                lbl_success.setText("සාර්ථක");
                lbl_font.setText("අකුරු");
                btn_border.setText("මායිම්");
                btn_warning.setText("අනතුරු ඇඟවීම");
                btn_success.setText("සාර්ථකයි");
                btn_defaults.setText("පෙරනිමි [F5]");
                btn_save.setText(" සුරකින්න [F1]");
            })).start();
        } else {
            new Thread(() -> Platform.runLater(() -> {
                lbl_main.setText("Change Theme");
                lbl_colorBg.setText("Background Color");
                lbl_colorBorder.setText("Border Color");
                lbl_colorFont.setText("Font Color");
                lbl_colorSuccess.setText("Success Color");
                lbl_colorWarning.setText("Warning Color");
                lbl_success.setText("Success");
                lbl_font.setText("Font");
                btn_border.setText("Border");
                btn_warning.setText("Warning");
                btn_success.setText("Success");
                btn_defaults.setText(" Reset [F5]");
                btn_save.setText(" Save [F1]");
            })).start();
        }
    }

    private void setColors() {
        new Thread(() -> {
            try {
                Theme.initialize();
                Platform.runLater(() -> {
                    // background
//                    Theme.setBackgroundColor("background", pane);
                    // text
//                    Theme.setTextFill("background", lbl_main);
                    // icon
//                    Theme.setIconFill("background", icon_date, icon_time, icon_signIn, icon_exit);
                });
            } catch (SQLException e) {
                Theme.giveAWarning("Database config invalid", "Have A Great Day !", Session.admin_mainLabel, Session.admin_regionBack, Session.admin_regionTop, Session.admin_regionBottom, Session.admin_regionLeft, Session.admin_regionRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
