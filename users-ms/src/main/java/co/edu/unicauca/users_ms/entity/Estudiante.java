package co.edu.unicauca.users_ms.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Estudiante extends Persona{

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
