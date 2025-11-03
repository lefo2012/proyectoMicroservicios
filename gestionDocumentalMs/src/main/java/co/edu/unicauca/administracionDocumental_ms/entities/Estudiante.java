package co.edu.unicauca.administracionDocumental_ms.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Estudiante extends Persona{

    @ManyToOne
    Programa programa;
    @Column
    int cantidadIntentosInvestigacion;
    @Column
    int cantidadIntentosPracticaLaboral;

    @ManyToMany
    List<ProyectoDeGrado> proyectosDeGrado;

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

    public boolean disponible() {
        if (proyectosDeGrado != null && !proyectosDeGrado.isEmpty())
        {
            return false;
        }
        return true;
    }

    public boolean addProyectoDeGrado(ProyectoDeGrado proyectoDeGrado)
    {
        if(!this.proyectosDeGrado.contains(proyectoDeGrado)){
            this.proyectosDeGrado.add(proyectoDeGrado);
            return true;
        }
        return false;
    }
    public void aumentarCantidadIntentosInvestigacion()
    {
        cantidadIntentosInvestigacion++;
    }
    public void aumentarCantidadIntentosPracticaLaboral()
    {
        cantidadIntentosPracticaLaboral++;
    }

    public boolean investigacionDisponible() {
        return this.cantidadIntentosInvestigacion < 3;
    }
    public boolean practicaLaboralDisponible()
    {
        return this.cantidadIntentosPracticaLaboral < 3;
    }
}
