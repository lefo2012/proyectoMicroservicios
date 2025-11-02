package co.edu.unicauca.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontendApplication extends Application {

    private static Scene scene;

    public static void goLogin() {
        // Método pendiente de implementación
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(new javafx.scene.Group(), 1920, 1080);
        primaryStage.setScene(scene);

        setRoot("UserRegister");

        primaryStage.setTitle("Registro de Usuario");
        primaryStage.show();
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
        launch(args);
    }

    public static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                FrontendApplication.class.getResource("/fxml/" + fxml + ".fxml")
        );
        return fxmlLoader.load();
    }

    //user-ms in 9000
}
