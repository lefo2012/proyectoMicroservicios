package co.edu.unicauca.service;

import co.edu.unicauca.entities.Persona;
import co.edu.unicauca.infra.config.RestTemplateConfig;
import co.edu.unicauca.infra.dto.PersonaDto;
import co.edu.unicauca.infra.dto.PersonaRegistrarDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

@Service
public class PersonaService {
    private static PersonaService instance;
    private static final String USUARIO_URL = "http://localhost:9000/api/v1/personas";

    private RestTemplate restTemplate = RestTemplateConfig.getInstance();

    public static PersonaService getInstance(){

        if (instance==null)
        {
            instance = new PersonaService();
            return instance;
        }
        return instance;
    }

    public String registrarUsuario(PersonaRegistrarDto persona) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PersonaRegistrarDto> request = new HttpEntity<>(persona, headers);

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

    public PersonaDto iniciarSesion(String username, String password) throws Exception{
        try {
            ResponseEntity<PersonaDto> response = restTemplate.getForEntity(
                    USUARIO_URL + "/iniciarSesion/" + username + "/" + password,
                    PersonaDto.class
            );
            return response.getBody();
        }catch (Exception e)
        {
            throw e;
        }
    }
    public Persona mapearDto(PersonaDto personaDto,String cargo)
    {
        Persona persona = new Persona();
        persona.setId(personaDto.getId());
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setRol(cargo);
        persona.setCelular(personaDto.getCelular());
        persona.setCorreoElectronico(personaDto.getCorreoElectronico());
        if(cargo.equals("ESTUDIANTE"))
        {
            persona.setIdPrograma(personaDto.getIdPrograma());
            persona.setNombre(personaDto.getNombreProgama());
        }else
        {
            persona.setIdDepartamento(personaDto.getIdDepartamento());
            persona.setNombreDepartamento(personaDto.getNombreDepartamento());
        }
        return persona;
    }

}
