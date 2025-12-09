package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.AsignarEvaluadoresRequest;
import co.edu.unicauca.infra.dto.ProfesorDto;
import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.service.ProyectoService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
    private Label textFieldNombreEvaluador1;

    @FXML
    private Label textFieldNombreEvaluador2;

    @FXML
    private Label labelObservaciones;
    @FXML
    private Text advertencia;
    @FXML
    private Text txtAsigarEvaluadores;
    @FXML
    private ComboBox<ProfesorDto> evaluador1,evaluador2;
    private ProyectoDto proyectoDto;
    private ProyectoService proyectoService;
    private List<ProfesorDto> profesoresLista;
    @FXML
    private Button botonAsignar;
    @FXML
    Pane panelInformacionOk,panelInformacion;

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
        if(proyectoDto.getEstado().equalsIgnoreCase("evaluadores_anteproyecto")){
            evaluador1.setVisible(false);
            evaluador2.setVisible(false);
            botonAsignar.setVisible(false);
            textFieldNombreEvaluador1.setVisible(true);
            textFieldNombreEvaluador2.setVisible(true);
            textFieldNombreEvaluador1.setText(proyectoDto.getNombreEvaluador1());
            textFieldNombreEvaluador2.setText(proyectoDto.getNombreEvaluador2());
            txtAsigarEvaluadores.setText("Evaluadores asignados");
        }else {
            botonAsignar.setVisible(true);
            evaluador1.setDisable(false);
            evaluador2.setDisable(false);
            evaluador1.setVisible(true);
            evaluador2.setVisible(true);
            textFieldNombreEvaluador1.setVisible(false);
            textFieldNombreEvaluador2.setVisible(false);
            txtAsigarEvaluadores.setText("Asignar evaluadores");
        }


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

        AsignarEvaluadoresRequest asignarEvaluadoresRequest = new AsignarEvaluadoresRequest();
        asignarEvaluadoresRequest.setIdProyecto(proyectoDto.getId());
        asignarEvaluadoresRequest.setCorreoElectronicoEvaluador1(profesor1.getCorreo());
        asignarEvaluadoresRequest.setCorreoElectronicoEvaluador2(profesor2.getCorreo());

        boolean bandera = proyectoService.asignarEvaluadores(asignarEvaluadoresRequest);

        if (bandera) {
            informacionOk();
            evaluador1.setDisable(true);
            evaluador2.setDisable(true);
            botonAsignar.setVisible(false);
        }
        else  {
            System.out.println("No se pudo ");
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
