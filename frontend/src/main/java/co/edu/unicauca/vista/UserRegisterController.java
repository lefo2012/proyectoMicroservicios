package co.edu.unicauca.vista;



import co.edu.unicauca.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.service.DepartamentoService;
import co.edu.unicauca.service.PersonaService;
import co.edu.unicauca.service.ProgramaService;
import co.edu.unicauca.util.Rol;
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
    ComboBox<Rol> comboBoxRol;
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

        comboBoxRol.setItems(FXCollections.observableArrayList(Rol.values()));
        comboBoxRol.getSelectionModel().selectFirst();
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

        if(comboBoxRol.getValue().equals(Rol.ESTUDIANTE))
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

      if (comboBoxRol.getItems() != null && !comboBoxRol.getItems().isEmpty()) {
          comboBoxRol.getSelectionModel().selectFirst();
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
            PersonaRegistrarDto personaRegistrar =new PersonaRegistrarDto();
            personaRegistrar.setNombre(textFieldNombre.getText());
            personaRegistrar.setApellido(textFieldApellido.getText());
            personaRegistrar.setCelular(textFieldCelular.getText());
            personaRegistrar.setCorreoElectronico(textFieldCorreoElectronico.getText());
            personaRegistrar.setPassword(passwordFieldContrasenia.getText());

            if(comboBoxRol.getValue().equals(Rol.ESTUDIANTE)){
                personaRegistrar.setRol("ESTUDIANTE");
                personaRegistrar.setIdPrograma(comboBoxPrograma.getValue().getId());
            }

            else if(comboBoxRol.getValue().equals(Rol.PROFESOR)){
                personaRegistrar.setRol("PROFESOR");
                personaRegistrar.setIdDepartamento(comboBoxDepartamento.getValue().getId());
            }

            else{
                personaRegistrar.setRol("COORDINADOR");
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
        
        if(comboBoxRol.getValue().equals(Rol.ESTUDIANTE))
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
