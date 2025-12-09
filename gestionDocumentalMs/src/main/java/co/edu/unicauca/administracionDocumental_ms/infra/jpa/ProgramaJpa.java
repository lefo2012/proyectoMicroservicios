package co.edu.unicauca.administracionDocumental_ms.infra.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ProgramaJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @OneToMany
    List<EstudianteJpa> estudiantes;

    @ManyToOne
    private DepartamentoJpa departamento;

    public ProgramaJpa() {
        estudiantes = new ArrayList<EstudianteJpa>();
    }

}
