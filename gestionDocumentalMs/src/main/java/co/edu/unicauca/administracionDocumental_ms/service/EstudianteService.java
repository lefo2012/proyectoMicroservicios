package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.repository.EstudianteRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProgramaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements BaseService<Estudiante,String>{

    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    ProgramaRepository programaRepository;
    @Override
    @Transactional
    public List<Estudiante> findAll() throws Exception {

        return List.of();
    }

    @Override
    @Transactional
    public Estudiante findById(String id) throws Exception {
        try{
            Optional<Estudiante> estudiante = estudianteRepository.findByCorreoElectronico(id);
            System.out.println(estudiante);
            return estudiante.orElse(null);
        }catch(Exception ex){
            throw new Exception("Error al buscar al estudiante con id: "+id+" "+ ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudiante save(Estudiante entity) throws Exception {
        try{
            return estudianteRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar estudiante: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudiante updateById(Estudiante entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) throws Exception {
        return false;
    }

    @Transactional
    public List<ProyectoDto> listaProyecto(String correoElectronico) throws Exception{
        try{
            List<ProyectoDto> listaProyectos;
            Optional<Estudiante> estudianteC = estudianteRepository.findByCorreoElectronico(correoElectronico);
            Estudiante estudiante = estudianteC.orElse(null);
            if(estudiante != null)
            {
                listaProyectos = new ArrayList<>();
                for(ProyectoDeGrado proyecto : estudiante.getProyectosDeGrado())
                {
                    listaProyectos.add(proyectoService.mapearProyecto(proyecto));
                }
                return listaProyectos;
            }else
            {
                throw new Exception("Estudiante no encontrado");
            }

        }catch(Exception ex){
            throw new Exception("Error al listar proyectos de grado: "+ex.getMessage());
        }
    }
    public Estudiante mapearDto(PersonaDto personaDto)
    {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(personaDto.getNombre());
        estudiante.setApellido(personaDto.getApellido());
        estudiante.setCelular(personaDto.getCelular());
        estudiante.setCorreoElectronico(personaDto.getCorreoElectronico());
        estudiante.setPrograma(programaRepository.getById(personaDto.getIdPrograma()));
        return estudiante;
    }
}
