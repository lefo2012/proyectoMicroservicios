package co.edu.unicauca.frontend;

import co.edu.unicauca.entities.Persona;
import co.edu.unicauca.infra.dto.PersonaDto;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.vista.*;
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
    private static Parent loginRoot;
    private static Parent registroRoot;
    private static Parent seleccionadorRoot;
    private static Parent jefeDepartamento;
    private static Parent jefeDepartamentoVerAnteProyecto;
    private static Parent coordinadorFormatos;
    private static Parent coordinadorEvaluarFormato;
    private static Parent estudianteFormatos;
    private static Parent estudianteVerFormato;
    private static Parent profesorFormatos;
    private static Parent profesorVerFormato;
    private static Parent profesorSubirProyectoRoot;
    private static JefeDepartamentoAnteProyectosController jefeDepartamentoController;
    private static JefeDepartamentoVerAnteProyectoController jefeDepartamentoVerAnteProyectoController;
    private static CoordinadorFormatosController coordinadorFormatosController;
    private static CoordinadorEvaluarFormatosAController  coordinadorEvaluarFormatosAController;
    private static EstudianteFormatosController estudianteFormatosController;
    private static EstudianteVerFormatoAController estudianteVerFormatosController;
    private static ProfesorFormatosController profesorFormatosController;
    private static ProfesorVerFormatoAController profesorVerFormatoAController;
    private static SeleccionadorDeModoController seleccionadorController;

    private static Persona persona;



    public static void goProfesorNotificaciones() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(new javafx.scene.Group(), 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/css/labelFondo.css").toExternalForm());
        primaryStage.setScene(scene);

        loginRoot = loadFXML("UserLogin");

        scene.setRoot(loginRoot);
        primaryStage.setTitle("Gestion de proyectos");
        primaryStage.show();

        registroRoot = loadFXML("UserRegister");

        FXMLLoader loader = loadIntance("SeleccionadorDeCargo");

        seleccionadorRoot = loader.load();
        seleccionadorController =loader.getController();

        profesorSubirProyectoRoot = loadFXML("ProfesorSubirProyectoDeGrado");

        loader = loadIntance("EstudianteFormatos");
        estudianteFormatos = loader.load();
        estudianteFormatosController= loader.getController();

        loader = loadIntance("EstudianteVerFormatoA");
        estudianteVerFormato = loader.load();
        estudianteVerFormatosController=loader.getController();

        loader = loadIntance("ProfesorFormatos");
        profesorFormatos = loader.load();
        profesorFormatosController=loader.getController();

        loader = loadIntance("ProfesorVerFormatoA");
        profesorVerFormato = loader.load();
        profesorVerFormatoAController = loader.getController();


        loader = loadIntance("CoordinadorFormatos");
        coordinadorFormatos = loader.load();
        coordinadorFormatosController = loader.getController();

        loader = loadIntance("CoordinadorEvaluarFormatosA");
        coordinadorEvaluarFormato = loader.load();
        coordinadorEvaluarFormatosAController = loader.getController();

        loader = loadIntance("JefeDepartamentoFormatos");
        jefeDepartamento = loader.load();
        jefeDepartamentoController = loader.getController();

        loader = loadIntance("JefeDepartamentoVerFormatos");
        jefeDepartamentoVerAnteProyecto = loader.load();
        jefeDepartamentoVerAnteProyectoController = loader.getController();

    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
        launch(args);
    }

    public static void goRegistrarse()
    {
        scene.setRoot(registroRoot);
    }

    public static void goLogin()
    {
        scene.setRoot(loginRoot);
    }


    public static void goProfesorSubirProyecto()
    {
        scene.setRoot(profesorSubirProyectoRoot);
    }

    public static void goProfesorFormatos() {
        profesorFormatosController.actualizarFormatos();
        scene.setRoot(profesorFormatos);
    }

    public static void goProfesorVerFormatos(ProyectoDto proyecto){
        profesorVerFormatoAController.setFormato(proyecto);
        scene.setRoot(profesorVerFormato);
    }
    public static void goEstudianteFormatos(){
        estudianteFormatosController.actualizarFormatos();
        scene.setRoot(estudianteFormatos);
    }
    public static void goEstudianteVerFormatos(ProyectoDto proyecto){
        estudianteVerFormatosController.setFormato(proyecto);
        scene.setRoot(estudianteVerFormato);
    }

    public static void goCoordinadorFormatos()
    {
        coordinadorFormatosController.actualizarFormatos();
        scene.setRoot(coordinadorFormatos);
    }

    public static void goCoordinadorEvaluar(ProyectoDto proyecto)
    {
        coordinadorEvaluarFormatosAController.setFormato(proyecto);
        scene.setRoot(coordinadorEvaluarFormato);
    }

    public static void goJefeDepartamentoAnteProyectos(){
        jefeDepartamentoController.actualizarFormatos();
        scene.setRoot(jefeDepartamento);
    }
    public static void goJefeDepartamentoVerAnteProyecto(ProyectoDto proyecto)
    {
        jefeDepartamentoVerAnteProyectoController.setFormato(proyecto);
        scene.setRoot(jefeDepartamentoVerAnteProyecto);
    }
    public static void goSeleccionarModo(PersonaDto persona)
    {
        seleccionadorController.setPersonaDto(persona);
        scene.setRoot(seleccionadorRoot);
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
    private static FXMLLoader loadIntance(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                FrontendApplication.class.getResource("/fxml/" + fxml + ".fxml")
        );
        return fxmlLoader;
    }
    public static void setUsuario(Persona persona)
    {
        FrontendApplication.persona = persona;
    }
    public static Persona  getPersona()
    {
        return persona;
    }
    //user-ms in 9000
}
