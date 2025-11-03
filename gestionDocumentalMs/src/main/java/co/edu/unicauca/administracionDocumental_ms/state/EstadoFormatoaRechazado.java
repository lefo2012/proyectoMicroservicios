package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

public class EstadoFormatoaRechazado implements EstadoProyecto{
    private final String nombre = "RECHAZADO";
    @Override
    public void avanzar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("No se puede avanzar, el Formato A fue Rechazado");
    }

    @Override
    public void correciones(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("No se puede corregir, el Formato A fue Rechazado");
    }

    @Override
    public void aprobar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("No se aprobar un Formato A que fue Rechazado");
    }

    @Override
    public void rechazar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("El Formato A ya esa rechazado");
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
