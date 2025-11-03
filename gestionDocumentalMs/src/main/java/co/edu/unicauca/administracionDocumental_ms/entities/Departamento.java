package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToOne
    private Coordinador coordinador;

    @OneToOne
    private JefeDepartamento jefeDepartamento;

    @OneToMany(mappedBy = "departamento")
    private List<Profesor> profesores;

    @ManyToOne
    @JoinColumn (name = "facultad_id")
    private Facultad facultad;

    @OneToMany(mappedBy = "departamento")
    private List<Programa> programas;

    public Departamento(){
        profesores=new ArrayList<>();
        programas=new ArrayList<>();
    }


    public boolean relacionarProfesor(Profesor profesor) {
        if(!profesores.contains(profesor))
        {
            profesores.add(profesor);
            profesor.relacionarDepartamento(this);
            return true;
        }
        return false;
    }

    public boolean relacionarCoordinador(Coordinador coordinador)
    {
        if(this.coordinador==null)
        {
            this.coordinador=coordinador;
            this.coordinador.relacionarDepartamento(this);
            return true;
        }

        return false;
    }
    public boolean relacionarJefeDepartamento(JefeDepartamento jefeDepartamento)
    {
        if(this.jefeDepartamento==null)
        {
            this.jefeDepartamento=jefeDepartamento;
            this.jefeDepartamento.relacionarDepartamento(this);
            return  true;
        }
        return false;

    }
    public boolean relacionarPrograma(Programa programa)
    {
        if(!programas.contains(programa))
        {
            programas.add(programa);
            programa.relacionarDepartamento(this);
            return true;
        }
        return false;
    }

    public boolean relacionarFacultad(Facultad facultad){
        if(this.facultad==null)
        {
            this.facultad=facultad;
            this.facultad.relacionarDepartamento(this);
            return true;
        }
        return false;
    }

}
