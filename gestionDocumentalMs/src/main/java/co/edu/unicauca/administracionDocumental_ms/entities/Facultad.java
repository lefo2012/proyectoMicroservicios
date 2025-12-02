package co.edu.unicauca.administracionDocumental_ms.entities;


import java.util.ArrayList;
import java.util.List;


public class Facultad {


    private int id;
    private String nombre;
    private List<Departamento> departamentos;

    public Facultad()
    {
        departamentos = new ArrayList<Departamento>();
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean relacionarDepartamento(Departamento departamento){
        if(!this.departamentos.contains(departamento)){
            departamentos.add(departamento);
            departamento.relacionarFacultad(this);
            return true;
        }
        return false;
    }
}
