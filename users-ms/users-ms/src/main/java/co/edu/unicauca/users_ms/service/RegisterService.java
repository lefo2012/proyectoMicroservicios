package co.edu.unicauca.users_ms.service;


import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.users_ms.rabbitConfig.PersonaProducer;
import co.edu.unicauca.users_ms.repository.DepartamentoRepository;
import co.edu.unicauca.users_ms.repository.ProgramaRepository;
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
    private GestionProyectoCliente gestionProyectoCliente;
    @Autowired
    private PersonaProducer personaProducer;
    @Autowired
    private KeycloakService keycloakService;

    @Transactional
    public PersonaDto registrarParaDataLoader(PersonaRegistrarDto personaDto) throws Exception
    {

        try{
            keycloakService.registrarEnKeycloak(
                    personaDto.getCorreoElectronico(),
                    personaDto.getPassword(),
                    personaDto.getRol(),
                    personaDto.getNombre(),
                    personaDto.getApellido()
            );
        }catch (Exception ex)
        {
            System.out.println("El usuario ya existe en keycloak");
        }
        try {



            String rol = personaDto.getRol();
            Persona persona;
            PersonaDto personaSegura = new PersonaDto();
            persona = crearPersonaPorRol(personaDto);

            personaSegura.setNombre(personaDto.getNombre());
            personaSegura.setApellido(personaDto.getApellido());
            personaSegura.setCelular(personaDto.getCelular());
            personaSegura.setCorreoElectronico(personaDto.getCorreoElectronico());

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
            personaSegura.setToken(keycloakService.solicitarToken(personaDto.getCorreoElectronico(), personaDto.getPassword()));
            return personaSegura;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar al usuario "+ e.getMessage());
        }
    }
    public PersonaDto registrarPersona(PersonaRegistrarDto personaDto) throws Exception {

        try{
            keycloakService.registrarEnKeycloak(
                    personaDto.getCorreoElectronico(),
                    personaDto.getPassword(),
                    personaDto.getRol(),
                    personaDto.getNombre(),
                    personaDto.getApellido()
            );
        }catch (Exception ex)
        {
            System.out.println("El usuario ya existe en keycloak");
        }
        try {



            String rol = personaDto.getRol();
            Persona persona;
            PersonaDto personaSegura = new PersonaDto();
            persona = crearPersonaPorRol(personaDto);

            personaSegura.setNombre(personaDto.getNombre());
            personaSegura.setApellido(personaDto.getApellido());
            personaSegura.setCelular(personaDto.getCelular());
            personaSegura.setCorreoElectronico(personaDto.getCorreoElectronico());

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
            personaSegura.setToken(keycloakService.solicitarToken(personaDto.getCorreoElectronico(), personaDto.getPassword()));
            try{
                personaProducer.enviarPersona(personaSegura);
            } catch (Exception e) {
                throw new RuntimeException("No se pudo registrar la persona en gestionProyecto " + e.getMessage());
            }

            return personaSegura;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar al usuario "+ e.getMessage());
        }
    }
    private Persona crearPersonaPorRol(PersonaRegistrarDto dto)  throws Exception{
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
                setDatosBase(estudiante, dto);
                Programa programa = programaRepository.findById(dto.getIdPrograma()).orElseThrow(() -> new RuntimeException("Programa no encontrado"));
                estudiante.relacionarPrograma(programa);
                persona = estudianteService.save(estudiante);
            }
            case "PROFESOR" -> {
                Profesor profesor = new Profesor();
                setDatosBase(profesor, dto);
                Departamento dep = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                profesor.setDepartamento(dep);
                persona = profesorService.save(profesor);
            }
            case "COORDINADOR" -> {
                Coordinador coordinador = new Coordinador();
                setDatosBase(coordinador, dto);
                Departamento dep = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                coordinador.setDepartamento(dep);
                persona = coordinadorService.save(coordinador);
            }
            case "JEFEDEPARTAMENTO" -> {
                JefeDepartamento jefe = new JefeDepartamento();
                setDatosBase(jefe, dto);
                Departamento dep = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                jefe.setDepartamento(dep);
                persona = jefeDepartamentoService.save(jefe);
            }
            default -> throw new RuntimeException("Rol no reconocido: " + dto.getRol());
        }
        return persona;
    }
    private void setDatosBase(Persona persona, PersonaRegistrarDto dto) {
        persona.setNombre(dto.getNombre());
        persona.setApellido(dto.getApellido());
        persona.setCelular(dto.getCelular());
        persona.setCorreoElectronico(dto.getCorreoElectronico());
    }

}







