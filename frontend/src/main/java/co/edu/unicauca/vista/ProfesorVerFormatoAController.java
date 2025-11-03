package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;


public class ProfesorVerFormatoAController {
    @FXML
    private Label textAreaObjetivoGeneral;

    @FXML
    private Label textAreaObjetivosEspecificos;

    @FXML
    private Label textFieldDirector;

    @FXML
    private Label textFieldCodirector;

    @FXML
    private Label textFieldEstudiante;

    @FXML
    private Label textFieldEstudiante1;

    @FXML
    private Label textFieldModalidad;

    @FXML
    private Label textFieldTituloProyecto;

    @FXML
    private Label labelObservaciones;
    @FXML
    private Button btnSubirAnteproyecto;
    @FXML
    private ProyectoDto proyectoDto;
    @FXML
    ImageView imagenArchivoPlano,imagenPdf;
    void verDocumento(ActionEvent event) {
        if (proyectoDto != null && proyectoDto.getArchivoAdjunto() != null) {
            try {
                File file = new File(proyectoDto.getArchivoAdjunto()); // aquí tienes la ruta completa

                if (!file.exists()) {
                    System.out.println("No se encontró el archivo en: " + file.getAbsolutePath());
                    return;
                }

                // Abrir con el visor de PDF predeterminado del SO
                Desktop.getDesktop().open(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setFormato(ProyectoDto proyectoDto) {
        this.proyectoDto = proyectoDto;


        textFieldTituloProyecto.setText(proyectoDto.getTitulo());
        textFieldModalidad.setText(proyectoDto.getTipo());
        textAreaObjetivoGeneral.setText(proyectoDto.getObjetivo());
        textAreaObjetivosEspecificos.setText(proyectoDto.getObjetivoEspecifico());

        textFieldDirector.setText(proyectoDto.getNombreDirector());
        textFieldCodirector.setText(proyectoDto.getNombreCodirectores().getFirst());


        textFieldEstudiante.setText(proyectoDto.getNombreEstudiante1());
        textFieldEstudiante1.setText(proyectoDto.getNombreEstudiante2());
        if(proyectoDto.getEstado().equals("APROBADO"))
        {
            imagenArchivoPlano.setVisible(true);
            btnSubirAnteproyecto.setVisible(true);
        }
    }
    public void subirAnteproyecto()
    {

    }
    public void verDocumento()
    {

    }
    public void goProfesorSubirFormato()
    {
        FrontendApplication.goProfesorSubirProyecto();
    }
    public void salir() {
        FrontendApplication.goProfesorFormatos();
    }
    public void cerrarSesion()
    {
        FrontendApplication.goLogin();
    }
}
