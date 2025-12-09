package co.edu.unicauca.users_ms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Programa {

    private int id;

    private String nombre;

    List<Estudiante> estudiantes;

    private Departamento departamento;

    public Programa() {
        estudiantes = new ArrayList<Estudiante>();
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
