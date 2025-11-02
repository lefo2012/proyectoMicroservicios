package co.edu.unicauca.users_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Estudiante extends Persona{



    @ManyToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;

    public boolean relacionarPrograma(Programa programa)
    {
        if(this.programa ==null)
        {
            this.programa = programa;
            this.programa.relacionarEstudiante(this);
            return true;
        }
        return false;
    }
}
