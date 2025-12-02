package co.edu.unicauca.administracionDocumental_ms.entities;



public class FormatoA extends File{

    long id;
    ProyectoDeGrado proyectoDeGrado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProyectoDeGrado getProyectoDeGrado() {
        return proyectoDeGrado;
    }

    public void setProyectoDeGrado(ProyectoDeGrado proyectoDeGrado) {
        this.proyectoDeGrado = proyectoDeGrado;
    }
}
