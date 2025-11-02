package co.edu.unicauca.vista;


import java.io.IOException;
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
    public void irARegistrarse() throws IOException {



    }

    @FXML
    public void iniciarSesion() throws Exception
    {
        vaciarCampos();
    }


    private void vaciarCampos() {
        textFieldCorreoElectronico.setText("");
        passwordFieldContrasenia.setText("");
    }

}