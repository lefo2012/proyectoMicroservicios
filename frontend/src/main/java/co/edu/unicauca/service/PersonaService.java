package co.edu.unicauca.service;

import co.edu.unicauca.infra.config.RestTemplateConfig;
import co.edu.unicauca.infra.dto.PersonaDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

@Service
public class PersonaService {

    private static final String USUARIO_URL = "http://localhost:9000/api/v1/personas";


    private RestTemplate restTemplate = RestTemplateConfig.getInstance();

    public String registrarUsuario(PersonaDto persona) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PersonaDto> request = new HttpEntity<>(persona, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                USUARIO_URL +"/registrar",
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Registro completado";
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "Credenciales invalidas o formato incorrecto";
        } else {
            return "Error: " + response.getStatusCode();
        }


    }
    public String iniciarSesion(String username, String password){

        ResponseEntity<String> response = restTemplate.getForEntity(
                USUARIO_URL + "/iniciarSesion/" + username + "/" + password,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return "c";
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "Credenciales invalidas o formato incorrecto";
        } else {
            return "Error: " + response.getStatusCode();
        }
    }
}
