package co.edu.unicauca.users_ms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profesor extends Persona{


    @ManyToOne
    @JoinColumn(name = "departamento_id")
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
