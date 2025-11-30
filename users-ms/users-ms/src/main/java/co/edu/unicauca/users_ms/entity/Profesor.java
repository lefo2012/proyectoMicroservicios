package co.edu.unicauca.users_ms.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profesor extends Persona{

    private Departamento departamento;

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

}
