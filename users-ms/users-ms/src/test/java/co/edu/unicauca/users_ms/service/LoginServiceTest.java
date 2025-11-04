package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.util.Encriptador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @Mock
    private JefeDepartamentoService jefeDepartamentoService;

    @Mock
    private CoordinadorService coordinadorService;

    @Mock
    private ProfesorService profesorService;

    @Mock
    private EstudianteService estudianteService;

    @Mock
    private Encriptador encriptador;

    @InjectMocks
    private LoginService loginService;

    private PasswordEncoder encoderMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encoderMock = mock(PasswordEncoder.class);
        when(encriptador.passwordEncoder()).thenReturn(encoderMock);
    }

    // ==========================================================
    // ✅ CASOS CORRECTOS
    // ==========================================================

    @Test
    void iniciarSesion_profesor_ok() throws Exception {
        Profesor prof = new Profesor();
        prof.setCorreoElectronico("prof@uni.edu");
        prof.setPassword("encoded");
        Departamento dep = new Departamento();
        dep.setId(1);
        dep.setNombre("Ciencias");
        prof.setDepartamento(dep);

        when(profesorService.findById("prof@uni.edu")).thenReturn(prof);
        when(encoderMock.matches("123", "encoded")).thenReturn(true);

        PersonaDto result = loginService.iniciarSesion("prof@uni.edu", "123");

        assertNotNull(result);
        assertTrue(result.getRoles().contains("PROFESOR"));
        assertEquals("Ciencias", result.getNombreDepartamento());
    }

    @Test
    void iniciarSesion_coordinador_ok() throws Exception {
        Coordinador coord = new Coordinador();
        coord.setCorreoElectronico("coord@uni.edu");
        coord.setPassword("encoded");
        Departamento dep = new Departamento();
        dep.setId(2);
        dep.setNombre("Ingeniería");
        coord.setDepartamento(dep);

        when(coordinadorService.findById("coord@uni.edu")).thenReturn(coord);
        when(encoderMock.matches("123", "encoded")).thenReturn(true);

        PersonaDto result = loginService.iniciarSesion("coord@uni.edu", "123");

        assertNotNull(result);
        assertTrue(result.getRoles().contains("COORDINADOR"));
        assertEquals("Ingeniería", result.getNombreDepartamento());
    }
    @Test
    void iniciarSesion_estudiante_ok() throws Exception {
        Estudiante est = new Estudiante();
        est.setCorreoElectronico("estu@uni.edu");
        est.setPassword("encoded");
        Programa prog = new Programa();
        prog.setId(3);
        prog.setNombre("Sistemas");
        est.relacionarPrograma(prog);

        when(estudianteService.findById("estu@uni.edu")).thenReturn(est);
        when(encoderMock.matches("123", "encoded")).thenReturn(true);

        PersonaDto result = loginService.iniciarSesion("estu@uni.edu", "123");

        assertNotNull(result);
        assertTrue(result.getRoles().contains("ESTUDIANTE"));
        assertEquals("Sistemas", result.getNombreProgama());
    }

    @Test
    void iniciarSesion_jefeDepartamento_ok() throws Exception {
        JefeDepartamento jefe = new JefeDepartamento();
        jefe.setCorreoElectronico("jefe@uni.edu");
        jefe.setPassword("encoded");
        Departamento dep = new Departamento();
        dep.setId(4);
        dep.setNombre("Matemáticas");
        jefe.setDepartamento(dep);

        when(jefeDepartamentoService.findById("jefe@uni.edu")).thenReturn(jefe);
        when(encoderMock.matches("123", "encoded")).thenReturn(true);

        PersonaDto result = loginService.iniciarSesion("jefe@uni.edu", "123");

        assertNotNull(result);
        assertTrue(result.getRoles().contains("JEFEDEPARTAMENTO"));
        assertEquals("Matemáticas", result.getNombreDepartamento());
    }

    @Test
    void iniciarSesion_usuario_no_existe() throws Exception {
        when(profesorService.findById("noexiste@uni.edu")).thenReturn(null);
        when(coordinadorService.findById("noexiste@uni.edu")).thenReturn(null);
        when(estudianteService.findById("noexiste@uni.edu")).thenReturn(null);
        when(jefeDepartamentoService.findById("noexiste@uni.edu")).thenReturn(null);

        PersonaDto result = loginService.iniciarSesion("noexiste@uni.edu", "123");

        assertNull(result, "Debe devolver null si el usuario no existe");
    }

    @Test
    void iniciarSesion_contrasenia_incorrecta() throws Exception {
        Profesor prof = new Profesor();
        prof.setCorreoElectronico("prof@uni.edu");
        prof.setPassword("encoded");

        when(profesorService.findById("prof@uni.edu")).thenReturn(prof);
        when(encoderMock.matches("123", "encoded")).thenReturn(false); // Contraseña incorrecta

        PersonaDto result = loginService.iniciarSesion("prof@uni.edu", "123");

        assertNull(result, "Debe devolver null si la contraseña no coincide");
    }
}
