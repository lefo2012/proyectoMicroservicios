package co.edu.unicauca.users_ms;

import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.users_ms.repository.*;
import co.edu.unicauca.users_ms.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private JefeDepartamentoRepository jefeDepartamentoRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private ProgramaRepository programaRepository;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private FacultadRepository facultadRepository;



    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            JefeDepartamento jefeDepartamento = new JefeDepartamento();
            jefeDepartamento.setNombre("Isabella");
            jefeDepartamento.setApellido("Fernandez Pastrana");
            jefeDepartamento.setId(3);
            jefeDepartamento.setCorreoElectronico("jefe@unicauca.edu.co");

            PersonaRegistrarDto jefe = new PersonaRegistrarDto();
            jefe.setNombre("Isabella");
            jefe.setApellido("Fernandez Pastrana");
            jefe.setCorreoElectronico("jefe@unicauca.edu.co");
            jefe.setPassword("123ASD.");
            jefe.setIdDepartamento(1);
            jefe.setRol("JEFEDEPARTAMENTO");


            Coordinador coordinador = new Coordinador();
            coordinador.setNombre("Nicolas");
            coordinador.setApellido("Rocha");
            coordinador.setId(1);
            coordinador.setCorreoElectronico("coor@unicauca.edu.co");

            PersonaRegistrarDto coor = new PersonaRegistrarDto();
            coor.setNombre("Nicolas");
            coor.setApellido("Rocha");
            coor.setCorreoElectronico("coor@unicauca.edu.co");
            coor.setPassword("123ASD.");
            coor.setIdDepartamento(1);
            coor.setRol("COORDINADOR");

            Profesor profesor = new Profesor();
            profesor.setNombre("Jorge");
            profesor.setApellido("Curioso");
            profesor.setId(4);
            profesor.setCorreoElectronico("prof@unicauca.edu.co");

            PersonaRegistrarDto prof = new PersonaRegistrarDto();
            prof.setNombre("Jorge");
            prof.setApellido("Curioso");
            prof.setCorreoElectronico("prof@unicauca.edu.co");
            prof.setPassword("123ASD.");
            prof.setIdDepartamento(1);
            prof.setRol("PROFESOR");

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Luis");
            estudiante.setApellido("Fierro");
            estudiante.setId(2);
            estudiante.setCorreoElectronico("est@unicauca.edu.co");

            PersonaRegistrarDto est = new PersonaRegistrarDto();
            est.setNombre("Luis");
            est.setApellido("Fierro");
            est.setCorreoElectronico("est@unicauca.edu.co");
            est.setPassword("123ASD.");
            est.setIdPrograma(1);
            est.setRol("ESTUDIANTE");

            Programa programa = new Programa();
            programa.setNombre("Programa1");

            Departamento departamento = new Departamento();
            departamento.setNombre("Departamento1");

            Facultad facultad = new Facultad();
            facultad.setNombre("Facultad");

            facultadRepository.save(facultad);
            departamento.relacionarFacultad(facultad);
            departamentoRepository.save(departamento);
            programa.relacionarDepartamento(departamento);
            programaRepository.save(programa);

            estudiante.relacionarPrograma(programa);
            jefeDepartamento.relacionarDepartamento(departamento);
            profesor.relacionarDepartamento(departamento);
            coordinador.relacionarDepartamento(departamento);

            registerService.registrarPersona(coor);
            registerService.registrarPersona(est);
            registerService.registrarPersona(jefe);
            registerService.registrarPersona(prof);

            programaRepository.save(programa);
            departamentoRepository.save(departamento);
        }catch (Exception e)
        {
            System.out.println("ERROR EN COMMAND LINE RUNNER "+e.getMessage());
        }

    }

}
