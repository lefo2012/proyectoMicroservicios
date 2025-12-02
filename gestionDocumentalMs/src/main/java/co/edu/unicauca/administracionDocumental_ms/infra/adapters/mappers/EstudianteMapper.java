package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.Programa;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.EstudianteJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProgramaJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProyectoDeGradoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstudianteMapper {

    @Autowired
    private ProyectoDeGradoMapper proyectoDeGradoMapper;

    public Estudiante jpaToDomain(EstudianteJpa e) {
        Estudiante domain = new Estudiante();
        domain.setId(e.getId());
        domain.setNombre(e.getNombre());
        domain.setApellido(e.getApellido());
        domain.setCelular(e.getCelular());
        domain.setCorreoElectronico(e.getCorreoElectronico());
        if(e.getPrograma()!=null)
        {
            Programa programa = new Programa();
            programa.setId(e.getPrograma().getId());
            domain.setPrograma(programa);
        }

        if(e.getProyectosDeGrado()!=null)
        {
            List<ProyectoDeGrado> proyectosDeGrado = new ArrayList<ProyectoDeGrado>();
            for(ProyectoDeGradoJpa proyectoDeGradoJpa : e.getProyectosDeGrado())
            {
                proyectosDeGrado.add(proyectoDeGradoMapper.jpaToDomain(proyectoDeGradoJpa));
            }

            domain.setProyectosDeGrado(proyectosDeGrado);
        }
        return domain;
    }

    public EstudianteJpa domainToJpa(Estudiante e) {
        EstudianteJpa jpa = new EstudianteJpa();
        jpa.setId(e.getId());
        jpa.setNombre(e.getNombre());
        jpa.setApellido(e.getApellido());
        jpa.setCelular(e.getCelular());
        jpa.setCorreoElectronico(e.getCorreoElectronico());
        if(e.getPrograma()!=null) {
            ProgramaJpa programa = new ProgramaJpa();
            programa.setId(e.getPrograma().getId());
            jpa.setPrograma(programa);
        }

        if(e.getProyectosDeGrado()!=null)
        {
            List<ProyectoDeGradoJpa> proyectosDeGrado = new ArrayList<ProyectoDeGradoJpa>();
            for(ProyectoDeGrado proyectoDeGrado : e.getProyectosDeGrado())
            {
                proyectosDeGrado.add(proyectoDeGradoMapper.domainToJpa(proyectoDeGrado));
            }

            jpa.setProyectosDeGrado(proyectosDeGrado);
        }
        return jpa;
    }
}
