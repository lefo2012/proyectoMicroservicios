package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.repository.DepartamentoRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.FacultadRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.administracionDocumental_ms.util.DecodificadorJWT;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private JefeDepService jefeDepService;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CoordinadorService coordinadorService;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProgramaRepository programaRepository;


    public Persona mapearDto(PersonaDto personaDto) throws Exception {
        try
        {
            List<String> roles = DecodificadorJWT.getRoles(personaDto.getToken());
            if(roles.getFirst().equals("ESTUDIANTE"))
            {
                return estudianteService.mapearDto(personaDto);
            }
            else if (roles.getFirst().equals("PROFESOR"))
            {
                return profesorService.mapearDto(personaDto);
            }
            else if (roles.getFirst().equals("COORDINADOR"))
            {
                return coordinadorService.mapearDto(personaDto);
            }
            else if (roles.getFirst().equals("JEFEDEPARTAMENTO"))
            {
                return jefeDepService.mapearDto(personaDto);
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Imposible mapear el dto");
        }
        return null;
    }
    public Persona guardar(Persona persona) throws Exception
    {
        try {
            if(persona instanceof Estudiante estudiante)
            {
                return estudianteService.save(estudiante);
            }
            else if (persona instanceof Profesor profesor)
            {
                return profesorService.save(profesor);
            }
            else if (persona instanceof  Coordinador coordinador)
            {
                return coordinadorService.save(coordinador);
            }
            else if (persona instanceof JefeDepartamento jefeDepartamento)
            {
                return jefeDepService.save(jefeDepartamento);
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Imposible guardar la persona");
        }
        return null;
    }
}
