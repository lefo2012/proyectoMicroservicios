package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.AnteProyecto;
import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProfesorDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoRequest;
import co.edu.unicauca.administracionDocumental_ms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements BaseService<Profesor,String>{
    @Autowired
    ProyectoDomainReposiroty proyectoReposiroty;
    @Autowired
    ProfesorDomainRepository profesorRepository;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private DepartamentoDomainRepository departamentoRepository;
    @Autowired
    private AnteProyectoDomainRepository anteProyectoRepository;
    @Autowired
    private JefeDepartamentoDomainRepository jefeDepartamentoRepository;
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
            Optional<Profesor> profesor = profesorRepository.findByCorreo(id);
            System.out.println(id);
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
            Optional<Profesor> profesorOptional = profesorRepository.findByCorreo(correoElectronico);
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
            throw new Exception("Error al listar proyectos de grado: "+ ex.getMessage());
        }
    }
    public void subirAnteproyecto(ProyectoDeGrado proyectoDeGrado,Profesor profesor,String nombreAnteproyecto) throws Exception
    {
        try
        {
            AnteProyecto anteProyecto = new AnteProyecto(nombreAnteproyecto);
            proyectoReposiroty.save(profesor.subirAnteproyecto(proyectoDeGrado,anteProyectoRepository.save(anteProyecto)));
            jefeDepartamentoRepository.save(profesor.getDepartamento().getJefeDepartamento());
        }catch (Exception ex)
        {

            ex.printStackTrace();
            System.out.println(ex.getMessage());
            throw new Exception("Error al subir anteproyecto: "+ex.getMessage());

        }

    }
    public Profesor mapearDto(PersonaDto personaDto) throws Exception {
        Profesor profesor = new Profesor();
        profesor.setNombre(personaDto.getNombre());
        profesor.setApellido(personaDto.getApellido());
        profesor.setCelular(personaDto.getCelular());
        profesor.setId(personaDto.getId());
        profesor.setDepartamento(departamentoRepository.findById(personaDto.getIdDepartamento()).orElseThrow(() -> new Exception("Director no encontrado")));
        return profesor;
    }

    public List<ProfesorDto> obtenerProfesoresDisponiblesDto(long idProyecto) throws Exception {

        try{

            ProyectoDeGrado proyecto = proyectoReposiroty.findById(idProyecto).orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

            List<Profesor> profesores = obtenerProfesoresDisponibles(proyecto);

            return profesores.stream()
                    .map(profesor -> new ProfesorDto(
                            profesor.getId(),
                            profesor.getNombre(),
                            profesor.getApellido(),
                            profesor.getCorreoElectronico()
                    )).toList();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            throw new Exception("Error al obtener los profesores: "+ex.getMessage());
        }


    }

    public List<Profesor> obtenerProfesoresDisponibles(ProyectoDeGrado proyecto) {
        Profesor director = proyecto.getDirector();
        List<Profesor> codirectores = proyecto.getCodirectores();

        return profesorRepository.findAll()
                .stream()
                .filter(profesor -> profesor.getId() != director.getId())
                .filter(profesor -> codirectores.stream().noneMatch(cd -> cd.getId() == profesor.getId())).toList();
    }

}
