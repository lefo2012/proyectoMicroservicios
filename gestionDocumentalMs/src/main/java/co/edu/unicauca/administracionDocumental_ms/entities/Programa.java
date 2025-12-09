package co.edu.unicauca.administracionDocumental_ms.entities;

import java.util.ArrayList;
import java.util.List;


public class Programa {
    private int id;
    private String nombre;
    List<Estudiante> estudiantes;
    private Departamento departamento;

    public Programa() {
        estudiantes = new ArrayList<Estudiante>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean relacionarEstudiante(Estudiante estudiante){
        if(!estudiantes.contains(estudiante))
        {
            estudiantes.add(estudiante);
            estudiante.relacionarPrograma(this);
            return true;
        }
        return false;
    }

    public boolean relacionarDepartamento(Departamento departamento){
        if(this.departamento == null)
        {
            this.departamento = departamento;
            this.departamento.relacionarPrograma(this);
            return true;
        }
        return false;
    }
}
