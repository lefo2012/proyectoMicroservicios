package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public class EstadoRevisionAnteProyecto implements EstadoProyecto{
    @Override
    public void avanzar(ProyectoDeGrado proyectoDeGrado) {
        proyectoDeGrado.setEstadoProyecto(new EstadoAnteProyectoAprobado());
    }

    @Override
    public void correciones(ProyectoDeGrado proyectoDeGrado) {

    }

    @Override
    public void aprobar(ProyectoDeGrado proyectoDeGrado) {

        avanzar(proyectoDeGrado);

    }

    @Override
    public void rechazar(ProyectoDeGrado proyectoDeGrado) {

    }

    @Override
    public String getNombre() {
        return "REVISION_ANTEPROYECTO";
    }
}
