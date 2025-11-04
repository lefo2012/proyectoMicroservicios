package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.users_ms.rabbitConfig.PersonaProducer;
import co.edu.unicauca.users_ms.repository.DepartamentoRepository;
import co.edu.unicauca.users_ms.repository.ProgramaRepository;
import co.edu.unicauca.users_ms.util.Encriptador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterServiceTest {

    @InjectMocks
    private RegisterService registerService;

    @Mock private JefeDepartamentoService jefeDepartamentoService;
    @Mock private CoordinadorService coordinadorService;
    @Mock private ProfesorService profesorService;
    @Mock private EstudianteService estudianteService;
    @Mock private ProgramaRepository programaRepository;
    @Mock private DepartamentoRepository departamentoRepository;
    @Mock private Encriptador encriptador;
    @Mock private PersonaProducer personaProducer;
    @Mock private GestionProyectoCliente gestionProyectoCliente;

    @Mock private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(encriptador.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded123");
    }

    @Test
    void registrarPersona_estudiante_ok() throws Exception {
        PersonaRegistrarDto dto = new PersonaRegistrarDto();
        dto.setRol("ESTUDIANTE");
        dto.setCorreoElectronico("est@unicauca.edu.co");
        dto.setNombre("Juan");
        dto.setApellido("Pérez");
        dto.setCelular("1234");
        dto.setPassword("123");
        dto.setIdPrograma(1);

        Programa programa = new Programa();
        programa.setId(1);
        programa.setNombre("Ingeniería");

        when(estudianteService.existsByCorreo(anyString())).thenReturn(false);
        when(programaRepository.findById(1)).thenReturn(Optional.of(programa));
        Estudiante estudiante = new Estudiante();
        estudiante.setPrograma(programa);
        when(estudianteService.save(any())).thenReturn(estudiante);

        PersonaDto result = registerService.registrarPersona(dto);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        assertEquals("Ingeniería", result.getNombreProgama());
        assertTrue(result.getRoles().contains("ESTUDIANTE"));
        verify(personaProducer).enviarPersona(any(PersonaDto.class));
    }

    @Test
    void registrarPersona_profesor_ok() throws Exception {
        PersonaRegistrarDto dto = new PersonaRegistrarDto();
        dto.setRol("PROFESOR");
        dto.setCorreoElectronico("prof@unicauca.edu.co");
        dto.setNombre("Carlos");
        dto.setApellido("Suarez");
        dto.setCelular("987");
        dto.setPassword("123");
        dto.setIdDepartamento(2);

        Departamento dep = new Departamento();
        dep.setId(2);
        dep.setNombre("Matemáticas");

        when(profesorService.existsByCorreo(anyString())).thenReturn(false);
        when(departamentoRepository.findById(2)).thenReturn(Optional.of(dep));
        Profesor profesor = new Profesor();
        profesor.setDepartamento(dep);
        when(profesorService.save(any())).thenReturn(profesor);

        PersonaDto result = registerService.registrarPersona(dto);

        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombreDepartamento());
        assertTrue(result.getRoles().contains("PROFESOR"));
        verify(personaProducer).enviarPersona(any(PersonaDto.class));
    }

}
