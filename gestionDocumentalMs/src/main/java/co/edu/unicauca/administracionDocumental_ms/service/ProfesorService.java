package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.repository.DepartamentoRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements BaseService<Profesor,String>{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    @Transactional
    public List<Profesor> findAll() throws Exception {
        try{
            return profesorRepository.findAll();
        }catch (Exception ex)
        {
            throw new Exception("Error al buscar todos los profesores: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Profesor findById(String id) throws Exception {
        try
        {
            Optional<Profesor> profesor = profesorRepository.findByCorreoElectronico(id);
            System.out.println(profesor);
            return profesor.orElse(null);
        }catch(Exception ex)
        {
            throw new Exception("Error al buscar el profesor con id: "+id+" :"+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public Profesor save(Profesor entity) throws Exception {
        try{
            return profesorRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar profesor: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Profesor updateById(Profesor entity) throws Exception {
        try {
            return profesorRepository.save(entity);
        }catch (Exception ex){
            throw new Exception("Error al guardar profesor: "+ex.getMessage());
        }

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
            Optional<Profesor> profesorOptional = profesorRepository.findByCorreoElectronico(correoElectronico);
            Profesor profesor = profesorOptional.orElse(null);

            if (profesor != null) {
                listaProyectos = new ArrayList<>();
                for (ProyectoDeGrado proyectoDeGrado: profesor.getProyectosDeGradoDirigidos()){
                    listaProyectos.add(proyectoService.mapearProyecto(proyectoDeGrado));
                }

                for (ProyectoDeGrado proyectoDeGradoc: profesor.getProyectosDeGradoCodirigidos()){
                    listaProyectos.add(proyectoService.mapearProyecto(proyectoDeGradoc));
                }

                return listaProyectos;
            }
            else
            {
                throw new Exception("Profesor no encontrado");
            }
        }catch (Exception ex){
            throw new Exception("Error al listar proyectos de grado: "+ex.getMessage());
        }
    }

    public Profesor mapearDto(PersonaDto personaDto)
    {
        Profesor profesor = new Profesor();
        profesor.setNombre(personaDto.getNombre());
        profesor.setApellido(personaDto.getApellido());
        profesor.setCelular(personaDto.getCelular());
        profesor.setCorreoElectronico(personaDto.getCorreoElectronico());
        profesor.setDepartamento(departamentoRepository.getById(personaDto.getIdDepartamento()));
        return profesor;
    }
}
