package co.edu.unicauca.users_ms.infra.adapters.mappers;

import co.edu.unicauca.users_ms.entity.Departamento;
import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.users_ms.infra.jpa.JefeDepartamentoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JefeDepartamentoMapper {

    public JefeDepartamento jpaToDomain(JefeDepartamentoJpa jefeDepartamento)
    {
        JefeDepartamento jefe =new JefeDepartamento();
        jefe.setId(jefeDepartamento.getId());
        jefe.setNombre(jefeDepartamento.getNombre());
        jefe.setApellido(jefeDepartamento.getApellido());
        jefe.setCelular(jefeDepartamento.getCelular());
        jefe.setCorreoElectronico(jefeDepartamento.getCorreoElectronico());
        if(jefeDepartamento.getDepartamento()!=null)
        {
            Departamento departamento = new Departamento();
            departamento.setId(jefeDepartamento.getDepartamento().getId());
            jefe.setDepartamento(departamento);
        }
        return jefe;
    }

    public JefeDepartamentoJpa domainToJpa(JefeDepartamento jefeDepartamento) {
        JefeDepartamentoJpa jefe =new JefeDepartamentoJpa();
        jefe.setId(jefeDepartamento.getId());
        jefe.setNombre(jefeDepartamento.getNombre());
        jefe.setApellido(jefeDepartamento.getApellido());
        jefe.setCelular(jefeDepartamento.getCelular());
        jefe.setCorreoElectronico(jefeDepartamento.getCorreoElectronico());
        if(jefeDepartamento.getDepartamento()!=null)
        {
            DepartamentoJpa departamento = new DepartamentoJpa();
            departamento.setId(jefeDepartamento.getDepartamento().getId());
            jefe.setDepartamento(departamento);
        }
        return jefe;
    }
}
