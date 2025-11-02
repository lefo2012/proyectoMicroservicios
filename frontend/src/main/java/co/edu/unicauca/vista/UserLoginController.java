package co.edu.unicauca.vista;


import java.io.IOException;

import co.edu.unicauca.frontend.FrontendApplication;
import co.edu.unicauca.service.PersonaService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;

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

    }

    @FXML
    public void iniciarSesion() throws Exception
    {
        PersonaService personaService = new PersonaService();
        if(personaService.iniciarSesion(textFieldCorreoElectronico.getText(), passwordFieldContrasenia.getText()).equals("c"))
        {
            System.out.println("Se ha iniciado correctamente");
            vaciarCampos();
        }

    }


    private void vaciarCampos() {
        textFieldCorreoElectronico.setText("");
        passwordFieldContrasenia.setText("");
    }

}