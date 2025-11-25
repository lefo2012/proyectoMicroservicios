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
import javafx.util.StringConverter;

import java.awt.*;
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
    private ComboBox<ProfesorDto> evaluador1,evaluador2;

    private ProyectoDto proyectoDto;
    private ProyectoService proyectoService;

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
        cargarProfesoresDisponibles(proyectoDto.getId());

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

    private void cargarProfesoresDisponibles(long idProyecto) {
        try {
            proyectoService = ProyectoService.getIntance();

            List<ProfesorDto> lista = proyectoService.getIntance().obtenerProfesoresDisponibles(idProyecto);
            ObservableList<ProfesorDto> observableList = FXCollections.observableArrayList(lista);

            evaluador1.setItems(observableList);
            evaluador2.setItems(observableList);

            evaluador1.setConverter(new StringConverter<>() {
                @Override
                public String toString(ProfesorDto profesor) {
                    return profesor != null ? profesor.getNombreCompleto() : "";
                }

                @Override
                public ProfesorDto fromString(String s) {
                    return null; // No se necesita
                }
            });

            evaluador2.setConverter(new StringConverter<>() {
                @Override
                public String toString(ProfesorDto profesor) {
                    return profesor != null ? profesor.getNombreCompleto() : "";
                }

                @Override
                public ProfesorDto fromString(String s) {
                    return null; // No se necesita
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando profesores: " + e.getMessage());
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
