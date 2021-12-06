package front_end.anim;

import back_end.bo.BOFactory;
import back_end.bo.custom.ColorBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Theme {
    static ColorBO bo = (ColorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COLOR);
    // base colors
    public static String background = "#ffffff";
    public static String success = "#44bd32";
    public static String border = "#1B1464";
    public static String font = "#999999";
    public static String warning = "#a50000";
    // color adjustments
    public static ColorAdjust inactiveColorAdjust = new ColorAdjust();
    public static ColorAdjust standardColorAdjust = new ColorAdjust();

    public static Thread errorThread = null;

    public static void initialize() throws SQLException, ClassNotFoundException {
        background = bo.searchColor("background").getCode();
        success = bo.searchColor("success").getCode();
        border = bo.searchColor("border").getCode();
        font = bo.searchColor("font").getCode();
        warning = bo.searchColor("warning").getCode();
        Platform.runLater(() -> {
            inactiveColorAdjust.setBrightness(-0.75);
        });
    }

    public static void giveAWarning(String warning_text, String after_text, Label label, Region back_region, Region... regions) {
        Platform.runLater(() -> {
            label.setText(warning_text);
            back_region.setStyle("-fx-background-color:" + warning);
            for (Region region :
                    regions) {
                region.setStyle("-fx-background-color:" + warning);
            }
        });

        if (null != errorThread) {
            errorThread.interrupt();
        }

        errorThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                Platform.runLater(() -> {
                    label.setText(after_text);
                    back_region.setStyle("-fx-background-color:" + success);
                    for (Region region :
                            regions) {
                        region.setStyle("-fx-background-color:" + border);
                    }
                });
            } catch (InterruptedException ignored) {
            }
        });
        errorThread.start();
    }

    public static void giveBorderWarning(Node... nodes) {
        Platform.runLater(() -> {
            for (Node node :
                    nodes) {
                node.setStyle("-fx-border-color:" + warning);
            }
        });
    }

    public static void setChangeListeners(TextField... textFields) {
        Platform.runLater(() -> {
            for (TextField textField :
                    textFields) {
                textField.textProperty().addListener((observableValue, s, t1) -> textField.setStyle("-fx-border-color: transparent"));
            }
        });
    }

    public static void setToggleColor(String toggleColor, String unToggleColor, String toggleLineColor, String unToggleLineColor, String colorBg, JFXToggleButton... list) {
        for (JFXToggleButton node :
                list) {
            node.setToggleColor(Paint.valueOf(colorSwitch(toggleColor)));
            node.setUnToggleColor(Paint.valueOf(colorSwitch(unToggleColor)));
            node.setToggleLineColor(Paint.valueOf(colorSwitch(toggleLineColor)));
            node.setUnToggleLineColor(Paint.valueOf(colorSwitch(unToggleLineColor)));
            node.setStyle("-fx-background-color:" + colorSwitch(colorBg));
        }
    }

    public static void setBackgroundColor(String code, Node... list) {
        for (Node node :
                list) {
            if (node instanceof JFXButton) {
                node.setStyle("-fx-background-color: " + colorSwitch(code));
                setOnMouseMoveFocus((JFXButton) node);
            } else {
                node.setStyle("-fx-background-color:" + colorSwitch(code));
            }
        }
    }

    public static void setTextFill(String code, Node... list) {
        for (Node node :
                list) {
            if (node instanceof Label label) {
                label.setTextFill(Paint.valueOf(colorSwitch(code)));
            } else if (node instanceof JFXButton button) {
                button.setTextFill(Paint.valueOf(colorSwitch(code)));
                setOnMouseMoveFocus((JFXButton) node);
            } else if (node instanceof Hyperlink link) {
                link.setTextFill(Paint.valueOf(colorSwitch(code)));
            } else if (node instanceof JFXToggleButton button) {
                button.setTextFill(Paint.valueOf(colorSwitch(code)));
            } else if (node instanceof JFXCheckBox comboBox) {
                comboBox.setTextFill(Paint.valueOf(colorSwitch(code)));
            } else if (node instanceof JFXRadioButton radioButton) {
                radioButton.setTextFill(Paint.valueOf(colorSwitch(code)));
            }
        }
    }

    public static void setIconFill (String code, FontAwesomeIconView... list) {
        for (FontAwesomeIconView node :
                list) {
            node.setFill(Paint.valueOf(colorSwitch(code)));
        }
    }

    private static String colorSwitch(String code) {
        return switch (code) {
            case "background" -> background;
            case "success" -> success;
            case "border" -> border;
            case "font" -> font;
            case "warning" -> warning;
            default -> null;
        };
    }

    public static void setOnMouseMoveFocus(JFXButton btn) {
        btn.setOnMouseMoved(mouseEvent -> {
            btn.arm();
        });
    }

    public static void setTimeDate(Label date, Label time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Session.isSinhala()) {
                    date.setText(" දිනය : " + new SimpleDateFormat("MM-dd-yyyy").format(new Date()));
                    time.setText(" වේලාව : " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
                } else {
                    date.setText(" Date : " + new SimpleDateFormat("MM-dd-yyyy").format(new Date()));
                    time.setText(" Time : " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
                }
            }
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void scale(Node node, boolean isFullScreen) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double x = screenBounds.getWidth();
        double y = screenBounds.getHeight();
        double ratioX = (x / 1366);
        double ratioY = (y / 768);
        Scale scale;
        scale = new Scale(ratioX, ratioY, 0, 0);
        if (!isFullScreen) {
            double ratio = Math.min(ratioX, ratioY);
            scale = new Scale(ratio, ratio, 0, 0);
            if (node instanceof Pane p) {
                p.setPrefSize(p.getPrefWidth() * ratioX, p.getPrefHeight() * ratioY);
            }
        }
        if (!(node instanceof Pane)) {
            node.setLayoutX(node.getLayoutX() * ratioX);
            node.setLayoutY(node.getLayoutY() * ratioY);
        }
        node.getTransforms().add(scale);
    }

    public static void setShade(Stage stage) {
        ColorAdjust inactiveColorAdjust = new ColorAdjust();
        ColorAdjust standardColorAdjust = new ColorAdjust();
        final ChangeListener<? super Boolean> FocusHandel = (__, ___, isFocused) ->
        {
            if (isFocused) {
                stage.getScene().getRoot().setEffect(standardColorAdjust);
            } else {
                stage.getScene().getRoot().setEffect(inactiveColorAdjust);
            }
        };
        stage.focusedProperty().addListener(FocusHandel);
    }

    public static void successGif(Stage stage) {
        final Stage initStage = new Stage();
        int size = 600;
        ImageView splash = new ImageView(new Image(Objects.requireNonNull(Theme.class.getResource("success.gif")).toExternalForm()));
        splash.setStyle("-fx-background-color: transparent;");
        splash.setFitWidth(size);
        splash.setFitHeight(size);
        splash.setPickOnBounds(true);
        Pane splashLayout = new Pane();
        splashLayout.getChildren().add(splash);

        Group group = new Group();
        group.getChildren().add(splashLayout);
        Scene successScene = new Scene(group, size, size);
        successScene.setFill(Color.TRANSPARENT);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setWidth(size);
        initStage.setHeight(size);
        initStage.setScene(successScene);
        initStage.setAlwaysOnTop(true);
        initStage.show();
        new Thread(() -> {
            Platform.runLater(() -> {
                stage.getScene().getRoot().setEffect(inactiveColorAdjust);
            });
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                initStage.close();
                stage.getScene().getRoot().setEffect(standardColorAdjust);
            });
        }).start();
    }

    public static boolean confirmPopup(String msg, Stage stage) {
        new Thread(() -> {
            Platform.runLater(() -> {
                stage.getScene().getRoot().setEffect(inactiveColorAdjust);
            });
        }).start();
        AtomicBoolean val = new AtomicBoolean();
        val.set(false);
        final Stage popup = new Stage();
        popup.setAlwaysOnTop(true);
        popup.setResizable(false);
        AnchorPane popP = new AnchorPane();
        int n = msg.split("\n").length - 1;
        popP.setPrefSize(360, 160 + (15 * n));
        setBackgroundColor("background", popP);

        Label popMainLbl = new Label(Session.isSinhala() ? "අනතුරු ඇඟවීම" : "Warning");
        setBackgroundColor("warning", popMainLbl);
        popMainLbl.setTextAlignment(TextAlignment.CENTER);
        setTextFill("background", popMainLbl);
        popMainLbl.setFont(Font.font("System Bold", FontWeight.BOLD, 14));
        popMainLbl.setAlignment(Pos.TOP_CENTER);
        popMainLbl.setLayoutY(20);
        popMainLbl.setPrefWidth(popP.getPrefWidth());
        popP.getChildren().add(popMainLbl);

        Label label = new Label(msg);
        setTextFill("font", label);
        label.setFont(Font.font("System Bold", FontWeight.BOLD, 13));
        label.setLayoutX(0);
        label.setLayoutY(63);
        popP.getChildren().add(label);
        label.setPrefWidth(360);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.TOP_CENTER);

        JFXButton confirmBtn = new JFXButton(Session.isSinhala() ? "තහවුරු කරන්න" : "Confirm");
        confirmBtn.setLayoutX(190);
        confirmBtn.setLayoutY(110 + (15 * n));
        confirmBtn.setFont(Font.font("System Bold", FontWeight.BOLD, 13));
        setTextFill("background", confirmBtn);
        setBackgroundColor("warning", confirmBtn);
        popP.getChildren().add(confirmBtn);
        confirmBtn.setOnAction(event -> {
            val.set(true);
            popup.close();
        });
        confirmBtn.setPrefSize(150, 30);

        JFXButton cancelBtn = new JFXButton(Session.isSinhala() ? "අවලංගු කරන්න" : "Cancel");
        cancelBtn.setLayoutX(20);
        cancelBtn.setLayoutY(110 + (15 * n));
        cancelBtn.setFont(Font.font("System Bold", FontWeight.BOLD, 13));
        setTextFill("background", cancelBtn);
        setBackgroundColor("success", cancelBtn);
        popP.getChildren().add(cancelBtn);
        cancelBtn.setOnAction(event -> {
            val.set(false);
            popup.close();
        });
        cancelBtn.setPrefSize(150, 30);

        Group rg = new Group();
        Scene dialogScene = new Scene(rg, Color.TRANSPARENT);
        popup.setScene(dialogScene);
        final ChangeListener<? super Boolean> keepFocus = (__, e, isFocused) ->
        {
            if (isFocused) {
                popup.getScene().getRoot().setEffect(Theme.standardColorAdjust);
            } else {
                popup.getScene().getRoot().setEffect(Theme.inactiveColorAdjust);
                new Thread(() -> {
                    Platform.runLater(popup::requestFocus);
                }).start();
            }
        };
        popup.focusedProperty().addListener(keepFocus);
        rg.getChildren().add(popP);
        popP.setEffect(new DropShadow());
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setAlwaysOnTop(true);
        scale(rg, false);
        popup.showAndWait();
        return val.get();
    }
}
