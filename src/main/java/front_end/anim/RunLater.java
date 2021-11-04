package front_end.anim;

import javafx.application.Platform;
import javafx.scene.Node;

public class RunLater {
    public RunLater(Node node) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                node.requestFocus();
            }
        });
    }
}
