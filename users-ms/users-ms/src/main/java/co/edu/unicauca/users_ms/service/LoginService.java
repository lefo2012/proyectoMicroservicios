package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.entity.Estudiante;
import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.entity.Profesor;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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


    public PersonaDto iniciarSesion(String username,String password) throws Exception {

        List<String> listaDeRoles = new ArrayList<>();
        PersonaDto personaDto = new PersonaDto();
        boolean usuarioValido = false;

        Profesor profesor = profesorService.findById(username);
        if(profesor != null && profesor.getPassword().equals(password))
        {

            usuarioValido = true;
            listaDeRoles.add("Profesor");
            personaDto.setNombre(profesor.getNombre());
            personaDto.setApellido(profesor.getApellido());
            personaDto.setCelular(profesor.getCelular());
            personaDto.setNombreDepartamento(profesor.getDepartamento().getNombre());
            personaDto.setIdDepartamento(profesor.getDepartamento().getId());
            personaDto.setCorreoElectronico(profesor.getCorreoElectronico());

        }

        Coordinador coordinador = coordinadorService.findById(username);

        if(coordinador!=null && coordinador.getPassword().equals(password))
        {
            usuarioValido = true;
            listaDeRoles.add("Coordinador");
            personaDto.setNombre(coordinador.getNombre());
            personaDto.setApellido(coordinador.getApellido());
            personaDto.setCelular(coordinador.getCelular());
            personaDto.setNombreDepartamento(coordinador.getDepartamento().getNombre());
            personaDto.setIdDepartamento(coordinador.getDepartamento().getId());
            personaDto.setCorreoElectronico(coordinador.getCorreoElectronico());

        }

        Estudiante estudiante = estudianteService.findById(username);


        if(estudiante!=null && estudiante.getPassword().equals(password))
        {
            usuarioValido = true;
            listaDeRoles.add("Estudiante");
            personaDto.setNombre(estudiante.getNombre());
            personaDto.setApellido(estudiante.getApellido());
            personaDto.setCelular(estudiante.getCelular());
            personaDto.setCorreoElectronico(estudiante.getCorreoElectronico());
            personaDto.setNombreProgama(estudiante.getPrograma().getNombre());
            personaDto.setIdPrograma(estudiante.getPrograma().getId());

        }
        System.out.println("buscando jefe");
        JefeDepartamento jefeDepartamento = jefeDepartamentoService.findById(username);
        System.out.println(jefeDepartamento);

        if (jefeDepartamento!=null && jefeDepartamento.getPassword().equals(password)) {
            usuarioValido = true;
            listaDeRoles.add("JefeDepartamento");
            personaDto.setNombre(jefeDepartamento.getNombre());
            personaDto.setApellido(jefeDepartamento.getApellido());
            personaDto.setCelular(jefeDepartamento.getCelular());
            personaDto.setNombreDepartamento(jefeDepartamento.getDepartamento().getNombre());
            personaDto.setIdDepartamento(jefeDepartamento.getDepartamento().getId());
            personaDto.setCorreoElectronico(jefeDepartamento.getCorreoElectronico());

        }
        if(usuarioValido)
        {
            personaDto.setCargos(listaDeRoles);
            return personaDto;

        }else
        {
            return null;
        }


    }
}
