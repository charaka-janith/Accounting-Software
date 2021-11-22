package front_end.anim;

import back_end.bo.BOFactory;
import back_end.bo.custom.ColorBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import front_end.sessions.Session;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Theme {
    static ColorBO bo = (ColorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COLOR);
    //    base colors
    public static String background = "#ffffff";
    public static String success = "#44bd32";
    public static String border = "#1B1464";
    public static String font = "#999999";
    public static String warning = "#a50000";

    public static Thread errorThread = null;

    public static void initialize() throws Exception {
        background = bo.searchColor("background").getCode();
        success = bo.searchColor("success").getCode();
        border = bo.searchColor("border").getCode();
        font = bo.searchColor("font").getCode();
        warning = bo.searchColor("warning").getCode();
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

    public static void setToggleColor(String toggleColor, String unToggleColor, String toggleLineColor, String unToggleLineColor, JFXToggleButton... list) {
        for (JFXToggleButton node :
                list) {
            node.setToggleColor(Paint.valueOf(colorSwitch(toggleColor)));
            node.setUnToggleColor(Paint.valueOf(colorSwitch(unToggleColor)));
            node.setToggleLineColor(Paint.valueOf(colorSwitch(toggleLineColor)));
            node.setUnToggleLineColor(Paint.valueOf(colorSwitch(unToggleLineColor)));
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
}
