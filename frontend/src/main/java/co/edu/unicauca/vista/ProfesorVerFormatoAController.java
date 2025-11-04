package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.service.ProyectoService;
import co.edu.unicauca.util.ArchivosProyecto;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;


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
    Pane PanelSubirFormatoA,PaneSeleccionarModalidad,panelInformacionOk,panelInformacion;
    @FXML
    private Label labelObservaciones;
    @FXML
    private Button btnSubirAnteproyecto;
    @FXML
    private ProyectoDto proyectoDto;
    @FXML
    private ImageView imagenArchivoPlano,imagenPdf;

    private ProyectoService proyectoService;

    private File archivo=null;

    
    public void setFormato(ProyectoDto proyectoDto) {
        this.proyectoDto = proyectoDto;


        textFieldTituloProyecto.setText(proyectoDto.getTitulo());
        textFieldModalidad.setText(proyectoDto.getTipoProyecto());
        textAreaObjetivoGeneral.setText(proyectoDto.getObjetivo());
        textAreaObjetivosEspecificos.setText(proyectoDto.getObjetivoEspecifico());

        textFieldDirector.setText(proyectoDto.getNombreDirector());

        if (proyectoDto.getNombreCodirectores() != null && !proyectoDto.getNombreCodirectores().isEmpty()) {
            textFieldCodirector.setText(proyectoDto.getNombreCodirectores().getFirst());
        }


        textFieldEstudiante.setText(proyectoDto.getNombreEstudiante1());
        textFieldEstudiante1.setText(proyectoDto.getNombreEstudiante2());
        if(proyectoDto.getEstado().equals("APROBADO"))
        {
            imagenArchivoPlano.setVisible(true);
            btnSubirAnteproyecto.setVisible(true);
        }
        else {
            imagenArchivoPlano.setVisible(false);
            btnSubirAnteproyecto.setVisible(false);
        }
    }
    public void subirAnteproyecto()
    {
        proyectoService = ProyectoService.getIntance();
        FileChooser fc = new FileChooser();

        fc.setTitle("Selecciona un archivo");

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        File archivo = fc.showOpenDialog(btnSubirAnteproyecto.getScene().getWindow());


        if (!ArchivosProyecto.validarArchivo(archivo)){

            System.out.println("El archivo elegido no cumple con los parametros requeridos");
        }else
        {
            imagenArchivoPlano.setDisable(true);
            this.archivo=archivo;
        }
        if(proyectoService.subirAnteProyecto(proyectoDto,archivo.getName()).equals("Anteproyecto subido correctamente")){
            informacionOk();
        }
    }
    @FXML
    void verDocumento(ActionEvent event) {
        if (proyectoDto != null && proyectoDto.getArchivoAdjunto() != null) {
            try {
                File file = new File(proyectoDto.getArchivoAdjunto());

                if (!file.exists()) {
                    System.out.println("No se encontró el archivo en: " + file.getAbsolutePath());
                    return;
                }

                // Solución directa con Runtime
                abrirArchivoDirecto(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void abrirArchivoDirecto(File file) {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "\"\"", "\"" + file.getAbsolutePath() + "\""});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", file.getAbsolutePath()});
            } else {
                Runtime.getRuntime().exec(new String[]{"xdg-open", file.getAbsolutePath()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void informacionOk()
    {
        panelInformacion.setVisible(true);
        panelInformacionOk.setVisible(true);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            panelInformacion.setVisible(false);
            panelInformacionOk.setVisible(false);
            panelInformacion.setManaged(false);
            panelInformacionOk.setManaged(false);
        });
        delay.play();

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
