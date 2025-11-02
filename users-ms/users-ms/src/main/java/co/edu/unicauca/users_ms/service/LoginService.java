package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.entity.Estudiante;
import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.entity.Profesor;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.util.Encriptador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    @Autowired
    private Encriptador encriptador;

    public PersonaDto iniciarSesion(String username,String password) throws Exception {

        List<String> listaDeRoles = new ArrayList<>();
        PersonaDto personaDto = new PersonaDto();
        boolean usuarioValido = false;

        Profesor profesor = profesorService.findById(username);

        if(profesor != null && verificarContrasenia(profesor.getPassword(),password))
        {

            usuarioValido = true;
            listaDeRoles.add("PROFESOR");
            personaDto.setNombre(profesor.getNombre());
            personaDto.setApellido(profesor.getApellido());
            personaDto.setCelular(profesor.getCelular());
            personaDto.setNombreDepartamento(profesor.getDepartamento().getNombre());
            personaDto.setIdDepartamento(profesor.getDepartamento().getId());
            personaDto.setCorreoElectronico(profesor.getCorreoElectronico());

        }

        Coordinador coordinador = coordinadorService.findById(username);

        if(coordinador!=null && verificarContrasenia(coordinador.getPassword(),password))
        {
            usuarioValido = true;
            listaDeRoles.add("COORDINADOR");
            personaDto.setNombre(coordinador.getNombre());
            personaDto.setApellido(coordinador.getApellido());
            personaDto.setCelular(coordinador.getCelular());
            personaDto.setNombreDepartamento(coordinador.getDepartamento().getNombre());
            personaDto.setIdDepartamento(coordinador.getDepartamento().getId());
            personaDto.setCorreoElectronico(coordinador.getCorreoElectronico());

        }

        Estudiante estudiante = estudianteService.findById(username);
        if(estudiante!=null && verificarContrasenia(estudiante.getPassword(),password))
        {
            usuarioValido = true;
            listaDeRoles.add("ESTUDIANTE");
            personaDto.setNombre(estudiante.getNombre());
            personaDto.setApellido(estudiante.getApellido());
            personaDto.setCelular(estudiante.getCelular());
            personaDto.setCorreoElectronico(estudiante.getCorreoElectronico());
            personaDto.setNombreProgama(estudiante.getPrograma().getNombre());
            personaDto.setIdPrograma(estudiante.getPrograma().getId());

        }

        JefeDepartamento jefeDepartamento = jefeDepartamentoService.findById(username);


        if (jefeDepartamento!=null && verificarContrasenia(jefeDepartamento.getPassword(),password)) {
            usuarioValido = true;
            listaDeRoles.add("JEFEDEPARTAMENTO");
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

    private boolean verificarContrasenia (String contraseniaEncriptada, String contraseniaDigitada){
        return encriptador.passwordEncoder().matches(contraseniaDigitada,contraseniaEncriptada);
    }


}
