package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public interface EstadoProyecto {

    public abstract void avanzar(ProyectoDeGrado proyectoDeGrado);
    public abstract void correciones (ProyectoDeGrado proyectoDeGrado);
    public abstract void aprobar (ProyectoDeGrado proyectoDeGrado);
    public abstract void rechazar(ProyectoDeGrado proyectoDeGrado);
    public abstract String getNombre();
}
