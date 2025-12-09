package co.edu.unicauca.administracionDocumental_ms.infra.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AnteProyectoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @ManyToOne
    private ProyectoDeGradoJpa proyectoDeGrado;

    @ManyToOne
    private ProfesorJpa evaluador1;

    @ManyToOne
    private ProfesorJpa evaluador2;

    public AnteProyectoJpa(String nombre)
    {
        this.nombre = nombre;
    }

    public AnteProyectoJpa()
    {

    }
}
