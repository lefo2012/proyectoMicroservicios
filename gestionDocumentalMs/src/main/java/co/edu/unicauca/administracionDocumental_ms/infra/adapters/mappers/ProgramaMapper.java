package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.Departamento;
import co.edu.unicauca.administracionDocumental_ms.entities.Programa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProgramaJpa;

import org.springframework.stereotype.Component;

@Component
public class ProgramaMapper {

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
