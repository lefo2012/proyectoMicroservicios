package co.edu.unicauca.administracionDocumental_ms.entities;


import java.util.ArrayList;
import java.util.List;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programas) {
        this.programas = programas;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public JefeDepartamento getJefeDepartamento() {
        return jefeDepartamento;
    }

    public void setJefeDepartamento(JefeDepartamento jefeDepartamento) {
        this.jefeDepartamento = jefeDepartamento;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
