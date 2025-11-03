package co.edu.unicauca.users_ms.service;


import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.users_ms.rabbitConfig.PersonaProducer;
import co.edu.unicauca.users_ms.repository.DepartamentoRepository;
import co.edu.unicauca.users_ms.repository.ProgramaRepository;
import co.edu.unicauca.users_ms.util.Encriptador;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RegisterService {
    @Autowired
    JefeDepartamentoService jefeDepartamentoService;
    @Autowired
    CoordinadorService coordinadorService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    ProgramaRepository programaRepository;
    @Autowired
    DepartamentoRepository departamentoRepository;
    @Autowired
    Encriptador encriptador;
    @Autowired
    private GestionProyectoCliente gestionProyectoCliente;
    @Autowired
    private PersonaProducer personaProducer;

    @Transactional
    public PersonaDto registrarPersona(PersonaRegistrarDto personaDto) throws Exception {
        String rol = personaDto.getRol();
        Persona persona;
        PersonaDto personaSegura = new PersonaDto();
        String passwordEncripted = encriptador.passwordEncoder().encode(personaDto.getPassword());

        try {
            persona = crearPersonaPorRol(personaDto, passwordEncripted);

            personaSegura.setNombre(personaDto.getNombre());
            personaSegura.setApellido(personaDto.getApellido());
            personaSegura.setCelular(personaDto.getCelular());
            personaSegura.setCorreoElectronico(personaDto.getCorreoElectronico());
            personaSegura.setRoles(List.of(rol));


            if (persona instanceof Estudiante estudiante) {
                Programa programa = estudiante.getPrograma();
                personaSegura.setIdPrograma(programa.getId());
                personaSegura.setNombreProgama(programa.getNombre());
            } else if (persona instanceof Profesor profesor) {
                Departamento dep = profesor.getDepartamento();
                personaSegura.setIdDepartamento(dep.getId());
                personaSegura.setNombreDepartamento(dep.getNombre());
            } else if (persona instanceof Coordinador coordinador) {
                Departamento dep = coordinador.getDepartamento();
                personaSegura.setIdDepartamento(dep.getId());
                personaSegura.setNombreDepartamento(dep.getNombre());
            } else if (persona instanceof JefeDepartamento jefe) {
                Departamento dep = jefe.getDepartamento();
                personaSegura.setIdDepartamento(dep.getId());
                personaSegura.setNombreDepartamento(dep.getNombre());
            }

            try{
                personaProducer.enviarPersona(personaSegura);
            } catch (Exception e) {
                throw new RuntimeException("No se pudo registrar la persona en gestionProyecto " + e.getMessage());
            }

            return personaSegura;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar al usuario");
        }
    }

    private Persona crearPersonaPorRol(PersonaRegistrarDto dto, String passwordEncripted)  throws Exception{
        Persona persona;

        boolean correoExiste = false;

        switch (dto.getRol()) {
            case "ESTUDIANTE" -> correoExiste = estudianteService.existsByCorreo(dto.getCorreoElectronico());
            case "PROFESOR" -> correoExiste = profesorService.existsByCorreo(dto.getCorreoElectronico());
            case "COORDINADOR" -> correoExiste = coordinadorService.existsByCorreo(dto.getCorreoElectronico());
            case "JEFEDEPARTAMENTO" -> correoExiste = jefeDepartamentoService.existsByCorreo(dto.getCorreoElectronico());
        }

        if (correoExiste) {
            throw new RuntimeException("El correo ya está registrado para este rol");
        }
        switch (dto.getRol()) {
            case "ESTUDIANTE" -> {
                Estudiante estudiante = new Estudiante();
                setDatosBase(estudiante, dto, passwordEncripted);
                Programa programa = programaRepository.findById(dto.getIdPrograma()).orElseThrow(() -> new RuntimeException("Programa no encontrado"));
                estudiante.relacionarPrograma(programa);
                persona = estudianteService.save(estudiante);
            }
            case "PROFESOR" -> {
                Profesor profesor = new Profesor();
                setDatosBase(profesor, dto, passwordEncripted);
                Departamento dep = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                profesor.setDepartamento(dep);
                persona = profesorService.save(profesor);
            }
            case "COORDINADOR" -> {
                Coordinador coordinador = new Coordinador();
                setDatosBase(coordinador, dto, passwordEncripted);
                Departamento dep = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                coordinador.setDepartamento(dep);
                persona = coordinadorService.save(coordinador);
            }
            case "JEFEDEPARTAMENTO" -> {
                JefeDepartamento jefe = new JefeDepartamento();
                setDatosBase(jefe, dto, passwordEncripted);
                Departamento dep = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                jefe.setDepartamento(dep);
                persona = jefeDepartamentoService.save(jefe);
            }
            default -> throw new RuntimeException("Rol no reconocido: " + dto.getRol());
        }
        return persona;
    }

    private void setDatosBase(Persona persona, PersonaRegistrarDto dto, String passwordEncripted) {
        persona.setNombre(dto.getNombre());
        persona.setApellido(dto.getApellido());
        persona.setCelular(dto.getCelular());
        persona.setCorreoElectronico(dto.getCorreoElectronico());
        persona.setPassword(passwordEncripted);
    }

}







