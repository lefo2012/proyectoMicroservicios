package co.edu.unicauca.administracionDocumental_ms.factory;

import co.edu.unicauca.administracionDocumental_ms.state.*;


public class StateFactory {
    private static StateFactory instance;
    private StateFactory()
    {

    }
    public static StateFactory getInstance()
    {
        if(instance==null)
        {
            instance=new StateFactory();
            return instance;
        }

        return instance;
    }

    public EstadoProyecto getInstance(String nombre)
    {
        if(nombre.equals("REVISION_ANTEPROYECTO"))
        {

            return new EstadoRevisionAnteProyecto();

        } else if (nombre.equals("REVISION")) {

            return new EstadoInicio();

        }else if (nombre.equals("RECHAZADO"))
        {
            return new EstadoFormatoaRechazado();

        }else if (nombre.equals("ANTEPROYECTO_APROBADO")) {

            return new EstadoAnteProyectoAprobado();

        } else if (nombre.equals("APROBADO")) {

            return new EstadoFormatoaAprobado();

        }
        else if (nombre.equals("CORRECION")) {

            return new EstadoFormatoaCorrecion();

        }

        return null;
    }
}
