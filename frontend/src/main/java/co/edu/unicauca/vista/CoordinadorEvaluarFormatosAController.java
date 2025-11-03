package co.edu.unicauca.vista;


import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.service.ProyectoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CoordinadorEvaluarFormatosAController {

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
    private TextArea textAreaObservaciones;
    @FXML
    private Button botonAceptarFormato;

    @FXML
    private Button botonRechazarFormato;

    private LocalDate hoy = LocalDate.now();
    private String fecha = hoy.format(DateTimeFormatter.ISO_DATE);
    private ProyectoService proyectoService;
    private ProyectoDto proyectoDto;

    public void initialize()
    {

    }
    @FXML
    void aceptarFormato(ActionEvent event) {
        try {
            proyectoService = ProyectoService.getIntance();
            proyectoService.aceptarProyecto(proyectoDto, FrontendApplication.getPersona().getCorreoElectronico() ,fecha);
            System.out.println("Proyecto aceptado con comentario");
            textAreaObservaciones.setText("");
            FrontendApplication.goCoordinadorFormatos();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void rechazarFormato(ActionEvent event) {
        try {
            proyectoService.rechazarProyecto(proyectoDto, FrontendApplication.getPersona().getCorreoElectronico() ,fecha);
            System.out.println("Proyecto rechazado con comentario");
            textAreaObservaciones.setText("");
            FrontendApplication.goCoordinadorFormatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salir() {
        FrontendApplication.goCoordinadorFormatos();
    }
    public void cerrarSesion()
    {
        FrontendApplication.goLogin();
    }

    @FXML
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
        if (proyectoDto.getNombreCodirectores() != null && !proyectoDto.getNombreCodirectores().isEmpty()) {
            textFieldCodirector.setText(proyectoDto.getNombreCodirectores().getFirst());
        }


        textFieldEstudiante.setText(proyectoDto.getNombreEstudiante1());
        textFieldEstudiante1.setText(proyectoDto.getNombreEstudiante2());

        if (proyectoDto.getEstado().equals("Aprobado")) {
            botonAceptarFormato.setVisible(false);
            botonRechazarFormato.setVisible(false);
            try {
                textAreaObservaciones.setEditable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            botonAceptarFormato.setVisible(true);
            botonRechazarFormato.setVisible(true);
            textAreaObservaciones.setText("");
            textAreaObservaciones.setEditable(true);
        }
    }
}