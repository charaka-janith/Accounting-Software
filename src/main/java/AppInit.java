import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import front_end.anim.Theme;
import front_end.sessions.Session;
import front_end.ui.login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class AppInit extends Application {

    public static void main(String[] args) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement parse = parser.parse(new FileReader(Objects.requireNonNull(Theme.class.getResource("theme.json")).getFile()));
            Session.setSinhala(parse.getAsJsonObject().get("sinhala").getAsBoolean());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("front_end/ui/login/Login.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            LoginController.stage = primaryStage;
            primaryStage.requestFocus();
            primaryStage.show();
            Theme.setShade(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}