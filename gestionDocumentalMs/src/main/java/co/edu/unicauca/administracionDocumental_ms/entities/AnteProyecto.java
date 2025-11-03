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
    @OneToOne
    private JefeDepartamento jefeDepartamento;
    @ManyToOne
    private Profesor evaluador1;
    @ManyToOne
    private Profesor evaluador2;
    public AnteProyecto(String nombre, JefeDepartamento jefeDepartamento)
    {
        this.jefeDepartamento=jefeDepartamento;
        this.nombre = nombre;
    }
}
