package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Coordinador extends Persona{

    @OneToOne
    private Departamento departamento;
    @OneToMany
    private List<ProyectoDeGrado> proyectosDeGrado;
    public Coordinador()
    {
        proyectosDeGrado = new ArrayList<>();
    }

    public boolean aprobarFormatoA(ProyectoDeGrado proyectoDeGrado)
    {

        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION_FORMATOA"))
        {
            proyectoDeGrado.aprobar();
            return true;
        }
        return false;
    }
    public boolean rechazarFormatoA(ProyectoDeGrado proyectoDeGrado)
    {

        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION_FORMATOA"))
        {
            proyectoDeGrado.rechazar();
            return true;
        }
        return false;
    }
    public boolean mandarACorregir(ProyectoDeGrado proyectoDeGrado)
    {
        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION_FORMATOA"))
        {
            proyectoDeGrado.correccion();
            return true;
        }
        return false;
    }

    public boolean setDepartamento(Departamento departamento)
    {
        if(this.departamento==null)
        {
            this.departamento=departamento;
            this.departamento.relacionarCoordinador(this);
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
}
