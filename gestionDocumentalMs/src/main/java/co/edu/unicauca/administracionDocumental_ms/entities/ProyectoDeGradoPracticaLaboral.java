package co.edu.unicauca.administracionDocumental_ms.entities;

public class ProyectoDeGradoPracticaLaboral extends ProyectoDeGrado{
    ProyectoDeGrado proyectoDeGrado;
    public String getDescripcion()
    {
        return super.getDescripcion() + " de practica laboral necesita un formato A y una carta laboral adjunta en ambos documentos";
    }
}
//posible decorador