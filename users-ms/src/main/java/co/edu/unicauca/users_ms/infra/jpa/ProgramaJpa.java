package co.edu.unicauca.users_ms.infra.jpa;

import co.edu.unicauca.users_ms.entity.Departamento;
import co.edu.unicauca.users_ms.entity.Estudiante;
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

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "programa")
    List<EstudianteJpa> estudiantes;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private DepartamentoJpa departamento;
    public ProgramaJpa()
    {
        estudiantes = new ArrayList<>();
    }
}
