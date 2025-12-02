package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Departamento;
import co.edu.unicauca.administracionDocumental_ms.entities.JefeDepartamento;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.CoordinadorJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.JefeDepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProyectoDeGradoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoordinadorMapper {

    @Autowired
    private DepartamentoMapper departamentoMapper;
    @Autowired
    private ProyectoDeGradoMapper proyectoDeGradoMapper;

    public Coordinador jpaToDomain(CoordinadorJpa coordinadorJpa) {
        if (coordinadorJpa == null) return null;

        Coordinador domain = new Coordinador();
        domain.setId(coordinadorJpa.getId());
        domain.setCorreoElectronico(coordinadorJpa.getCorreoElectronico());
        domain.setCelular(coordinadorJpa.getCelular());
        domain.setNombre(coordinadorJpa.getNombre());
        domain.setApellido(coordinadorJpa.getApellido());

        if(coordinadorJpa.getDepartamento()!=null)
        {
            Departamento departamento =new Departamento();
            departamento.setId(coordinadorJpa.getDepartamento().getId());

            if(coordinadorJpa.getDepartamento().getJefeDepartamento() != null)
            {
                JefeDepartamento jefeDepartamento =new JefeDepartamento();
                jefeDepartamento.setId(coordinadorJpa.getDepartamento().getJefeDepartamento().getId());
                departamento.setJefeDepartamento(jefeDepartamento);
            }
            domain.setDepartamento(departamento);
        }

        if(coordinadorJpa.getProyectosDeGrado()!=null)
        {
            List<ProyectoDeGrado> proyectosDeGrado = new ArrayList<ProyectoDeGrado>();
            for(ProyectoDeGradoJpa proyectoDeGradoJpa : coordinadorJpa.getProyectosDeGrado())
            {
                proyectosDeGrado.add(proyectoDeGradoMapper.jpaToDomain(proyectoDeGradoJpa));
            }

           domain.setProyectosDeGrado(proyectosDeGrado);
        }
        return domain;
    }

    public CoordinadorJpa domainToJpa(Coordinador coordinador) {
        if (coordinador == null) return null;

        CoordinadorJpa jpa = new CoordinadorJpa();
        jpa.setId(coordinador.getId());
        jpa.setCorreoElectronico(coordinador.getCorreoElectronico());
        jpa.setCelular(coordinador.getCelular());
        jpa.setNombre(coordinador.getNombre());
        jpa.setApellido(coordinador.getApellido());
        if(coordinador.getDepartamento()!=null)
        {
            DepartamentoJpa departamento =new DepartamentoJpa();
            departamento.setId(coordinador.getDepartamento().getId());

            if(coordinador.getDepartamento().getJefeDepartamento() != null)
            {
                JefeDepartamentoJpa jefeDepartamento =new JefeDepartamentoJpa();
                jefeDepartamento.setId(coordinador.getDepartamento().getJefeDepartamento().getId());
                departamento.setJefeDepartamento(jefeDepartamento);
            }
            jpa.setDepartamento(departamento);
        }

        if(coordinador.getProyectosDeGrado()==null)
        {
            List<ProyectoDeGradoJpa> proyectosDeGrado = new ArrayList<ProyectoDeGradoJpa>();
            for(ProyectoDeGrado proyectoDeGrado : coordinador.getProyectosDeGrado())
            {
                proyectosDeGrado.add(proyectoDeGradoMapper.domainToJpa(proyectoDeGrado));
            }

            jpa.setProyectosDeGrado(proyectosDeGrado);
        }
        return jpa;
    }
}
