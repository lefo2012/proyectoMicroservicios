package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public class EstadoFormatoaCorrecion implements EstadoProyecto{
    @Override
    public void avanzar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("Formato A corregido, se envia de nuevo a revision");
        proyectoDeGrado.setEstadoProyecto(new EstadoInicio());
    }

    @Override
    public void correciones(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("Formato A ya en estado de correcion");
    }

    @Override
    public void aprobar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("No se puede aprobar un Formato A en estado de correcion sin volver a revision");
    }

    @Override
    public void rechazar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("No se puede rechazar un Formato A en estado de correcion sin volver a revision");
    }

    @Override
    public String getNombre() {
        return "CORRECION";
    }
}
