package co.edu.unicauca.users_ms.infra.adapters.mappers;

import co.edu.unicauca.users_ms.entity.Departamento;
import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.entity.Profesor;
import co.edu.unicauca.users_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.users_ms.infra.jpa.ProfesorJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public Profesor jpaToDomain(ProfesorJpa p)
    {
        Profesor profesor =new Profesor();
        profesor.setId(p.getId());
        profesor.setNombre(p.getNombre());
        profesor.setApellido(p.getApellido());
        profesor.setCelular(p.getCelular());
        profesor.setCorreoElectronico(p.getCorreoElectronico());
        if(p.getDepartamento()!=null)
        {
            Departamento departamento =new Departamento();
            departamento.setId(p.getDepartamento().getId());
            profesor.setDepartamento(departamento);
        }
        return profesor;
    }

    public ProfesorJpa domainToJpa(Profesor p) {
        ProfesorJpa profesor =new ProfesorJpa();
        profesor.setId(p.getId());
        profesor.setNombre(p.getNombre());
        profesor.setApellido(p.getApellido());
        profesor.setCelular(p.getCelular());
        profesor.setCorreoElectronico(p.getCorreoElectronico());
        if(p.getDepartamento()!=null)
        {
            DepartamentoJpa departamento =new DepartamentoJpa();
            departamento.setId(p.getDepartamento().getId());
            profesor.setDepartamento(departamento);
        }
        return profesor;
    }
}
