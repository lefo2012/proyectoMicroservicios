package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JefeDepartamentoVerAnteProyectoController {

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


    private ProyectoDto proyectoDto;

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

    }
    @FXML
    void verDocumento(ActionEvent event) {
        if (proyectoDto != null && proyectoDto.getArchivoAdjunto() != null) {
            try {
                File file = new File(proyectoDto.getAnteProyecto());

                if (!file.exists()) {
                    System.out.println("No se encontró el archivo en: " + file.getAbsolutePath());
                    return;
                }

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

    @FXML
    public void cerrarSesion(ActionEvent event) {
        FrontendApplication.goLogin();
    }
    public void salir()
    {
        FrontendApplication.goJefeDepartamentoAnteProyectos();
    }
}
