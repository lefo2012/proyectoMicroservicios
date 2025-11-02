package co.edu.unicauca.administracionDocumental_ms.builder;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.TipoProyecto;
import co.edu.unicauca.administracionDocumental_ms.state.EstadoInicio;

public class PracticaBuilder extends Builder{

    @Override
    public void crearNuevoFormato() {
        this.proyectoDeGrado.setTipoProyecto(TipoProyecto.INVESTIGACION);
        this.proyectoDeGrado.setEstadoProyecto(new EstadoInicio());
    }

    @Override
    public void setEstudiantes(Estudiante est1, Estudiante est2){
            if(est1==null)
                throw new IllegalArgumentException("El formatoA debe tener un estudiante obligatoriamente");
            this.proyectoDeGrado.setEstudiante1(est1);
            if(est2!=null)
                throw new IllegalArgumentException("El formatoA en la modalidad de practica profesional solo acepta un estudiante");
        }

}
