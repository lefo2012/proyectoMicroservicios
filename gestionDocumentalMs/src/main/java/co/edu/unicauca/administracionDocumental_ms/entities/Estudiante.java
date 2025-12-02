package co.edu.unicauca.administracionDocumental_ms.entities;


import java.util.ArrayList;
import java.util.List;


public class Estudiante extends Persona{

    Programa programa;
    int cantidadIntentosInvestigacion;
    int cantidadIntentosPracticaLaboral;

    List<ProyectoDeGrado> proyectosDeGrado;

    public Estudiante(){
        proyectosDeGrado = new ArrayList<>();
    }
    public List<ProyectoDeGrado> getProyectosDeGrado() {
        return proyectosDeGrado;
    }

    public void setProyectosDeGrado(List<ProyectoDeGrado> proyectosDeGrado) {
        this.proyectosDeGrado = proyectosDeGrado;
    }

    public int getCantidadIntentosPracticaLaboral() {
        return cantidadIntentosPracticaLaboral;
    }

    public void setCantidadIntentosPracticaLaboral(int cantidadIntentosPracticaLaboral) {
        this.cantidadIntentosPracticaLaboral = cantidadIntentosPracticaLaboral;
    }

    public int getCantidadIntentosInvestigacion() {
        return cantidadIntentosInvestigacion;
    }

    public void setCantidadIntentosInvestigacion(int cantidadIntentosInvestigacion) {
        this.cantidadIntentosInvestigacion = cantidadIntentosInvestigacion;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

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
