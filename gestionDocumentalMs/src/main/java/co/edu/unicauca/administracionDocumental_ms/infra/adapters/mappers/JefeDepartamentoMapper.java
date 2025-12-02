package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.Departamento;
import co.edu.unicauca.administracionDocumental_ms.entities.JefeDepartamento;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.JefeDepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProyectoDeGradoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JefeDepartamentoMapper {
    @Autowired
    private ProyectoDeGradoMapper proyectoDeGradoMapper;

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
        if(jefeDepartamento.getProyectosDeGrado()!=null)
        {
            List<ProyectoDeGrado> proyectosDeGrado = new ArrayList<ProyectoDeGrado>();
            for(ProyectoDeGradoJpa proyectoDeGradoJpa : jefeDepartamento.getProyectosDeGrado())
            {
                proyectosDeGrado.add(proyectoDeGradoMapper.jpaToDomain(proyectoDeGradoJpa));
            }

            jefe.setProyectosDeGrado(proyectosDeGrado);
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
        if(jefeDepartamento.getProyectosDeGrado()!=null)
        {
            List<ProyectoDeGradoJpa> proyectosDeGrado = new ArrayList<ProyectoDeGradoJpa>();
            for(ProyectoDeGrado proyectoDeGrado : jefeDepartamento.getProyectosDeGrado())
            {
                proyectosDeGrado.add(proyectoDeGradoMapper.domainToJpa(proyectoDeGrado));
            }

            jefe.setProyectosDeGrado(proyectosDeGrado);
        }
        return jefe;
    }
}
