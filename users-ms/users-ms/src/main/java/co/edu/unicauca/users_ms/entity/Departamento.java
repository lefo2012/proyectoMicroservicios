package co.edu.unicauca.users_ms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Departamento {

    private int id;

    private String nombre;

    private Coordinador coordinador;

    private JefeDepartamento jefeDepartamento;

    private List<Profesor> profesores;

    private Facultad facultad;

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
