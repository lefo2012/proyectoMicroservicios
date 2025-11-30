package co.edu.unicauca.users_ms.infra.adapters.mappers;

import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.jpa.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartamentoMapper {
    public DepartamentoJpa domainToJpa(Departamento departamento)
    {
        DepartamentoJpa dep = new DepartamentoJpa();
        dep.setId(departamento.getId());
        dep.setNombre(departamento.getNombre());
        if(dep.getProgramas() == null)
        {
            List<ProgramaJpa> programas = new ArrayList<ProgramaJpa>();
            for(Programa p : departamento.getProgramas())
            {
                ProgramaJpa programa = new ProgramaJpa();
                programa.setId(p.getId());
                programas.add(programa);
            }

            dep.setProgramas(programas);
        }
        if(dep.getCoordinador() != null)
        {
            CoordinadorJpa coordinador = new CoordinadorJpa();
            coordinador.setId(departamento.getCoordinador().getId());
            dep.setCoordinador(coordinador);
        }
        if(dep.getJefeDepartamento() != null){
            JefeDepartamentoJpa jefe = new JefeDepartamentoJpa();
            jefe.setCorreoElectronico(departamento.getJefeDepartamento().getCorreoElectronico());
            dep.setJefeDepartamento(jefe);
        }
        if(dep.getProfesores() == null)
        {
            List<ProfesorJpa> profesores = new ArrayList<ProfesorJpa>();
            for(Profesor p : departamento.getProfesores())
            {
                ProfesorJpa profesor = new ProfesorJpa();
                profesor.setCorreoElectronico(p.getCorreoElectronico());
                profesores.add(profesor);
            }
            dep.setProfesores(profesores);
        }
        if(dep.getFacultad() != null)
        {
            FacultadJpa facultad = new FacultadJpa();
            facultad.setId(departamento.getFacultad().getId());
            dep.setFacultad(facultad);
        }
        return dep;
    }

    public Departamento jpaToDomain(DepartamentoJpa departamento)
    {

        Departamento dep = new Departamento();
        dep.setId(departamento.getId());
        dep.setNombre(departamento.getNombre());
        if(dep.getProgramas() == null)
        {
            List<Programa> programas = new ArrayList<Programa>();
            for(ProgramaJpa p : departamento.getProgramas())
            {
                Programa programa = new Programa();
                programa.setId(p.getId());
                programas.add(programa);
            }

            dep.setProgramas(programas);
        }
        if(dep.getCoordinador() != null)
        {
            Coordinador coordinador = new Coordinador();
            coordinador.setCorreoElectronico(departamento.getCoordinador().getCorreoElectronico());
            dep.setCoordinador(coordinador);
        }
        if(dep.getJefeDepartamento() != null){
            JefeDepartamento jefeDepartamento = new JefeDepartamento();
            jefeDepartamento.setCorreoElectronico(departamento.getJefeDepartamento().getCorreoElectronico());
            dep.setJefeDepartamento(jefeDepartamento);
        }
        if(dep.getProfesores() == null)
        {
            List<Profesor> profesores = new ArrayList<Profesor>();
            for(ProfesorJpa p : departamento.getProfesores())
            {
                Profesor profesor = new Profesor();
                profesor.setCorreoElectronico(p.getCorreoElectronico());
                profesores.add(profesor);
            }
            dep.setProfesores(profesores);
        }
        if(dep.getFacultad() != null)
        {
            Facultad facultad = new Facultad();
            facultad.setId(departamento.getFacultad().getId());
            dep.setFacultad(facultad);
        }
        return dep;
    }


}
