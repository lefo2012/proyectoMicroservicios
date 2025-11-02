package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;


@Entity
public class JefeDepartamento extends Persona{

    @OneToOne
    private Departamento departamento;
    @OneToMany
    private List<AnteProyecto> anteProyectos;
    public boolean relacionarDepartamento(Departamento departamento)
    {
        if(this.departamento==null)
        {
            this.departamento=departamento;
            this.departamento.relacionarJefeDepartamento(this);
            return true;
        }
        return false;
    }

}
