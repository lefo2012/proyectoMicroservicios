package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;

import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorMapper {

    @Autowired
    private ProyectoDeGradoMapper proyectoMapper;
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
            if(p.getDepartamento().getCoordinador()!=null)
            {
                Coordinador coordinador =new Coordinador();
                coordinador.setId(p.getDepartamento().getCoordinador().getId());
                departamento.setCoordinador(coordinador);
            }
            if(p.getDepartamento().getJefeDepartamento() != null)
            {
                JefeDepartamento jefeDepartamento =new JefeDepartamento();
                jefeDepartamento.setId(p.getDepartamento().getJefeDepartamento().getId());
                jefeDepartamento.setNombre(p.getDepartamento().getJefeDepartamento().getNombre());
                jefeDepartamento.setApellido(p.getDepartamento().getJefeDepartamento().getApellido());
                jefeDepartamento.setCorreoElectronico(p.getDepartamento().getJefeDepartamento().getCorreoElectronico());
                departamento.setJefeDepartamento(jefeDepartamento);
            }


            profesor.setDepartamento(departamento);
        }
        if(p.getProyectosDeGradoDirigidos()!=null && !p.getProyectosDeGradoDirigidos().isEmpty())
        {
            System.out.println("Si tengo algun proyecto dirigido en jpa");
            List<ProyectoDeGrado> proyectosDeGrado = new ArrayList<>();
            for(ProyectoDeGradoJpa proyectoDeGradoJpa : p.getProyectosDeGradoDirigidos())
            {
                proyectosDeGrado.add(proyectoMapper.jpaToDomain(proyectoDeGradoJpa));
            }
            profesor.setProyectosDeGradoDirigidos(proyectosDeGrado);
        }
        if(p.getProyectosDeGradoCodirigidos()!=null)
        {
            List<ProyectoDeGrado> proyectosDeGrado = new ArrayList<ProyectoDeGrado>();
            for(ProyectoDeGradoJpa proyectoDeGradoJpa : p.getProyectosDeGradoCodirigidos())
            {
                ProyectoDeGrado proyectoDeGradoCodirigido =new ProyectoDeGrado();
                proyectoDeGradoCodirigido.setId(proyectoDeGradoJpa.getId());
                proyectosDeGrado.add(proyectoDeGradoCodirigido);
            }
            profesor.setProyectosDeGradoCodirigidos(proyectosDeGrado);
        }
        if(p.getAnteProyectos()==null)
        {
            List<AnteProyecto> anteProyectosDeGrado = new ArrayList<AnteProyecto>();
            for(AnteProyectoJpa anteProyectoJpa : p.getAnteProyectos())
            {
                AnteProyecto anteProyecto =new AnteProyecto();
                anteProyecto.setId(anteProyectoJpa.getId());
                anteProyectosDeGrado.add(anteProyecto);
            }
            profesor.setAnteProyectos(anteProyectosDeGrado);
        }
        if(p.getAnteProyectosEvaluadosComo1()!=null){
            List<AnteProyecto> anteProyectosComo1 = new ArrayList<AnteProyecto>();
            for(AnteProyectoJpa anteProyectoJpa : p.getAnteProyectosEvaluadosComo1())
            {
                AnteProyecto anteProyectoComo1 =new AnteProyecto();
                anteProyectoComo1.setId(anteProyectoJpa.getId());
                anteProyectosComo1.add(anteProyectoComo1);
            }
            profesor.setAnteProyectosEvaluadosComo1(anteProyectosComo1);
        }
        if(p.getAnteProyectosEvaluadosComo2()!=null){
            List<AnteProyecto> anteProyectosComo2 = new ArrayList<AnteProyecto>();
            for(AnteProyectoJpa anteProyectoJpa : p.getAnteProyectosEvaluadosComo2())
            {
                AnteProyecto anteProyectoComo1 =new AnteProyecto();
                anteProyectoComo1.setId(anteProyectoJpa.getId());
                anteProyectosComo2.add(anteProyectoComo1);
            }
            profesor.setAnteProyectosEvaluadosComo2(anteProyectosComo2);
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
            if(p.getDepartamento().getCoordinador()!=null)
            {
                CoordinadorJpa coordinador =new CoordinadorJpa();
                coordinador.setId(p.getDepartamento().getCoordinador().getId());
                departamento.setCoordinador(coordinador);
            }
            if(p.getDepartamento().getJefeDepartamento()!=null)
            {
                JefeDepartamentoJpa jefeDepartamento =new JefeDepartamentoJpa();
                jefeDepartamento.setId(departamento.getJefeDepartamento().getId());
                jefeDepartamento.setNombre(p.getDepartamento().getJefeDepartamento().getNombre());
                jefeDepartamento.setApellido(p.getDepartamento().getJefeDepartamento().getApellido());
                jefeDepartamento.setCorreoElectronico(p.getDepartamento().getJefeDepartamento().getCorreoElectronico());
                departamento.setJefeDepartamento(jefeDepartamento);
            }


            profesor.setDepartamento(departamento);
        }
        if(p.getProyectosDeGradoDirigidos()!=null)
        {
            List<ProyectoDeGradoJpa> proyectosDeGradoJpa = new ArrayList<ProyectoDeGradoJpa>();
            for(ProyectoDeGrado proyectoDeGrado : p.getProyectosDeGradoDirigidos())
            {
                proyectosDeGradoJpa.add(proyectoMapper.domainToJpa(proyectoDeGrado));
            }
            profesor.setProyectosDeGradoDirigidos(proyectosDeGradoJpa);
        }
        if(p.getProyectosDeGradoCodirigidos()!=null)
        {
            List<ProyectoDeGradoJpa> proyectosDeGradoJpa = new ArrayList<ProyectoDeGradoJpa>();
            for(ProyectoDeGrado proyectoDeGrado : p.getProyectosDeGradoCodirigidos())
            {
                ProyectoDeGradoJpa proyectoDeGradoCodirigidoJpa =new ProyectoDeGradoJpa();
                proyectoDeGradoCodirigidoJpa.setId(proyectoDeGrado.getId());
                proyectosDeGradoJpa.add(proyectoDeGradoCodirigidoJpa);
            }
            profesor.setProyectosDeGradoCodirigidos(proyectosDeGradoJpa);
        }
        if(p.getAnteProyectos()==null)
        {
            List<AnteProyectoJpa> anteProyectosDeGradoJpa = new ArrayList<AnteProyectoJpa>();
            for(AnteProyecto anteProyecto : p.getAnteProyectos())
            {
                AnteProyectoJpa anteProyectoJpa =new AnteProyectoJpa();
                anteProyectoJpa.setId(anteProyecto.getId());
                anteProyectosDeGradoJpa.add(anteProyectoJpa);
            }
            profesor.setAnteProyectos(anteProyectosDeGradoJpa);
        }
        if(p.getAnteProyectosEvaluadosComo1()!=null){
            List<AnteProyectoJpa> anteProyectosComo1Jpa = new ArrayList<AnteProyectoJpa>();
            for(AnteProyecto anteProyecto : p.getAnteProyectosEvaluadosComo1())
            {
                AnteProyectoJpa anteProyectoComo1Jpa =new AnteProyectoJpa();
                anteProyectoComo1Jpa.setId(anteProyecto.getId());
                anteProyectosComo1Jpa.add(anteProyectoComo1Jpa);
            }
            profesor.setAnteProyectosEvaluadosComo1(anteProyectosComo1Jpa);
        }
        if(p.getAnteProyectosEvaluadosComo2()!=null){
            List<AnteProyectoJpa> anteProyectosComo2Jpa = new ArrayList<AnteProyectoJpa>();
            for(AnteProyecto anteProyecto : p.getAnteProyectosEvaluadosComo2())
            {
                AnteProyectoJpa anteProyectoComo1Jpa =new AnteProyectoJpa();
                anteProyectoComo1Jpa.setId(anteProyecto.getId());
                anteProyectosComo2Jpa.add(anteProyectoComo1Jpa);
            }
            profesor.setAnteProyectosEvaluadosComo2(anteProyectosComo2Jpa);
        }
        return profesor;
    }
}
