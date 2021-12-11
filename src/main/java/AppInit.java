import front_end.anim.Theme;
import front_end.sessions.IdleMonitor;
import front_end.sessions.Session;
import front_end.ui.dashboard.AdminDashboardController;
import front_end.ui.dashboard.CompanyDashboardController;
import front_end.ui.login.LoginController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class AppInit extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("front_end/ui/login/Login.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.setMaximized(true);
            primaryStage.requestFocus();
            primaryStage.show();
            Theme.setShade(primaryStage);
            IdleMonitor idleMonitor = new IdleMonitor(Duration.seconds(180),
                    new Runnable() {
                        @Override
                        public void run() {
                            if (null != Session.getUser()) {
                                if (Session.getUser().getType().equals("admin")) {
                                    try {
                                        LoginController.backToLogin(AdminDashboardController.stage);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        LoginController.backToLogin(CompanyDashboardController.stage);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }, true);
            idleMonitor.register(scene, Event.ANY);
            Session.setIdleMonitor(idleMonitor);
            LoginController.stage = primaryStage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}