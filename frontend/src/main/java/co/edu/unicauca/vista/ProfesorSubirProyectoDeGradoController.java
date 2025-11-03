package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoRequest;
import co.edu.unicauca.service.ProyectoService;
import co.edu.unicauca.util.ArchivosProyecto;
import co.edu.unicauca.util.Tipo;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;


public class ProfesorSubirProyectoDeGradoController {
    @FXML
    Button botonSubirArchivo;
    @FXML
    Pane PanelSubirFormatoA,PaneSeleccionarModalidad,panelInformacionOk,panelInformacion;
    @FXML
    ComboBox<Tipo> comboBoxModalidad;
    @FXML
    Text textNombreArchivo,textCartaAceptacion,advertencia;
    @FXML
    ImageView imagenArchivoPlano,imagenPdf;
    @FXML
    TextField textFieldTituloProyecto,textFieldCodirector,textFieldEstudiante,textFieldEstudiante1;
    @FXML
    TextArea textAreaObjetivosEspecificos,textAreaObjetivoGeneral;

    ProyectoService proyectoService;



    File archivo=null;

    public void initialize()
    {
        try{

            comboBoxModalidad.setItems(FXCollections.observableArrayList(Tipo.values()));
            comboBoxModalidad.getSelectionModel().selectFirst();
        }catch(Exception e)
        {
            System.out.println("Error en Profesor subir formato");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void cambiarPaginaFormatoA()
    {
        comboBoxModalidad.setValue(comboBoxModalidad.getValue());
        PanelSubirFormatoA.setVisible(true);
        PaneSeleccionarModalidad.setVisible(false);
    }

    public void enviarProyecto() throws Exception
    {
        validarCampos();
        proyectoService = ProyectoService.getIntance();
        ProyectoRequest proyectoRequest = new ProyectoRequest();
        proyectoRequest.setTitulo(textFieldTituloProyecto.getText());
        proyectoRequest.setObjetivo(textAreaObjetivoGeneral.getText());
        proyectoRequest.setObjetivoEspecifico(textAreaObjetivosEspecificos.getText());
        
        if(!textFieldCodirector.getText().isEmpty())
        {
            proyectoRequest.addCodirector(textFieldCodirector.getText());
        }

        proyectoRequest.setCorreoEstudiante1(textFieldEstudiante.getText());
        proyectoRequest.setCorreoEstudiante2(textFieldEstudiante1.getText());
        proyectoRequest.setArchivoAdjunto(archivo.getName());
        proyectoRequest.setCorreoDirector(FrontendApplication.getPersona().getCorreoElectronico());
        if(comboBoxModalidad.getValue().equals(Tipo.Investigacion))
            if(proyectoService.subirProyectoInvestigacion(proyectoRequest).equals("Registro completado"))
            {
                informacionOk();
                vaciarCampos();
            }
        else
        {
            if(proyectoService.subirProyectoPracticaLaboral(proyectoRequest).equals("Registro completado"))
            {
                informacionOk();
                vaciarCampos();
            }

        }
    }
    public void vaciarCampos()
    {
        textFieldTituloProyecto.setText("");
        textAreaObjetivoGeneral.setText("");
        textAreaObjetivosEspecificos.setText("");
        textFieldCodirector.setText("");
        textFieldEstudiante.setText("");
        textFieldEstudiante1.setText("");
        imagenArchivoPlano.setVisible(true);
        imagenPdf.setVisible(false);
        comboBoxModalidad.setValue(Tipo.Investigacion);
        textNombreArchivo.setText("Agrega un archivo PDF de maximo 20MB");
        advertencia.setText("");
        Object archivo = null;
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
    public void verificarProyecto()
    {

        if(comboBoxModalidad.getValue().equals(Tipo.PracticaLaboral))
        {
            textCartaAceptacion.setVisible(true);
            textFieldEstudiante1.setText("");
            textFieldEstudiante1.setEditable(false);
            textFieldEstudiante1.setMouseTransparent(true);
            textFieldEstudiante1.setFocusTraversable(false);
            textFieldEstudiante1.setPromptText("EN PRACTICA PROFESIONAL SOLO PUEDE HABER UN ESTUDIANTE");
        }
        if(comboBoxModalidad.getValue().equals(Tipo.Investigacion))
        {
            textCartaAceptacion.setVisible(false);
            textNombreArchivo.setText("Agrega un archivo PDF de maximo 20MB");
            textFieldEstudiante1.setText("");
            textFieldEstudiante1.setEditable(true);
            textFieldEstudiante1.setMouseTransparent(false);
            textFieldEstudiante1.setFocusTraversable(true);
            textFieldEstudiante1.setPromptText("ESTUDIANTE");
        }
    }
    public void subirDocumento()
    {
        FileChooser fc = new FileChooser();

        fc.setTitle("Selecciona un archivo");

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        File archivo = fc.showOpenDialog(botonSubirArchivo.getScene().getWindow());


        if (!ArchivosProyecto.validarArchivo(archivo)){

            System.out.println("El archivo elegido no cumple con los parametros requeridos");
        }else
        {
            textNombreArchivo.setText(archivo.getName());
            imagenArchivoPlano.setVisible(false);
            imagenPdf.setVisible(true);

            this.archivo=archivo;
        }
    }

    public boolean validarCampos(){
        boolean bandera = true;
        if(textFieldTituloProyecto.getText().isEmpty())
        {
            textFieldTituloProyecto.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera = false;
        }
        if(textFieldEstudiante.getText().isEmpty() && bandera)
        {
            textFieldEstudiante.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera = false;
        }
        if(textAreaObjetivoGeneral.getText().isEmpty() && bandera)
        {
            textAreaObjetivoGeneral.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera=false;
        }
        if(textAreaObjetivosEspecificos.getText().isEmpty() && bandera)
        {
            textAreaObjetivosEspecificos.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera=false;
        }
        return bandera;
    }

    public void goProfesorFormatos()
    {
        vaciarCampos();
        FrontendApplication.goProfesorFormatos();
    }
    public void goProfesorNotificaciones()
    {
        vaciarCampos();
        FrontendApplication.goProfesorNotificaciones();
    }
    public void cerrarSesion()
    {
        vaciarCampos();
        FrontendApplication.goLogin();
    }
}
