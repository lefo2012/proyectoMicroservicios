package co.edu.unicauca.administracionDocumental_ms.entities;

import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import co.edu.unicauca.administracionDocumental_ms.service.NotificationClient;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class Coordinador extends Persona{

    @OneToOne
    private Departamento departamento;

    @OneToMany
    private List<ProyectoDeGrado> proyectosDeGrado;

    @Transient
    private StateFactory stateFactory;


    public Coordinador()
    {
        proyectosDeGrado = new ArrayList<>();
    }

    public ProyectoDeGrado aprobarFormatoA(ProyectoDeGrado proyectoDeGrado) {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));
        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            proyectoDeGrado.aprobar();
            return proyectoDeGrado;
        }
        return proyectoDeGrado;
    }
    public ProyectoDeGrado rechazarFormatoA(ProyectoDeGrado proyectoDeGrado)
    {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));
        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            proyectoDeGrado.rechazar();
            return proyectoDeGrado;
        }
        return proyectoDeGrado;
    }
    public ProyectoDeGrado mandarACorregir(ProyectoDeGrado proyectoDeGrado)
    {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));

        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            proyectoDeGrado.correccion();
            return proyectoDeGrado;
        }
        return proyectoDeGrado;
    }

    public boolean relacionarDepartamento(Departamento departamento)
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
