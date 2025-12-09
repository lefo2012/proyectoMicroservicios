package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.*;
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
            System.out.println("hola si entre a guardar el coordinador");
            CoordinadorJpa coordinador = new CoordinadorJpa();
            coordinador.setId(departamento.getCoordinador().getId());
            coordinador.setCorreoElectronico(departamento.getCoordinador().getCorreoElectronico());
            dep.setCoordinador(coordinador);
        }
        if(dep.getJefeDepartamento() != null){
            JefeDepartamentoJpa jefe = new JefeDepartamentoJpa();
            jefe.setId(departamento.getJefeDepartamento().getId());
            jefe.setNombre(departamento.getJefeDepartamento().getNombre());
            jefe.setApellido(departamento.getJefeDepartamento().getApellido());
            jefe.setCorreoElectronico(departamento.getJefeDepartamento().getCorreoElectronico());
            dep.setJefeDepartamento(jefe);
        }
        if(dep.getProfesores() != null)
        {
            List<ProfesorJpa> profesores = new ArrayList<ProfesorJpa>();
            for(Profesor p : departamento.getProfesores())
            {
                ProfesorJpa profesor = new ProfesorJpa();
                profesor.setId(p.getId());
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
            coordinador.setId(departamento.getCoordinador().getId());
            coordinador.setCorreoElectronico(departamento.getCoordinador().getCorreoElectronico());
            dep.setCoordinador(coordinador);
        }
        if(dep.getJefeDepartamento() != null){
            JefeDepartamento jefeDepartamento = new JefeDepartamento();
            jefeDepartamento.setId(departamento.getJefeDepartamento().getId());
            jefeDepartamento.setNombre(departamento.getJefeDepartamento().getNombre());
            jefeDepartamento.setApellido(departamento.getJefeDepartamento().getApellido());
            jefeDepartamento.setCorreoElectronico(departamento.getJefeDepartamento().getCorreoElectronico());
            dep.setJefeDepartamento(jefeDepartamento);
        }
        if(dep.getProfesores() == null)
        {
            List<Profesor> profesores = new ArrayList<Profesor>();
            for(ProfesorJpa p : departamento.getProfesores())
            {
                Profesor profesor = new Profesor();
                profesor.setId(p.getId());
                profesores.add(profesor);
            }
            dep.setProfesores(profesores);
        }
        if(departamento.getFacultad() != null)
        {
            Facultad facultad = new Facultad();
            facultad.setId(departamento.getFacultad().getId());
            dep.setFacultad(facultad);
        }
        return dep;
    }


}
