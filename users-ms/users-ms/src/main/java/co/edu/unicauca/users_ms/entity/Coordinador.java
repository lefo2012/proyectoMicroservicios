package co.edu.unicauca.users_ms.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinador extends Persona{

    private Departamento departamento;

    public boolean relacionarDepartamento(Departamento departamento)
    {
        if(this.departamento==null)
        {
            this.departamento=departamento;
            this.departamento.relacionarCoordinador(this);
        }

        return true;
    }
}
