package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.ProyectoDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ProfesorDatosFormatoController {
    @FXML
    private Label lblEstado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblFormato;

    @FXML
    private Label lblName,lblName1;

    private ProyectoDto proyectoSeleccionado;

    public void setData(ProyectoDto proyectoSeleccionado) {

        this.proyectoSeleccionado = proyectoSeleccionado;
        lblFormato.setText(String.valueOf(proyectoSeleccionado.getId()));
        lblFecha.setText(proyectoSeleccionado.getFechaSubida());
        lblEstado.setText(proyectoSeleccionado.getEstado());

        lblName.setText("1. "+proyectoSeleccionado.getNombreEstudiante1());
        if(proyectoSeleccionado.getNombreEstudiante2()!=null)
        {
            lblName1.setText("2. "+proyectoSeleccionado.getNombreEstudiante2());
        }

        lblEstado.getStyleClass().removeAll("label-pendiente", "label-revision", "label-aprobado", "label-rechazado");
        switch (proyectoSeleccionado.getEstado().toLowerCase()) {
            case "pendiente" -> lblEstado.getStyleClass().add("label-pendiente");
            case "revision" -> lblEstado.getStyleClass().add("label-revision");
            case "aprobado" -> lblEstado.getStyleClass().add("label-aprobado");
            case "rechazado" -> lblEstado.getStyleClass().add("label-rechazado");
            default -> {} // sin estilo extra
        }
    }



    public void verDetalles() throws IOException {
        FrontendApplication.goProfesorVerFormatos(this.proyectoSeleccionado);
    }
}
