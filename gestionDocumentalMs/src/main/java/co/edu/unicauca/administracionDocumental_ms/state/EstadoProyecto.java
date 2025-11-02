package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public interface EstadoProyecto {
    void avanzar(ProyectoDeGrado proyectoDeGrado);
    void correciones (ProyectoDeGrado proyectoDeGrado);
    void aprobar (ProyectoDeGrado proyectoDeGrado);
    void rechazar(ProyectoDeGrado proyectoDeGrado);
    String getNombre();
}
