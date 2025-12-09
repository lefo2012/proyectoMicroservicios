package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.service.ProyectoService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class JefeDepartamentoAnteProyectosController {

    @FXML
    private VBox contactsLayout;
    @FXML
    private Pane panelFormatoNoti;
    @FXML
    private StackPane panelFormatoNotiOk;
    ProyectoService proyectoService;

    @FXML
    public void salir() throws IOException {
        FrontendApplication.goLogin();
    }


    public void actualizarFormatos() {
        proyectoService = ProyectoService.getIntance();
        if (contactsLayout.getChildren().size() > 1) {
            contactsLayout.getChildren().remove(1, contactsLayout.getChildren().size());
        }
        List<ProyectoDto> proyectos = proyectoService.obtenerProyectosJefeDepartamento(FrontendApplication.getPersona());
        try {
            for (ProyectoDto proyecto : proyectos) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JefeDepartamentoDatosAnteProyectos.fxml"));
                HBox hBox = loader.load();

                JefeDepartamentoDatosAnteProyectoController datosController = loader.getController();
                datosController.setData(proyecto);
                contactsLayout.getChildren().add(hBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarNotificacion() {
        panelFormatoNoti.setVisible(true);
        panelFormatoNotiOk.setVisible(true);
        panelFormatoNoti.toFront();
        panelFormatoNotiOk.toFront();

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> {
            panelFormatoNoti.setVisible(false);
            panelFormatoNotiOk.setVisible(false);
        });
        delay.play();
    }
}
