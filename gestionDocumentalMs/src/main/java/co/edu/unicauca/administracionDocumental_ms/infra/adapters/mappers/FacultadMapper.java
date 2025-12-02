package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.Departamento;
import co.edu.unicauca.administracionDocumental_ms.entities.Facultad;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.FacultadJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FacultadMapper {


    @Autowired
    DepartamentoMapper departamentoMapper;

    public Facultad jpaToDomain(FacultadJpa facultadJpa)
    {
        Facultad domain = new Facultad();
        domain.setId(facultadJpa.getId());
        domain.setNombre(facultadJpa.getNombre());
        if(domain.getDepartamentos()==null)
        {
            List<Departamento> departamentos = new ArrayList<>();
            for(DepartamentoJpa departamentoJpa : facultadJpa.getDepartamentos()) {
                Departamento departamento = new Departamento();
                departamento.setId(departamentoJpa.getId());
                departamentos.add(departamento);
            }
            domain.setDepartamentos(departamentos);
        }
        return domain;
    }

    public FacultadJpa domainToJpa(Facultad facultad) {
        FacultadJpa jpa = new FacultadJpa();
        jpa.setId(facultad.getId());
        jpa.setNombre(facultad.getNombre());
        if(jpa.getDepartamentos()==null)
        {
            List<DepartamentoJpa> departamentos = new ArrayList<>();
            for(Departamento departamentoJpa : facultad.getDepartamentos())
            {
                DepartamentoJpa departamento = new DepartamentoJpa();
                departamento.setId(departamentoJpa.getId());
                departamentos.add(departamento);
            }
            jpa.setDepartamentos(departamentos);
        }
        return jpa;
    }
}
