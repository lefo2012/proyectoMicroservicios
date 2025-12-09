package co.edu.unicauca.administracionDocumental_ms.entities;

import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Coordinador extends Persona{


    private Departamento departamento;
    private List<ProyectoDeGrado> proyectosDeGrado;
    private StateFactory stateFactory;

    public Coordinador()
    {
        proyectosDeGrado = new ArrayList<>();
    }
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public StateFactory getStateFactory() {
        return stateFactory;
    }

    public void setStateFactory(StateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    public List<ProyectoDeGrado> getProyectosDeGrado() {
        return proyectosDeGrado;
    }

    public void setProyectosDeGrado(List<ProyectoDeGrado> proyectosDeGrado) {
        this.proyectosDeGrado = proyectosDeGrado;
    }



    public ProyectoDeGrado aprobarFormatoA(ProyectoDeGrado proyectoDeGrado) {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));
        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            proyectoDeGrado.setFechaRevision(new Date());
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
            proyectoDeGrado.setFechaRevision(new Date());
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
