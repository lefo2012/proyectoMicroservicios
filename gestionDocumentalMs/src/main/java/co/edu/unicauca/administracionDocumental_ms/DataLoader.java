package co.edu.unicauca.administracionDocumental_ms;

import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.*;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.*;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JefeDepartamentoJpaRepository jefeDepartamentoRepository;
    @Autowired
    private ProfesorJpaRepository profesorRepository;
    @Autowired
    private EstudianteJpaRepository estudianteRepository;
    @Autowired
    private DepartamentoJpaRepository departamentoRepository;
    @Autowired
    private ProgramaJpaRepository programaRepository;
    @Autowired
    private FacultadJpaRepository facultadRepository;
    @Autowired
    private CoordinadorJpaRepository coordinadorRepository;
    @Autowired
    private ProgramaMapper programaMapper;
    @Autowired
    private DepartamentoMapper departamentoMapper;
    @Autowired
    private EstudianteMapper estudianteMapper;
    @Autowired
    private ProfesorMapper profesorMapper;
    @Autowired
    private CoordinadorMapper coordinadorMapper;
    @Autowired
    private JefeDepartamentoMapper jefeDepartamentoMapper;

    @Override
    public void run(String... args) {
        try {

            JefeDepartamento jefeDepartamento = new JefeDepartamento();
            jefeDepartamento.setNombre("Isabella");
            jefeDepartamento.setApellido("Fernandez Pastrana");
            jefeDepartamento.setCorreoElectronico("jefe@unicauca.edu.co");

            Coordinador coordinador = new Coordinador();
            coordinador.setNombre("Nicolas");
            coordinador.setApellido("Rocha");
            coordinador.setCorreoElectronico("coor@unicauca.edu.co");

            Profesor profesor = new Profesor();
            profesor.setNombre("Jorge");
            profesor.setApellido("Curioso");
            profesor.setCorreoElectronico("prof@unicauca.edu.co");

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Luis");
            estudiante.setApellido("Fierro");
            estudiante.setCorreoElectronico("est@unicauca.edu.co");

            ProgramaJpa programa = new ProgramaJpa();
            programa.setNombre("Programa1");

            DepartamentoJpa departamento = new DepartamentoJpa();
            departamento.setNombre("Departamento1");

            FacultadJpa facultad = new FacultadJpa();
            facultad.setNombre("Facultad");

            facultadRepository.save(facultad);
            departamento.setFacultad(facultad);
            DepartamentoJpa departamentoJpa = departamentoRepository.save(departamento);
            programa.setDepartamento(departamento);
            ProgramaJpa programaJpa = programaRepository.save(programa);

            estudiante.setPrograma(programaMapper.jpaToDomain(programaJpa));
            jefeDepartamento.relacionarDepartamento(departamentoMapper.jpaToDomain(departamentoJpa));
            profesor.relacionarDepartamento(departamentoMapper.jpaToDomain(departamentoJpa));
            coordinador.relacionarDepartamento(departamentoMapper.jpaToDomain(departamentoJpa));

            JefeDepartamentoJpa jefeDepartamentoJpa = jefeDepartamentoRepository.save(jefeDepartamentoMapper.domainToJpa(jefeDepartamento));
            CoordinadorJpa coordinadorJpa = coordinadorRepository.save(coordinadorMapper.domainToJpa(coordinador));
            ProfesorJpa profesorJpa = profesorRepository.save(profesorMapper.domainToJpa(profesor));
            EstudianteJpa estudianteJpa = estudianteRepository.save(estudianteMapper.domainToJpa(estudiante));

            programa.getEstudiantes().add(estudianteJpa);
            departamento.setJefeDepartamento(jefeDepartamentoJpa);
            departamento.setCoordinador(coordinadorJpa);
            departamento.getProfesores().add(profesorJpa);

            programaRepository.save(programa);
            departamentoRepository.save(departamento);


            log.info("Datos cargados correctamente");

        } catch (Exception e) {
            System.out.println("ERROR EN COMMAND LINE RUNNER: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
