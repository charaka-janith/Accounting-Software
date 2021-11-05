package front_end.anim;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Theme {
    public static String colorBG;
    public static String color1;

    public static void giveAWarning(Pane pane, Label label, String warning_text, String after_text) {
        /*Platform.runLater(() -> {
            label.setText(warning_text);
            label.setStyle("-fx-background-color:" + colorWarning);
        });

        if (null != errorThread) {
            errorThread.interrupt();
        }

        errorThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                Platform.runLater(() -> {
                    label.setStyle("-fx-background-color:" + color1);
                    label.setText(after_text);
                });
            } catch (InterruptedException e) {
                System.out.println("Theme.giveAWarning : ERROR : " + e.getMessage());
            }
        });
        errorThread.start();*/
    }
}
