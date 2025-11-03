package co.edu.unicauca.vista;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.service.PersonaService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LEFO
 * @author Nicor
 */
public class UserLoginController {


    @FXML
    TextField textFieldCorreoElectronico;
    @FXML
    PasswordField passwordFieldContrasenia;
    @FXML
    Text textCorreoOContraseniaIncorrecto;



    public void initialize()
    {

    }
    public void irARegistrarse()
    {
        FrontendApplication.goRegistrarse();
        vaciarCampos();
    }

    @FXML
    public void iniciarSesion() throws Exception
    {
        try {
            PersonaService personaService = new PersonaService();
            FrontendApplication.goSeleccionarModo(personaService.iniciarSesion(textFieldCorreoElectronico.getText(), passwordFieldContrasenia.getText()));
            vaciarCampos();
        }catch (Exception e)
        {
            textCorreoOContraseniaIncorrecto.setText("Correo o contrasenia incorrecto");
        }
    }


    private void vaciarCampos() {
        textFieldCorreoElectronico.setText("");
        passwordFieldContrasenia.setText("");
        textCorreoOContraseniaIncorrecto.setText("");
    }

}