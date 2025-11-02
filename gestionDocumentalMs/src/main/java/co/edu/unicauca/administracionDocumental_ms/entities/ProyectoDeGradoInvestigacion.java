package co.edu.unicauca.administracionDocumental_ms.entities;

public class ProyectoDeGradoInvestigacion extends ProyectoDeGrado{
    ProyectoDeGrado proyectoDeGrado;
    public String getDescripcion()
    {
        return super.getDescripcion() + "de investigacion";
    }
}
//posible decorador
