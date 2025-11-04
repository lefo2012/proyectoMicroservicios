package co.edu.unicauca.service;

import co.edu.unicauca.entities.Persona;
import co.edu.unicauca.infra.config.RestTemplateConfig;


import co.edu.unicauca.infra.dto.ProyectoDto;
import co.edu.unicauca.infra.dto.ProyectoRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.List;

public class ProyectoService {
    private static final String USUARIO_URL = "http://localhost:8080/Proyecto";
    private final RestTemplate restTemplate = RestTemplateConfig.getInstance();
    private static ProyectoService intance = null;

    public static ProyectoService getIntance()
    {
        if(intance==null)
        {
            intance=new ProyectoService();
            return intance;
        }
        return intance;
    }


    public String subirProyectoInvestigacion(ProyectoRequest proyectoRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProyectoRequest> request = new HttpEntity<>(proyectoRequest, headers);

        ResponseEntity<ProyectoRequest> response = restTemplate.postForEntity(
                USUARIO_URL +"/investigacion",
                request,
                ProyectoRequest.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return "Registro completado";
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "Credenciales invalidas o formato incorrecto";
        } else {
            return "Error: " + response.getStatusCode();
        }
    }

    public String subirProyectoPracticaLaboral(ProyectoRequest proyectoRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProyectoRequest> request = new HttpEntity<>(proyectoRequest, headers);

        ResponseEntity<ProyectoRequest> response = restTemplate.postForEntity(
                USUARIO_URL +"/practica",
                request,
                ProyectoRequest.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return "Registro completado";
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "Credenciales invalidas o formato incorrecto";
        } else {
            return "Error: " + response.getStatusCode();
        }
    }

    public String subirAnteProyecto(ProyectoDto proyectoDto, String nombreAnteProyecto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProyectoDto> request = new HttpEntity<>(proyectoDto, headers);

        ResponseEntity<ProyectoDto> response = restTemplate.getForEntity(
                USUARIO_URL +"/anteProyecto"+"/"+ proyectoDto.getId() + "/" + nombreAnteProyecto,
                ProyectoDto.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return "Anteproyecto subido correctamente";
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "No autorizado o formato inválido";
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return "Datos incorrectos o incompletos";
        } else {
            return "Error: " + response.getStatusCode();
        }
    }

    public String aceptarProyecto(ProyectoDto proyectoDto,String idCoordinador,String fecha)
    {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                    USUARIO_URL + "/calificarProyecto/aprobar/" + proyectoDto.getId() + "/" + idCoordinador+"/"+fecha,
                    String.class
            );
            return "Correcto";
        }catch (Exception e)
        {
            throw e;
        }

    }
    public String rechazarProyecto(ProyectoDto proyectoDto,String idCoordinador,String fecha)
    {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                    USUARIO_URL + "/calificarProyecto/rechazar/" + proyectoDto.getId() + "/" + idCoordinador+"/"+fecha,
                    String.class
            );
            return "Correcto";
        }catch (Exception e)
        {
            throw e;
        }

    }

    public List<ProyectoDto> obtenerProyectosCoordinador(Persona personaRequest) {
        try {
            ResponseEntity<List<ProyectoDto>> response = restTemplate.exchange(
                    USUARIO_URL + "/getProyectos/coordinador/" + personaRequest.getCorreoElectronico(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProyectoDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos del coordinador", e);
        }
    }
    public List<ProyectoDto> obtenerProyectosEstudiante(Persona personaRequest) {
        try {
            ResponseEntity<List<ProyectoDto>> response = restTemplate.exchange(
                    USUARIO_URL + "/getProyectos/estudiante/" + personaRequest.getCorreoElectronico(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProyectoDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos del estudiante", e);
        }
    }

    public List<ProyectoDto> obtenerProyectosProfesor(Persona personaRequest) {
        try {
            ResponseEntity<List<ProyectoDto>> response = restTemplate.exchange(
                    USUARIO_URL + "/getProyectos/profesor/" + personaRequest.getCorreoElectronico(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProyectoDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos del profesor"+ e.getMessage());
        }
    }

    public List<ProyectoDto> obtenerProyectosJefeDepartamento(Persona personaRequest) {
        try {
            ResponseEntity<List<ProyectoDto>> response = restTemplate.exchange(
                    USUARIO_URL + "/getProyectos/jefeDepartamento/" + personaRequest.getCorreoElectronico(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProyectoDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos del profesor", e);
        }
    }
}
