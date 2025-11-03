package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.service.ProyectoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ProfesorFormatosController {
    @FXML
    private VBox contactsLayout;

    ProyectoService proyectoService;

    public void actualizarFormatos(){

        proyectoService = ProyectoService.getIntance();

        try {
            if (contactsLayout.getChildren().size() > 1) {
                contactsLayout.getChildren().remove(1, contactsLayout.getChildren().size());
            }
            List<ProyectoDto> proyectos = proyectoService.obtenerProyectosProfesor(FrontendApplication.getPersona());

            for (ProyectoDto proyecto : proyectos) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfesorDatosFormato.fxml"));
                HBox hBox = loader.load();

                ProfesorDatosFormatoController datosFormatosContoller = loader.getController();
                datosFormatosContoller.setData(proyecto);
                contactsLayout.getChildren().add(hBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goProfesorSubirFormato()
    {
        FrontendApplication.goProfesorSubirProyecto();
    }
    public void cerrarSesion() {
        FrontendApplication.goLogin();
    }
    public void goProfesorNotificaciones(){

    }
}
