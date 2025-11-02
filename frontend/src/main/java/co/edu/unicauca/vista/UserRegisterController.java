package co.edu.unicauca.vista;



import co.edu.unicauca.infra.dto.PersonaDto;
import co.edu.unicauca.service.DepartamentoService;
import co.edu.unicauca.service.PersonaService;
import co.edu.unicauca.service.ProgramaService;
import co.edu.unicauca.util.Cargo;
import co.edu.unicauca.frontend.FrontendApplication;
import java.io.IOException;

import co.edu.unicauca.entities.Departamento;
import co.edu.unicauca.entities.Programa;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEFO
 */
public class UserRegisterController {
    @FXML
    TextField textFieldCorreoElectronico,textFieldNombre,textFieldApellido,textFieldCelular;
    @FXML
    PasswordField passwordFieldContrasenia;
    @FXML
    ComboBox<Cargo> comboBoxCargo;
    @FXML
    ComboBox<Programa> comboBoxPrograma;
    @FXML
    ComboBox<Departamento> comboBoxDepartamento;
    @FXML
    Text textoAviso;
    @FXML


    
    public void irALogin() throws IOException
    {

        FrontendApplication.goLogin();
    
    }
    public void initialize() {


        try{

        comboBoxCargo.setItems(FXCollections.observableArrayList(Cargo.values()));
        comboBoxCargo.getSelectionModel().selectFirst();
        comboBoxPrograma.setItems(FXCollections.observableArrayList(ProgramaService.obtenerTodos()));

        comboBoxPrograma.setCellFactory(param -> new ListCell<Programa>() {
            @Override
            protected void updateItem(Programa item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });

        comboBoxPrograma.setButtonCell(new ListCell<Programa>() {
            @Override
            protected void updateItem(Programa item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "Seleccione programa" : item.getNombre());
            }
        });
        comboBoxDepartamento.setItems(FXCollections.observableArrayList(DepartamentoService.obtenerTodos()));
        comboBoxDepartamento.setCellFactory(param -> new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });
        comboBoxDepartamento.setButtonCell(new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "Seleccione programa" : item.getNombre());
            }
        });
        comboBoxDepartamento.getSelectionModel().selectFirst();
        comboBoxPrograma.getSelectionModel().selectFirst();

        if(comboBoxCargo.getValue().equals(Cargo.ESTUDIANTE))
        {
            comboBoxDepartamento.setVisible(false);
            comboBoxPrograma.setVisible(true);
        }else
        {
            comboBoxDepartamento.setVisible(true);
            comboBoxPrograma.setVisible(false);
        }


        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void limpiarCampos() {
      textFieldCorreoElectronico.clear();
      textFieldNombre.clear();
      textFieldApellido.clear();
      textFieldCelular.clear();
      passwordFieldContrasenia.clear();
      textoAviso.setText(""); 

      if (comboBoxCargo.getItems() != null && !comboBoxCargo.getItems().isEmpty()) {
          comboBoxCargo.getSelectionModel().selectFirst(); 
      }

      textFieldCorreoElectronico.setStyle("-fx-alignment: CENTER;");
      textFieldNombre.setStyle("-fx-alignment: CENTER;");
      textFieldApellido.setStyle("-fx-alignment: CENTER;");
      textFieldCelular.setStyle("-fx-alignment: CENTER;");
      passwordFieldContrasenia.setStyle("-fx-alignment: CENTER;");
    }
    public void registrarse() throws Exception
    {
        String resultado ="";

        PersonaService personaService = new PersonaService();

        if (validarCampos())
        {
            PersonaDto personaRegistrar =new PersonaDto();
            personaRegistrar.setNombre(textFieldNombre.getText());
            personaRegistrar.setApellido(textFieldApellido.getText());
            personaRegistrar.setCelular(textFieldCelular.getText());
            personaRegistrar.setCorreoElectronico(textFieldCorreoElectronico.getText());
            personaRegistrar.setPassword(passwordFieldContrasenia.getText());

            if(comboBoxCargo.getValue().equals(Cargo.ESTUDIANTE)){
                personaRegistrar.setCargo("Estudiante");
                personaRegistrar.setIdPrograma(comboBoxPrograma.getValue().getId());
            }

            else if(comboBoxCargo.getValue().equals(Cargo.PROFESOR)){
                personaRegistrar.setCargo("Profesor");
                personaRegistrar.setIdDepartamento(comboBoxDepartamento.getValue().getId());
            }

            else{
                personaRegistrar.setCargo("Coordinador");
                personaRegistrar.setIdDepartamento(comboBoxDepartamento.getValue().getId());
            }

            resultado = personaService.registrarUsuario(personaRegistrar);

            if (resultado.equals("Registro completado")){
                textoAviso.setText(resultado);
                limpiarCampos();

            }
        }
    }
    private boolean validarCampos()
    {   
        boolean bandera = true;
        String resultado = "";
        if("".equals(textFieldNombre.getText()) )
        {
            textFieldNombre.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE NOMBRE\n";
            bandera=false;
        }
        if("".equals(textFieldApellido.getText()) )
        {
            textFieldApellido.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE APELLIDO\n";
            bandera=false;
        }
        if("".equals(textFieldCorreoElectronico.getText()))
        {
            textFieldCorreoElectronico.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE CORREO ELECTRONICO\n";
            bandera=false;
        }
        if("".equals(passwordFieldContrasenia.getText()))
        {
            passwordFieldContrasenia.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE CONTRASEÑA\n";
            bandera=false;
        }
        textoAviso.setText(resultado);
        return bandera;
    }
    public void cambiarDepartamentoPrograma()
    {
        
        if(comboBoxCargo.getValue().equals(Cargo.ESTUDIANTE))
        {
            comboBoxDepartamento.setVisible(false);
            comboBoxPrograma.setVisible(true);
            System.out.println("Cambiando combo box a programa");
        }
        else
        {
            comboBoxDepartamento.setVisible(true);
            comboBoxPrograma.setVisible(false);
            System.out.println("Cambiando combo box a departamento");
        }
    }
}
