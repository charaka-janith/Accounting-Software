package front_end.anim;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Theme {
//    base colors
    public static String colorBG;
    public static String color1;
    public static String colorWarning;

    private static Thread errorThread = null;

    public static void giveAWarning(String warning_text, String after_text, Label label, Region... regions) {
        Platform.runLater(() -> {
            label.setText(warning_text);
            for (Region region :
                    regions) {
                region.setStyle("-fx-background-color:" + colorWarning);
            }
            label.setStyle("-fx-background-color:" + colorWarning);
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
                        region.setStyle("-fx-background-color:" + color1);
                    }
                    label.setStyle("-fx-background-color:" + color1);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        errorThread.start();
    }
}
