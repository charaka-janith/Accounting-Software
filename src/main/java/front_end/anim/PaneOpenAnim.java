package front_end.anim;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class PaneOpenAnim {
    public PaneOpenAnim(Node pane) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), pane);
        fadeIn.setFromValue(0.1);
        fadeIn.setToValue(1.0);
//        fadeIn.play();
    }
}
