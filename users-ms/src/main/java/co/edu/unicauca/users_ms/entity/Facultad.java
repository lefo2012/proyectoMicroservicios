package co.edu.unicauca.users_ms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Facultad {

    private int id;

    private String nombre;

    private List<Departamento> departamentos;

    public Facultad()
    {
        departamentos = new ArrayList<Departamento>();
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
