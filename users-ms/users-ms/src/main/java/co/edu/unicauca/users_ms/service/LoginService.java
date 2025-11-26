package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.entity.Estudiante;
import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.entity.Profesor;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import co.edu.unicauca.users_ms.service.KeycloakService;

@Service
public class LoginService {

    @Autowired
    JefeDepartamentoService jefeDepartamentoService;
    @Autowired
    CoordinadorService coordinadorService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    KeycloakService keycloakService;



    public PersonaDto iniciarSesion(String username,String password) throws Exception {
        try {
            String token = keycloakService.solicitarToken(username, password);
            System.out.println("Token: " + token);
            PersonaDto personaDto = new PersonaDto();
            boolean usuarioValido = false;
            Profesor profesor = profesorService.findById(username);
            if(profesor != null)
            {
                usuarioValido = true;
                personaDto.setId(profesor.getId());
                personaDto.setNombre(profesor.getNombre());
                personaDto.setApellido(profesor.getApellido());
                personaDto.setCelular(profesor.getCelular());
                personaDto.setNombreDepartamento(profesor.getDepartamento().getNombre());
                personaDto.setIdDepartamento(profesor.getDepartamento().getId());
                personaDto.setCorreoElectronico(profesor.getCorreoElectronico());
            }
            Coordinador coordinador = coordinadorService.findById(username);
            if(coordinador!=null)
            {
                usuarioValido = true;
                personaDto.setId(coordinador.getId());
                personaDto.setNombre(coordinador.getNombre());
                personaDto.setApellido(coordinador.getApellido());
                personaDto.setCelular(coordinador.getCelular());
                personaDto.setNombreDepartamento(coordinador.getDepartamento().getNombre());
                personaDto.setIdDepartamento(coordinador.getDepartamento().getId());
                personaDto.setCorreoElectronico(coordinador.getCorreoElectronico());

            }
            Estudiante estudiante = estudianteService.findById(username);
            if(estudiante!=null)
            {
                usuarioValido = true;
                personaDto.setId(estudiante.getId());
                personaDto.setNombre(estudiante.getNombre());
                personaDto.setApellido(estudiante.getApellido());
                personaDto.setCelular(estudiante.getCelular());
                personaDto.setCorreoElectronico(estudiante.getCorreoElectronico());
                personaDto.setNombreProgama(estudiante.getPrograma().getNombre());
                personaDto.setIdPrograma(estudiante.getPrograma().getId());

            }
            JefeDepartamento jefeDepartamento = jefeDepartamentoService.findById(username);
            if (jefeDepartamento!=null) {

                usuarioValido = true;
                personaDto.setId(jefeDepartamento.getId());
                personaDto.setNombre(jefeDepartamento.getNombre());
                personaDto.setApellido(jefeDepartamento.getApellido());
                personaDto.setCelular(jefeDepartamento.getCelular());
                personaDto.setNombreDepartamento(jefeDepartamento.getDepartamento().getNombre());
                personaDto.setIdDepartamento(jefeDepartamento.getDepartamento().getId());
                personaDto.setCorreoElectronico(jefeDepartamento.getCorreoElectronico());
            }
            if(usuarioValido)
            {

                personaDto.setToken(token);
                return personaDto;
            }else
            {
                return null;
            }
        }catch(Exception e)
        {
            throw new Exception("Error al iniciar sesion " + e.getMessage());
        }
    }



}
