package co.edu.unicauca.users_ms.infra.adapters.mappers;

import co.edu.unicauca.users_ms.entity.Departamento;
import co.edu.unicauca.users_ms.entity.Programa;
import co.edu.unicauca.users_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.users_ms.infra.jpa.ProgramaJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProgramaMapper {

    @Autowired
    DepartamentoMapper departamentoMapper;

    public Programa jpaToDomain(ProgramaJpa p) {
        if (p == null) return null;

        Programa domain = new Programa();
        domain.setId(p.getId());
        domain.setNombre(p.getNombre());

        if (p.getDepartamento() != null) {
            Departamento dep = new Departamento();
            dep.setId(p.getDepartamento().getId());
            domain.setDepartamento(dep);
        }
        return domain;
    }

    public ProgramaJpa domainToJpa(Programa p) {
        if (p == null) return null;

        ProgramaJpa jpa = new ProgramaJpa();
        jpa.setId(p.getId());
        jpa.setNombre(p.getNombre());

        if (p.getDepartamento() != null) {
            DepartamentoJpa dep = new DepartamentoJpa();
            dep.setId(p.getDepartamento().getId());
            jpa.setDepartamento(dep);
        }
        return jpa;
    }
}
