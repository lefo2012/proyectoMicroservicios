package co.edu.unicauca.users_ms;

import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.repository.*;
import co.edu.unicauca.users_ms.util.Encriptador;
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
    private FacultadRepository facultadRepository;

    @Autowired
    Encriptador encriptador;

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            JefeDepartamento jefeDepartamento = new JefeDepartamento();
            jefeDepartamento.setNombre("Isabella");
            jefeDepartamento.setApellido("Fernandez Pastrana");
            jefeDepartamento.setCorreoElectronico("jefe@unicauca.edu.co");
            jefeDepartamento.setPassword(encriptador.passwordEncoder().encode("123456"));

            Coordinador coordinador = new Coordinador();
            coordinador.setNombre("Nicolas");
            coordinador.setApellido("Rocha");
            coordinador.setCorreoElectronico("coor@unicauca.edu.co");
            coordinador.setPassword(encriptador.passwordEncoder().encode("123456"));

            Profesor profesor = new Profesor();
            profesor.setNombre("Jorge");
            profesor.setApellido("Curioso");
            profesor.setCorreoElectronico("prof@unicauca.edu.co");
            profesor.setPassword(encriptador.passwordEncoder().encode("123456"));

            Profesor profesor2 = new Profesor();
            profesor2.setNombre("Calamardo");
            profesor2.setApellido("Tentaculos");
            profesor2.setCorreoElectronico("prof2@unicauca.edu.co");
            profesor2.setPassword(encriptador.passwordEncoder().encode("123456"));

            Profesor profesor3 = new Profesor();
            profesor3.setNombre("Tenta");
            profesor3.setApellido("Culos");
            profesor3.setCorreoElectronico("prof3@unicauca.edu.co");
            profesor3.setPassword(encriptador.passwordEncoder().encode("123456"));

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Luis");
            estudiante.setApellido("Fierro");
            estudiante.setCorreoElectronico("est@unicauca.edu.co");
            estudiante.setPassword(encriptador.passwordEncoder().encode("123456"));

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
            profesor2.relacionarDepartamento(departamento);
            profesor3.relacionarDepartamento(departamento);
            coordinador.relacionarDepartamento(departamento);

            coordinadorRepository.save(coordinador);
            estudianteRepository.save(estudiante);
            jefeDepartamentoRepository.save(jefeDepartamento);
            profesorRepository.save(profesor);
            profesorRepository.save(profesor2);
            profesorRepository.save(profesor3);

            programaRepository.save(programa);
            departamentoRepository.save(departamento);
        }catch (Exception e)
        {
            System.out.println("ERROR EN COMMAND LINE RUNNER "+e.getMessage());
        }

    }

}
