package co.edu.unicauca.administracionDocumental_ms.state;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.entities.TipoProyecto;

public class EstadoInicio implements EstadoProyecto{
    private final String nombre = "REVISION";
    @Override
    public void avanzar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("Formato A Aprobado");
        proyectoDeGrado.setEstadoProyecto(new EstadoFormatoaAprobado());
    }
    @Override
    public void correciones(ProyectoDeGrado proyectoDeGrado){

        proyectoDeGrado.aumentarIntentosEstudiantes();

        if(proyectoDeGrado.disponibilidadEstudiantes()){
            System.out.println("Se supero el numero de correciones. Formato A Rechazado");
            proyectoDeGrado.setEstadoProyecto(new EstadoFormatoaRechazado());
        }
        else {
            System.out.println("El Formato A requiere correciones. Intento: ");
            proyectoDeGrado.setEstadoProyecto(new EstadoFormatoaCorrecion());
        }
    }
    @Override
    public void aprobar(ProyectoDeGrado proyectoDeGrado) {
        avanzar(proyectoDeGrado);
    }

    @Override
    public void rechazar(ProyectoDeGrado proyectoDeGrado) {
        System.out.println("El Formato A fue Rechazado");
        proyectoDeGrado.setEstadoProyecto(new EstadoFormatoaRechazado());
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
