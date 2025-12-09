package co.edu.unicauca.users_ms.infra.adapters.mappers;

import co.edu.unicauca.users_ms.entity.Estudiante;
import co.edu.unicauca.users_ms.entity.Programa;
import co.edu.unicauca.users_ms.infra.jpa.EstudianteJpa;
import co.edu.unicauca.users_ms.infra.jpa.ProgramaJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper {

    @Autowired
    ProgramaMapper programaMapper;
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
        return domain;
    }

    public EstudianteJpa domainToJpa(Estudiante e) {
        EstudianteJpa jpa = new EstudianteJpa();
        jpa.setId(e.getId());
        jpa.setNombre(e.getNombre());
        jpa.setApellido(e.getApellido());
        jpa.setCelular(e.getCelular());
        jpa.setCorreoElectronico(e.getCorreoElectronico());
        if(e.getPrograma()!=null)
        {
            ProgramaJpa programa = new ProgramaJpa();
            programa.setId(e.getPrograma().getId());
            jpa.setPrograma(programa);
        }
        return jpa;
    }
}
