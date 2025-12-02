package co.edu.unicauca.administracionDocumental_ms.entities;

import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;


import java.util.ArrayList;
import java.util.List;


public class JefeDepartamento extends Persona{

    private Departamento departamento;
    private List<ProyectoDeGrado> proyectosDeGrado;

    public JefeDepartamento(){
        proyectosDeGrado = new ArrayList<>();
    }



    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<ProyectoDeGrado> getProyectosDeGrado() {
        return proyectosDeGrado;
    }

    public void setProyectosDeGrado(List<ProyectoDeGrado> proyectosDeGrado) {
        this.proyectosDeGrado = proyectosDeGrado;
    }

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

    public ProyectoDeGrado asignagEvaluadores(ProyectoDeGrado proyectoDeGrado)
    {
        StateFactory stateFactory = StateFactory.getInstance();
        if(proyectoDeGrado.getEstado().equals("REVISION_ANTEPROYECTO"))
        {
            proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance("EVALUADORES_ANTEPROYECTO"));
        }
        return proyectoDeGrado;
    }
}
