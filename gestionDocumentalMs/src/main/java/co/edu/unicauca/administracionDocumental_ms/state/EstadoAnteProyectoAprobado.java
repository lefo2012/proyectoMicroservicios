package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public class EstadoAnteProyectoAprobado implements EstadoProyecto{
    private final String nombre = "ANTEPROYECTO_APROBADO";
    @Override
    public void avanzar(ProyectoDeGrado proyectoDeGrado) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void correciones(ProyectoDeGrado proyectoDeGrado) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void aprobar(ProyectoDeGrado proyectoDeGrado) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void rechazar(ProyectoDeGrado proyectoDeGrado) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
