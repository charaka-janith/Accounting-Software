package front_end.ui.settings;

import back_end.bo.BOFactory;
import back_end.bo.custom.ColorBO;
import back_end.dto.ColorDTO;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.anim.RunLater;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import front_end.ui.login.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeThemeController implements Initializable {

    ColorBO colorBO = (ColorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COLOR);

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
    void btn_save_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_defaults.requestFocus();
        }
    }

    @FXML
    void colorPicker_colorSuccess_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            colorPicker_colorFont.requestFocus();
        }
    }

    @FXML
    void colorPicker_colorBg_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            btn_save.requestFocus();
        }
    }

    @FXML
    void colorPicker_colorFont_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            colorPicker_colorBorder.requestFocus();
        }
    }

    @FXML
    void colorPicker_colorBorder_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            colorPicker_colorBg.requestFocus();
        }
    }

    @FXML
    void colorPicker_colorWarning_keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            colorPicker_colorSuccess.requestFocus();
        }
    }


    @FXML
    void btn_defaults_onAction() {
        reset();
    }

    private void reset () {
        try {
            colorBO.updateColor(new ColorDTO("background", "#ffffff") );
            colorBO.updateColor(new ColorDTO("success", "#44bd32") );
            colorBO.updateColor(new ColorDTO("border", "#1B1464") );
            colorBO.updateColor(new ColorDTO("font", "#999999") );
            colorBO.updateColor(new ColorDTO("warning", "#a50000") );
            LoginController.backToLogin(AdminDashboardController.stage);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_save_onAction() {
        saveColors();
    }

    private void saveColors() {
        try {
            colorBO.updateColor(new ColorDTO("background", "#" + (String.valueOf(colorPicker_colorBg.getValue())).substring(2)));
            colorBO.updateColor(new ColorDTO("success", "#" + (String.valueOf(colorPicker_colorSuccess.getValue())).substring(2)));
            colorBO.updateColor(new ColorDTO("border", "#" + (String.valueOf(colorPicker_colorBorder.getValue())).substring(2)));
            colorBO.updateColor(new ColorDTO("font", "#" + (String.valueOf(colorPicker_colorFont.getValue())).substring(2)));
            colorBO.updateColor(new ColorDTO("warning", "#" + (String.valueOf(colorPicker_colorWarning.getValue())).substring(2)));
            Theme.successGif(AdminDashboardController.stage);
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                Platform.runLater(() -> {
                    try {
                        LoginController.backToLogin(AdminDashboardController.stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void colorButtons_onAction() {
        lbl_success.setText("Warning");
        region_bg.setStyle("-fx-background-color:" + "#" + (String.valueOf(colorPicker_colorWarning.getValue())).substring(2));

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    lbl_success.setText("Success");
                    region_bg.setStyle("-fx-background-color:" + "#" + (String.valueOf(colorPicker_colorBg.getValue())).substring(2));
                });
            } catch (InterruptedException ignored) {
            }
        }).start();
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
        runLater();
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
                lbl_success.setTextFill(Paint.valueOf("#" + color));
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
                btn_defaults.setText("යළි පිහිටුවන්න [F5]");
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
        Platform.runLater(() -> {
            // background
            Theme.setBackgroundColor("background", pane, region_bg);
            Theme.setBackgroundColor("success", btn_save, btn_success);
            Theme.setBackgroundColor("border", region_border, btn_border);
            Theme.setBackgroundColor("warning", btn_defaults, btn_warning);
            // text
            Theme.setTextFill("background", btn_defaults, btn_save, btn_border, btn_warning, btn_success);
            Theme.setTextFill("success", lbl_main, lbl_success);
            Theme.setTextFill("font",
                    lbl_font,
                    lbl_colorBg,
                    lbl_colorBorder,
                    lbl_colorFont,
                    lbl_colorSuccess,
                    lbl_colorWarning
            );
            // icon
            Theme.setIconFill("background", icon_refresh, icon_save);
            // pickers
            colorPicker_colorBg.setValue(Color.valueOf(Theme.background));
            colorPicker_colorBorder.setValue(Color.valueOf(Theme.border));
            colorPicker_colorFont.setValue(Color.valueOf(Theme.font));
            colorPicker_colorSuccess.setValue(Color.valueOf(Theme.success));
            colorPicker_colorWarning.setValue(Color.valueOf(Theme.warning));
        });
    }

    private void runLater() {
        Platform.runLater(() -> {
            pane.getScene().setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.F1)) {
                    saveColors();
                } else if (event.getCode().equals(KeyCode.F5)) {
                    reset();
                }
            });
        });
    }
}
