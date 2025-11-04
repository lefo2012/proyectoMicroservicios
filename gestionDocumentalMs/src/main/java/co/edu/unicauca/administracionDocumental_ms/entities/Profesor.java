package co.edu.unicauca.administracionDocumental_ms.entities;


import co.edu.unicauca.administracionDocumental_ms.builder.Director;
import co.edu.unicauca.administracionDocumental_ms.builder.InvestigacionBuilder;
import co.edu.unicauca.administracionDocumental_ms.builder.PracticaBuilder;
import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Profesor extends Persona{

    @OneToMany
    List<AnteProyecto> anteProyectos;

    @OneToMany
    List<ProyectoDeGrado> proyectosDeGradoDirigidos;

    @ManyToMany
    List<ProyectoDeGrado> proyectosDeGradoCodirigidos;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    Departamento departamento;

    public boolean relacionarDepartamento(Departamento departamento)
    {
        if(this.departamento==null)
        {
            this.departamento=departamento;
            departamento.relacionarProfesor(this);
            return true;
        }
        return false;
    }

    public Profesor()
    {
        this.anteProyectos=new ArrayList<>();
        this.proyectosDeGradoDirigidos=new ArrayList<>();
    }

    public ProyectoDeGrado iniciarProyectoDeGradoInvestigacion(String titulo, String objetivo, String objetivoEspecifico, String archivoAdjunto, Estudiante estudiante1, Estudiante estudiante2, List<Profesor> codirectores) throws Exception {

        if(!estudiante1.disponible() && !estudiante1.investigacionDisponible())
        {
            throw new RuntimeException("Estudiante: "+estudiante1.getNombre()+" no disponible");
        }
        if(estudiante2!=null){
            if(!estudiante2.disponible() && !estudiante2.investigacionDisponible())
            {
                throw new RuntimeException("Estudiante: "+estudiante2.getNombre()+" no disponible");
            }
        }


        Director director = new Director();
        director.setBuilder(new InvestigacionBuilder());

        director.build(
                titulo,
                objetivo,
                objetivoEspecifico,
                new Date(),
                archivoAdjunto,
                estudiante1,
                estudiante2,
                this.departamento.getCoordinador(),
                this,
                codirectores
        );

        return director.getProyectoDeGrado();
    }
    public ProyectoDeGrado iniciarProyectoDeGradoPracticaLaboral(String titulo, String objetivo, String objetivoEspecifico, String archivoAdjunto, Estudiante estudiante1, Estudiante estudiante2, List<Profesor> codirectores) throws Exception {

        if(!estudiante1.disponible() && !estudiante1.practicaLaboralDisponible())
        {
            throw new RuntimeException("Estudiante: "+estudiante1.getNombre()+" no disponible");
        }

        Director director = new Director();
        director.setBuilder(new PracticaBuilder());

        director.build(
                titulo,
                objetivo,
                objetivoEspecifico,
                new Date(),
                archivoAdjunto,
                estudiante1,
                estudiante2,
                this.departamento.getCoordinador(),
                this,
                codirectores
        );

        return director.getProyectoDeGrado();
    }
    public ProyectoDeGrado subirAnteproyecto(ProyectoDeGrado proyectoDeGrado,AnteProyecto anteProyecto)
    {
        StateFactory stateFactory = StateFactory.getInstance();
        if(proyectoDeGrado.getEstado().equals("APROBADO"))
        {
            proyectoDeGrado.setJefeDepartamento(this.departamento.getJefeDepartamento());
            this.departamento.getJefeDepartamento().addProyectoDeGrado(proyectoDeGrado);
            proyectoDeGrado.addAnteProyecto(anteProyecto);
            proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance("REVISION_ANTEPROYECTO"));
        }
        return proyectoDeGrado;

    }

    public boolean addProyectoDeGradoDirigido(ProyectoDeGrado proyectoDeGrado)
    {
        if(!this.proyectosDeGradoDirigidos.contains(this)){
            this.proyectosDeGradoDirigidos.add(proyectoDeGrado);
            return true;
        }
        return false;
    }
    public boolean addProyectoDeGradoCodirigidos(ProyectoDeGrado proyectoDeGrado)
    {
        if(!this.proyectosDeGradoCodirigidos.contains(this)){
            this.proyectosDeGradoCodirigidos.add(proyectoDeGrado);
            return true;
        }
        return false;

    }

}
