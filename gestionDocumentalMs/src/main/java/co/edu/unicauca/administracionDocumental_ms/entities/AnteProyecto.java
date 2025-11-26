package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AnteProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @ManyToOne
    private ProyectoDeGrado proyectoDeGrado;

    @ManyToOne
    private Profesor evaluador1;

    @ManyToOne
    private Profesor evaluador2;

    public AnteProyecto(String nombre)
    {
        this.nombre = nombre;
    }

    public AnteProyecto()
    {
        
    }

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
