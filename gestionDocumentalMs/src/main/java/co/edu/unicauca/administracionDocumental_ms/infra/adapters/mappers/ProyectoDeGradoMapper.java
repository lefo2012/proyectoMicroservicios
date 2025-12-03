package co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers;


import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProyectoDeGradoMapper {

    public ProyectoDeGrado jpaToDomain(ProyectoDeGradoJpa proyectoDeGradoJpa){
        if (proyectoDeGradoJpa == null) return null;

        ProyectoDeGrado proyectoDeGrado = new ProyectoDeGrado();
        proyectoDeGrado.setId(proyectoDeGradoJpa.getId());
        proyectoDeGrado.setTitulo(proyectoDeGradoJpa.getTitulo());
        proyectoDeGrado.setObjetivo(proyectoDeGradoJpa.getObjetivo());
        proyectoDeGrado.setObjetivoEspecifico(proyectoDeGradoJpa.getObjetivoEspecifico());
        proyectoDeGrado.setFechaSubida(proyectoDeGradoJpa.getFechaSubida());
        proyectoDeGrado.setFechaRevision(proyectoDeGradoJpa.getFechaRevision());
        proyectoDeGrado.setArchivoAdjunto(proyectoDeGradoJpa.getArchivoAdjunto());
        proyectoDeGrado.setEstado(proyectoDeGradoJpa.getEstado());
        proyectoDeGrado.setTipoProyecto(proyectoDeGradoJpa.getTipoProyecto());
        proyectoDeGrado.setDescripcion(proyectoDeGradoJpa.getDescripcion());

        if(proyectoDeGradoJpa.getJefeDepartamento() != null){
            JefeDepartamento jefeDepartamento = new JefeDepartamento();
            jefeDepartamento.setId(proyectoDeGradoJpa.getJefeDepartamento().getId());
            jefeDepartamento.setNombre(proyectoDeGradoJpa.getJefeDepartamento().getNombre());
            jefeDepartamento.setApellido(proyectoDeGradoJpa.getJefeDepartamento().getApellido());
            proyectoDeGrado.setJefeDepartamento(jefeDepartamento);
        }

        if(proyectoDeGradoJpa.getEstudiante1() != null){
            Estudiante estudiante1 = new Estudiante();
            estudiante1.setId(proyectoDeGradoJpa.getEstudiante1().getId());
            estudiante1.setNombre(proyectoDeGradoJpa.getEstudiante1().getNombre());
            estudiante1.setApellido(proyectoDeGradoJpa.getEstudiante1().getApellido());
            proyectoDeGrado.setEstudiante1(estudiante1);
        }

        if(proyectoDeGradoJpa.getEstudiante2() != null){
            Estudiante estudiante2 = new Estudiante();
            estudiante2.setId(proyectoDeGradoJpa.getEstudiante2().getId());
            estudiante2.setNombre(proyectoDeGradoJpa.getEstudiante2().getNombre());
            estudiante2.setApellido(proyectoDeGradoJpa.getEstudiante2().getApellido());
            proyectoDeGrado.setEstudiante2(estudiante2);
        }
        if(proyectoDeGradoJpa.getEstudiantes() != null)
        {
            List<Estudiante> estudiantes = new  ArrayList<>();
            for (EstudianteJpa e :  proyectoDeGradoJpa.getEstudiantes())
            {
                Estudiante estudianteJpa = new Estudiante();
                estudianteJpa.setId(e.getId());
                estudiantes.add(estudianteJpa);
            }
            proyectoDeGrado.setEstudiantes(estudiantes);
        }

        if(proyectoDeGradoJpa.getCoordinador() != null){
            Coordinador coordinador = new Coordinador();
            coordinador.setId(proyectoDeGradoJpa.getCoordinador().getId());
            coordinador.setNombre(proyectoDeGradoJpa.getCoordinador().getNombre());
            coordinador.setApellido(proyectoDeGradoJpa.getCoordinador().getApellido());
            proyectoDeGrado.setCoordinador(coordinador);
        }

        if(proyectoDeGradoJpa.getDirector() != null){
            Profesor director = new Profesor();
            director.setCorreoElectronico(proyectoDeGradoJpa.getDirector().getCorreoElectronico());
            director.setId(proyectoDeGradoJpa.getDirector().getId());
            director.setNombre(proyectoDeGradoJpa.getDirector().getNombre());
            director.setApellido(proyectoDeGradoJpa.getDirector().getApellido());
            proyectoDeGrado.setDirector(director);
        }

        if(proyectoDeGradoJpa.getCodirectores() != null){
            List <Profesor> codirectores = new ArrayList<>();
            for(ProfesorJpa codirec:proyectoDeGradoJpa.getCodirectores()){
                Profesor codirector = new Profesor();
                codirector.setId(codirec.getId());
                codirector.setNombre(codirec.getNombre());
                codirector.setApellido(codirec.getApellido());
                codirectores.add(codirector);
            }
            proyectoDeGrado.setCodirectores(codirectores);
        }

        if(proyectoDeGradoJpa.getFormatosA() != null){
            List <FormatoA> formatosA = new ArrayList<>();
            for(FormatoAJpa fmtA:proyectoDeGradoJpa.getFormatosA()){
                FormatoA formatoA = new FormatoA();
                formatoA.setId(fmtA.getId());
                formatosA.add(formatoA);
            }
            proyectoDeGrado.setFormatosA(formatosA);
        }

        if(proyectoDeGradoJpa.getAnteProyectos() != null){
            List <AnteProyecto> anteProyectos = new ArrayList<>();
            for(AnteProyectoJpa anteProyect:proyectoDeGradoJpa.getAnteProyectos()){
                AnteProyecto anteProyecto = new AnteProyecto();
                anteProyecto.setId(anteProyect.getId());
                anteProyectos.add(anteProyecto);
            }
            proyectoDeGrado.setAnteProyectos(anteProyectos);
        }

        return proyectoDeGrado;
    }

    public ProyectoDeGradoJpa domainToJpa(ProyectoDeGrado proyectoDeGrado){
        if (proyectoDeGrado == null) return null;

        ProyectoDeGradoJpa proyectoDeGradojpa = new ProyectoDeGradoJpa();
        proyectoDeGradojpa.setId(proyectoDeGrado.getId());
        proyectoDeGradojpa.setTitulo(proyectoDeGrado.getTitulo());
        proyectoDeGradojpa.setObjetivo(proyectoDeGrado.getObjetivo());
        proyectoDeGradojpa.setObjetivoEspecifico(proyectoDeGrado.getObjetivoEspecifico());
        proyectoDeGradojpa.setFechaSubida(proyectoDeGrado.getFechaSubida());
        proyectoDeGradojpa.setFechaRevision(proyectoDeGrado.getFechaRevision());
        proyectoDeGradojpa.setArchivoAdjunto(proyectoDeGrado.getArchivoAdjunto());
        proyectoDeGradojpa.setEstado(proyectoDeGrado.getEstado());
        proyectoDeGradojpa.setTipoProyecto(proyectoDeGrado.getTipoProyecto());
        proyectoDeGradojpa.setDescripcion(proyectoDeGrado.getDescripcion());

        if(proyectoDeGrado.getJefeDepartamento() != null){
            JefeDepartamentoJpa jefeDepartamento = new JefeDepartamentoJpa();
            jefeDepartamento.setId(proyectoDeGrado.getJefeDepartamento().getId());
            jefeDepartamento.setNombre(proyectoDeGrado.getJefeDepartamento().getNombre());
            jefeDepartamento.setApellido(proyectoDeGrado.getJefeDepartamento().getApellido());
            proyectoDeGradojpa.setJefeDepartamento(jefeDepartamento);
        }

        if(proyectoDeGrado.getEstudiante1() != null){
            EstudianteJpa estudiante1 = new EstudianteJpa();
            estudiante1.setId(proyectoDeGrado.getEstudiante1().getId());
            proyectoDeGradojpa.setEstudiante1(estudiante1);
        }

        if(proyectoDeGrado.getEstudiante2() != null){
            EstudianteJpa estudiante2 = new EstudianteJpa();
            estudiante2.setId(proyectoDeGrado.getEstudiante2().getId());
            proyectoDeGradojpa.setEstudiante2(estudiante2);
        }
        if(proyectoDeGrado.getEstudiantes() != null)
        {
            List<EstudianteJpa> estudiantes = new  ArrayList<>();
            for (Estudiante e :  proyectoDeGrado.getEstudiantes())
            {
                EstudianteJpa estudianteJpa = new EstudianteJpa();
                estudianteJpa.setId(e.getId());
                estudiantes.add(estudianteJpa);
            }
            proyectoDeGradojpa.setEstudiantes(estudiantes);
        }

        if(proyectoDeGrado.getCoordinador() != null){
            CoordinadorJpa coordinador = new CoordinadorJpa();
            coordinador.setId(proyectoDeGrado.getCoordinador().getId());
            proyectoDeGradojpa.setCoordinador(coordinador);
        }

        if(proyectoDeGrado.getDirector() != null){
            ProfesorJpa director = new ProfesorJpa();
            director.setCorreoElectronico(proyectoDeGrado.getDirector().getCorreoElectronico());
            director.setId(proyectoDeGrado.getDirector().getId());
            proyectoDeGradojpa.setDirector(director);
        }

        if(proyectoDeGrado.getCodirectores() != null){
            List <ProfesorJpa> codirectores = new ArrayList<>();
            for(Profesor codirec:proyectoDeGrado.getCodirectores()){
                ProfesorJpa codirector = new ProfesorJpa();
                codirector.setId(codirec.getId());
                codirectores.add(codirector);
            }
            proyectoDeGradojpa.setCodirectores(codirectores);
        }

        if(proyectoDeGrado.getFormatosA() != null){
            List <FormatoAJpa> formatosA = new ArrayList<>();
            for(FormatoA fmtA:proyectoDeGrado.getFormatosA()){
                FormatoAJpa formatoAJpa = new FormatoAJpa();
                formatoAJpa.setId(fmtA.getId());
                formatosA.add(formatoAJpa);
            }
            proyectoDeGradojpa.setFormatosA(formatosA);
        }

        if(proyectoDeGrado.getAnteProyectos() != null){
            List <AnteProyectoJpa> anteProyectos = new ArrayList<>();
            for(AnteProyecto anteProyecto:proyectoDeGrado.getAnteProyectos()){
                AnteProyectoJpa anteProyectoJpa = new AnteProyectoJpa();
                anteProyectoJpa.setId(anteProyecto.getId());
                anteProyectos.add(anteProyectoJpa);
            }
            proyectoDeGradojpa.setAnteProyectos(anteProyectos);
        }

        return proyectoDeGradojpa;
    }
}
