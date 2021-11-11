package front_end.anim;

import back_end.bo.BOFacory;
import back_end.bo.custom.ColorBO;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Theme {
    static ColorBO bo = (ColorBO) BOFacory.getInstance().getBO(BOFacory.BOTypes.COLOR);
//    base colors
    public static String background;
    public static String success;
    public static String border;
    public static String font;
    public static String warning;

    public static Thread errorThread = null;

    public static void initialize () throws Exception {
        background = bo.searchColor("background").getCode();
        success = bo.searchColor("success").getCode();
        border = bo.searchColor("border").getCode();
        font = bo.searchColor("font").getCode();
        warning = bo.searchColor("warning").getCode();
    }

    public static void giveAWarning(String warning_text, String after_text, Label label, Region... regions) {
        Platform.runLater(() -> {
            label.setText(warning_text);
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
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    label.setText(after_text);
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

    public static void setBorderColor(String code, JFXButton... list) {
        for (JFXButton node :
                list) {
            node.setStyle("-fx-border-color: " + colorSwitch(code));
            setOnMouseMoveFocus(node);
        }
    }

    public static void setBackgroundColor(String code, JFXButton... list) {
        for (JFXButton node :
                list) {
            node.setStyle("-fx-background-color: " + colorSwitch(code));
            setOnMouseMoveFocus(node);
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
