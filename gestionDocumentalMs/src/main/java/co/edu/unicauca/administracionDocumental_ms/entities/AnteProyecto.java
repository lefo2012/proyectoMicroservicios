package co.edu.unicauca.administracionDocumental_ms.entities;


public class AnteProyecto {

    private long id;
    private String nombre;
    private ProyectoDeGrado proyectoDeGrado;
    private Profesor evaluador1;
    private Profesor evaluador2;

    public AnteProyecto(String nombre)
    {
        this.nombre = nombre;
    }

    public AnteProyecto()
    {
        
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public Profesor getEvaluador2() {return evaluador2;}

    public Profesor getEvaluador1() {return evaluador1;}

    public ProyectoDeGrado getProyectoDeGrado() {return proyectoDeGrado;}

    public void setProyectoDeGrado(ProyectoDeGrado proyectoDeGrado) {this.proyectoDeGrado = proyectoDeGrado;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setEvaluador1(Profesor evaluador1) {
        this.evaluador1 = evaluador1;

        if (evaluador1 != null) {
            evaluador1.getAnteProyectosEvaluadosComo1().add(this);
        }
    }

    public void setEvaluador2(Profesor evaluador2) {
        this.evaluador2 = evaluador2;

        if (evaluador2 != null) {
            evaluador2.getAnteProyectosEvaluadosComo2().add(this);
        }
    }
}
