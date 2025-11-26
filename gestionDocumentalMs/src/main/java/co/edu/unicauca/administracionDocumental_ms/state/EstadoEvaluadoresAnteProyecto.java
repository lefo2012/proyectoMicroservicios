package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public class EstadoEvaluadoresAnteProyecto implements  EstadoProyecto{
    private final String nombre = "EVALUADORES_ANTEPROYECTO";
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
