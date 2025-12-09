package co.edu.unicauca.users_ms.infra.adapters.mappers;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.infra.jpa.CoordinadorJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoordinadorMapper {

    @Autowired
    private DepartamentoMapper departamentoMapper;

    public Coordinador jpaToDomain(CoordinadorJpa jpa) {
        if (jpa == null) return null;

        Coordinador domain = new Coordinador();
        domain.setId(jpa.getId());
        domain.setCorreoElectronico(jpa.getCorreoElectronico());
        domain.setCelular(jpa.getCelular());
        domain.setNombre(jpa.getNombre());
        domain.setApellido(jpa.getApellido());
        if(domain.getDepartamento()==null)
        {
            domain.setDepartamento(departamentoMapper.jpaToDomain(jpa.getDepartamento()));
        }
        return domain;
    }

    public CoordinadorJpa domainToJpa(Coordinador domain) {
        if (domain == null) return null;

        CoordinadorJpa jpa = new CoordinadorJpa();
        jpa.setId(domain.getId());
        jpa.setCorreoElectronico(domain.getCorreoElectronico());
        jpa.setCelular(domain.getCelular());
        jpa.setNombre(domain.getNombre());
        jpa.setApellido(domain.getApellido());
        if(jpa.getDepartamento()==null)
        {
            jpa.setDepartamento(departamentoMapper.domainToJpa(domain.getDepartamento()));
        }

        return jpa;
    }
}
