package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.AnteProyecto;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.AnteProyectoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnteProyectoMapper {
    @Autowired
    private ProyectoDeGradoMapper proyectoMapper;

    @Autowired
    private ProfesorMapper profesorMapper;

    public AnteProyecto jpaToDomain(AnteProyectoJpa jpa) {
        if (jpa == null) return null;

        AnteProyecto domain = new AnteProyecto();
        domain.setId(jpa.getId());
        domain.setNombre(jpa.getNombre());

        if(jpa.getProyectoDeGrado()!=null){
            domain.setProyectoDeGrado(proyectoMapper.jpaToDomain(jpa.getProyectoDeGrado()));
        }
        if(jpa.getEvaluador1()!=null){
            domain.setEvaluador1(profesorMapper.jpaToDomain(jpa.getEvaluador1()));
        }
        if(jpa.getEvaluador2()!=null){
            domain.setEvaluador2(profesorMapper.jpaToDomain(jpa.getEvaluador2()));
        }

        return domain;
    }

    public AnteProyectoJpa domainToJpa(AnteProyecto domain) {
        if (domain == null) return null;

        AnteProyectoJpa jpa = new AnteProyectoJpa();
        jpa.setId(domain.getId());
        jpa.setNombre(domain.getNombre());

        if(domain.getProyectoDeGrado()!=null){
            jpa.setProyectoDeGrado(proyectoMapper.domainToJpa(domain.getProyectoDeGrado()));
        }
        if(domain.getEvaluador1()!=null){
            jpa.setEvaluador1(profesorMapper.domainToJpa(domain.getEvaluador1()));
        }
        if(domain.getEvaluador2()!=null){
            jpa.setEvaluador2(profesorMapper.domainToJpa(domain.getEvaluador2()));
        }
        return jpa;
    }
}
