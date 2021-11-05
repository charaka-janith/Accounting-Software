import test.login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class AppInit extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("front_end/ui/login/login.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.setAlwaysOnTop(true);
            LoginController.stage = primaryStage;
            primaryStage.requestFocus();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("AppInit.start : error : " + e.getMessage());
        }
    }
}