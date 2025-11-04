package co.edu.unicauca.administracionDocumental_ms.builder;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.entities.TipoProyecto;
import co.edu.unicauca.administracionDocumental_ms.state.EstadoInicio;

public class PracticaBuilder extends Builder{

    @Override
    public void crearNuevoFormato() {
        this.proyectoDeGrado = new ProyectoDeGrado();
        this.proyectoDeGrado.setTipoProyecto(TipoProyecto.PRACTICA_LABORAL);
        this.proyectoDeGrado.setEstadoProyecto(new EstadoInicio());
    }

    @Override
    public void setEstudiantes(Estudiante estudiante1, Estudiante estudiante2){
            if(estudiante1==null)
                throw new IllegalArgumentException("El formatoA debe tener un estudiante obligatoriamente");
            if(estudiante1.disponible())
                this.proyectoDeGrado.setEstudiante1(estudiante1);
            else
                throw new IllegalArgumentException("Estudiante: "+estudiante1.getNombre()+" no disponible");

            if(estudiante2!=null)
                throw new IllegalArgumentException("El formatoA en la modalidad de practica profesional solo acepta un estudiante");
        }

}
