package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.AnteProyecto;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.AnteProyectoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProfesorJpa;
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
            Profesor evaluador1 = new Profesor();
            evaluador1.setId(jpa.getEvaluador1().getId());
            evaluador1.setNombre(jpa.getEvaluador1().getNombre());
            evaluador1.setApellido(jpa.getEvaluador1().getApellido());
            evaluador1.setCorreoElectronico(jpa.getEvaluador1().getCorreoElectronico());
            domain.setEvaluador1(evaluador1);
        }
        if(jpa.getEvaluador2()!=null){
            Profesor evaluador2 = new Profesor();
            evaluador2.setId(jpa.getEvaluador2().getId());
            evaluador2.setNombre(jpa.getEvaluador2().getNombre());
            evaluador2.setApellido(jpa.getEvaluador2().getApellido());
            evaluador2.setCorreoElectronico(jpa.getEvaluador2().getCorreoElectronico());
            domain.setEvaluador2(evaluador2);
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
            ProfesorJpa evaluador1 = new ProfesorJpa();
            evaluador1.setId(domain.getEvaluador1().getId());
            evaluador1.setNombre(domain.getEvaluador1().getNombre());
            evaluador1.setApellido(domain.getEvaluador1().getApellido());
            evaluador1.setCorreoElectronico(domain.getEvaluador1().getCorreoElectronico());
            jpa.setEvaluador1(evaluador1);
            //jpa.setEvaluador1(profesorMapper.domainToJpa(domain.getEvaluador1()));
        }
        if(domain.getEvaluador2()!=null){
            ProfesorJpa evaluador2 = new ProfesorJpa();
            evaluador2.setId(domain.getEvaluador2().getId());
            evaluador2.setNombre(domain.getEvaluador2().getNombre());
            evaluador2.setApellido(domain.getEvaluador2().getApellido());
            evaluador2.setCorreoElectronico(domain.getEvaluador2().getCorreoElectronico());
            jpa.setEvaluador2(evaluador2);
        }
        return jpa;
    }
}
