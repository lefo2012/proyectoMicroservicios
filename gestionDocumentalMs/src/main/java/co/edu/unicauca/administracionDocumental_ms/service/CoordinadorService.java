package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.repository.CoordinadorRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoordinadorService implements BaseService<Coordinador,String> {
    @Autowired
    CoordinadorRepository coordinadorRepository;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Override
    @Transactional
    public List<Coordinador> findAll() throws Exception {
        return List.of();
    }

    @Override
    @Transactional
    public Coordinador findById(String id) throws Exception {
        try {
            Optional<Coordinador> coordinador = coordinadorRepository.findByCorreoElectronico(id);

            System.out.println(coordinador);
            return coordinador.orElse(null);
        }catch(Exception ex){
            throw new Exception("Error al buscar el coordinador con id: "+id+" :"+ex.getMessage());
        }

    }



    @Override
    @Transactional
    public Coordinador save(Coordinador entity) throws Exception {
        try{
            return coordinadorRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar coordinador: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Coordinador updateById(Coordinador entity) throws Exception {
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
            Optional<Coordinador> coordinadorC = coordinadorRepository.findByCorreoElectronico(correoElectronico);
            Coordinador coordinador = coordinadorC.orElse(null);
            if(coordinador != null)
            {
                listaProyectos = new ArrayList<ProyectoDto>();
                for(ProyectoDeGrado proyectoDeGrado: coordinador.getProyectosDeGrado()){
                    listaProyectos.add(proyectoService.mapearProyecto(proyectoDeGrado));
                }
                return listaProyectos;
            }else
            {
                throw new Exception("Coordinador no encontrado");
            }

        }catch(Exception ex){
            throw new Exception("Error al listar proyectos de grado: "+ex.getMessage());
        }
    }
    public Coordinador mapearDto(PersonaDto personaDto)
    {
        Coordinador coordinador = new Coordinador();
        coordinador.setNombre(personaDto.getNombre());
        coordinador.setApellido(personaDto.getApellido());
        coordinador.setCelular(personaDto.getCelular());
        coordinador.setCorreoElectronico(personaDto.getCorreoElectronico());
        coordinador.relacionarDepartamento(departamentoRepository.getById(personaDto.getIdDepartamento()));
        return coordinador;
    }
}
