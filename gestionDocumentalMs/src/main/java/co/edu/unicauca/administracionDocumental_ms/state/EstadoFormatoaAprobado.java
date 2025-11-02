package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;


public class EstadoFormatoaAprobado implements EstadoProyecto{

    @Override
    public void avanzar(ProyectoDeGrado proyectoDeGrado) {
        proyectoDeGrado.setEstadoProyecto(new EstadoRevisionAnteProyecto());
    }

    @Override
    public void correciones(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("El Formato A ya fue Aprobado, no hay correciones");
    }

    @Override
    public void aprobar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("El Formato A ya fue Aprobado");
    }

    @Override
    public void rechazar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("El Formato A ya fue Aprobado, no se puede Rechazar");
    }

    @Override
    public String getNombre() {
        return "FORMATOA_APROBADO";
    }
}
