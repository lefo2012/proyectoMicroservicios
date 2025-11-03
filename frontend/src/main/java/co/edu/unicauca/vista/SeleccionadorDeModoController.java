package co.edu.unicauca.vista;


import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.infra.dto.PersonaDto;
import co.edu.unicauca.service.PersonaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javax.swing.*;

public class SeleccionadorDeModoController {

    PersonaDto personaDto;
    PersonaService personaService;
    @FXML
    ComboBox<String> comboBoxRol;


    public void initialize()
    {
        try
        {
            if(personaDto != null)
            {
                personaService = PersonaService.getInstance();
                comboBoxRol.setItems(FXCollections.observableArrayList(personaDto.getRoles()));
                comboBoxRol.getSelectionModel().selectFirst();
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    public void setPersonaDto(PersonaDto personaDto) {
        this.personaDto = personaDto;
        initialize();
    }
    public void ir()
    {
        FrontendApplication.setUsuario(personaService.mapearDto(personaDto,comboBoxRol.getValue()));
        if(comboBoxRol.getValue().equals("PROFESOR"))
        {
            FrontendApplication.goProfesorSubirProyecto();
        } else if (comboBoxRol.getValue().equals("ESTUDIANTE")) {
            FrontendApplication.goEstudianteFormatos();
        } else if (comboBoxRol.getValue().equals("COORDINADOR")) {
            FrontendApplication.goCoordinadorFormatos();
        } else if (comboBoxRol.getValue().equals("JEFEDEPARTAMENTO")) {
            FrontendApplication.goJefeDepartamentoAnteProyectos();
        }
    }
}
