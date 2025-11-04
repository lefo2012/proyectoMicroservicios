package co.edu.unicauca.administracionDocumental_ms.entities;

import co.edu.unicauca.administracionDocumental_ms.service.ProyectoService;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class JefeDepartamento extends Persona{

    @OneToOne
    private Departamento departamento;
    @OneToMany
    private List<ProyectoDeGrado> proyectosDeGrado;

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
    public void addProyectoDeGrado(ProyectoDeGrado proyectoDeGrado)
    {
        proyectosDeGrado.add(proyectoDeGrado);
    }

}
