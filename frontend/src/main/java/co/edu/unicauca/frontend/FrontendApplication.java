package co.edu.unicauca.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FrontendApplication extends Application {

    private static Scene scene;
    private static Parent loginRoot;
    private static Parent registroRoot;



    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(new javafx.scene.Group(), 1920, 1080);
        primaryStage.setScene(scene);

        loginRoot = loadFXML("UserLogin");

        scene.setRoot(loginRoot);
        primaryStage.setTitle("Registro de Usuario");
        primaryStage.show();

        registroRoot = loadFXML("UserRegister");


    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
        launch(args);
    }

    public static void goRegistrarse()
    {
        scene.setRoot(registroRoot);
    }

    public static void goLogin() {
        scene.setRoot(loginRoot);
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
