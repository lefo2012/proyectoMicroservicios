package co.edu.unicauca.administracionDocumental_ms.service;


import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.JefeDepartamento;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.repository.DepartamentoRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.JefeDepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JefeDepService implements  BaseService <JefeDepartamento,String>{

    @Autowired
    private JefeDepartamentoRepository JefeDepRepository;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Override
    @Transactional
    public List<JefeDepartamento> findAll() throws Exception {
        try{
            return JefeDepRepository.findAll();
        }catch (Exception ex)
        {
            throw new Exception("Error al buscar todos los profesores: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public JefeDepartamento findById(String id) throws Exception {
        try
        {
            Optional<JefeDepartamento> profesor = JefeDepRepository.findByCorreoElectronico(id);
            System.out.println(profesor);
            return profesor.orElse(null);
        }catch(Exception ex)
        {
            throw new Exception("Error al buscar el profesor con id: "+id+" :"+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public JefeDepartamento save(JefeDepartamento entity) throws Exception {
        try{
            return JefeDepRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar profesor: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public JefeDepartamento updateById(JefeDepartamento entity) throws Exception {
        try {
            return JefeDepRepository.save(entity);
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
            Optional<JefeDepartamento> jefeDepartamentoR = JefeDepRepository.findByCorreoElectronico(correoElectronico);
            JefeDepartamento jefeDepartamento = jefeDepartamentoR.orElse(null);
            if(jefeDepartamento != null)
            {
                listaProyectos = new ArrayList<>();
                for(ProyectoDeGrado proyecto : jefeDepartamento.getProyectosDeGrado())
                {
                    listaProyectos.add(proyectoService.mapearProyecto(proyecto));
                }
                return listaProyectos;
            }else
            {
                throw new Exception("Jefe de departamento no encontrado: "+correoElectronico);
            }

        }catch(Exception ex){
            throw new Exception("Error al listar proyectos de grado: "+ex.getMessage());
        }
    }
    public JefeDepartamento mapearDto(PersonaDto personaDto)
    {
        JefeDepartamento jefeDepartamento = new JefeDepartamento();
        jefeDepartamento.setNombre(personaDto.getNombre());
        jefeDepartamento.setApellido(personaDto.getApellido());
        jefeDepartamento.setCelular(personaDto.getCelular());
        jefeDepartamento.setCorreoElectronico(personaDto.getCorreoElectronico());
        jefeDepartamento.setDepartamento(departamentoRepository.getById(personaDto.getIdDepartamento()));
        return jefeDepartamento;
    }
}
