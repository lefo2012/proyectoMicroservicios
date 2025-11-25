package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProfesorDto;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.service.ProyectoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    @FXML
    private Text advertencia;
    @FXML
    private ComboBox<ProfesorDto> evaluador1,evaluador2;
    private ProyectoDto proyectoDto;
    private ProyectoService proyectoService;
    private List<ProfesorDto> profesoresLista;


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
        cargarProfesores(proyectoDto.getId());
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

    private void cargarProfesores(long idProyecto) {

        evaluador1.getItems().clear();
        evaluador2.getItems().clear();

        proyectoService = ProyectoService.getIntance();
        profesoresLista = proyectoService.obtenerProfesoresDisponibles(idProyecto);

        evaluador1.getItems().addAll(profesoresLista);
        evaluador2.getItems().addAll(profesoresLista);

        evaluador1.setConverter(new StringConverter<>() {
            @Override
            public String toString(ProfesorDto p) {
                return (p == null) ? "" : p.getCorreo();
            }
            @Override
            public ProfesorDto fromString(String s) { return null; }
        });

        evaluador2.setConverter(new StringConverter<>() {
            @Override
            public String toString(ProfesorDto p) {
                return (p == null) ? "" : p.getCorreo();
            }
            @Override
            public ProfesorDto fromString(String s) { return null; }
        });
    }
    @FXML
    public void asignar(ActionEvent event) {

        ProfesorDto profesor1 = evaluador1.getValue();
        ProfesorDto profesor2 = evaluador2.getValue();


        if (profesor1 == null || profesor2 == null) {
            advertencia.setText("Debe seleccionar ambos evaluadores.");
            return;
        }

        if (profesor1.getCorreo().equals(profesor2.getCorreo())) {
            advertencia.setText("Los evaluadores no pueden ser el mismo profesor.");
            return;
        }

        System.out.println("Asignando:");
        System.out.println("Evaluador 1: " + profesor1.getCorreo());
        System.out.println("Evaluador 2: " + profesor2.getCorreo());
        advertencia.setText("");

    }


    @FXML
    public void cerrarSesion(ActionEvent event) {
        FrontendApplication.goLogin();
        advertencia.setText("");
    }
    public void salir()
    {
        FrontendApplication.goJefeDepartamentoAnteProyectos();
        advertencia.setText("");
    }
}
