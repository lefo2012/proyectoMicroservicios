package co.edu.unicauca.users_ms.service;


import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.users_ms.repository.DepartamentoRepository;
import co.edu.unicauca.users_ms.repository.ProgramaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
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


    @Transactional
    public PersonaDto registrarPersona(PersonaRegistrarDto personaDto) throws Exception
    {

        String rol= personaDto.getCargo();
        Persona persona;
        PersonaDto personaSegura=new PersonaDto();


            try {
                if(rol.equals("Estudiante")){
                    Estudiante estudiante= new  Estudiante();
                    estudiante.setNombre(personaDto.getNombre());
                    estudiante.setApellido(personaDto.getApellido());
                    estudiante.setCelular(personaDto.getCelular());
                    estudiante.setCorreoElectronico(personaDto.getCorreoElectronico());
                    estudiante.setPassword(personaDto.getPassword());
                    Programa programa = programaRepository.findById(personaDto.getIdPrograma()).orElseThrow(() -> new RuntimeException("Programa no encontrado"));
                    estudiante.relacionarPrograma(programa);
                    persona = estudianteService.save(estudiante);

                    personaSegura.setIdPrograma(programa.getId());
                    personaSegura.setNombreProgama(programa.getNombre());

                }
                if(rol.equals("Profesor")){
                    Profesor profesor= new Profesor();
                    profesor.setNombre(personaDto.getNombre());
                    profesor.setApellido(personaDto.getApellido());
                    profesor.setCelular(personaDto.getCelular());
                    profesor.setCorreoElectronico(personaDto.getCorreoElectronico());
                    profesor.setPassword(personaDto.getPassword());
                    Departamento departamento= departamentoRepository.findById(personaDto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                    profesor.setDepartamento(departamento);
                    persona = profesorService.save(profesor);

                    personaSegura.setIdDepartamento(departamento.getId());
                    personaSegura.setNombreDepartamento(departamento.getNombre());
                }
                if(rol.equals("Coordinador")){
                    Coordinador coordinador = new Coordinador();
                    coordinador.setNombre(personaDto.getNombre());
                    coordinador.setApellido(personaDto.getApellido());
                    coordinador.setCelular(personaDto.getCelular());
                    coordinador.setCorreoElectronico(personaDto.getCorreoElectronico());
                    coordinador.setPassword(personaDto.getPassword());
                    Departamento departamento= departamentoRepository.findById(personaDto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                    coordinador.setDepartamento(departamento);
                    persona = coordinadorService.save(coordinador);

                    personaSegura.setIdDepartamento(departamento.getId());
                    personaSegura.setNombreDepartamento(departamento.getNombre());
                }
                if(rol.equals("JefeDepartamento")){
                    JefeDepartamento jefeDepartamento = new JefeDepartamento();
                    jefeDepartamento.setNombre(personaDto.getNombre());
                    jefeDepartamento.setApellido(personaDto.getApellido());
                    jefeDepartamento.setCelular(personaDto.getCelular());
                    jefeDepartamento.setCorreoElectronico(personaDto.getCorreoElectronico());
                    jefeDepartamento.setPassword(personaDto.getPassword());
                    Departamento departamento= departamentoRepository.findById(personaDto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                    jefeDepartamento.setDepartamento(departamento);
                    persona = jefeDepartamentoService.save(jefeDepartamento);

                    personaSegura.setIdDepartamento(departamento.getId());
                    personaSegura.setNombreDepartamento(departamento.getNombre());
                }
            }catch (DataIntegrityViolationException e){
                throw new RuntimeException("Correo en uso para este cargo: ");
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error al registrar al usuario: ");
            }


            personaSegura.setNombre(personaDto.getNombre());
            personaSegura.setApellido(personaDto.getApellido());
            personaSegura.setCelular(personaDto.getCelular());
            personaSegura.setCorreoElectronico(personaDto.getCorreoElectronico());
            List<String> cargos= new ArrayList<>();
            cargos.add(personaDto.getCargo());
            personaSegura.setCargos(cargos);

            return personaSegura;


    }

}
