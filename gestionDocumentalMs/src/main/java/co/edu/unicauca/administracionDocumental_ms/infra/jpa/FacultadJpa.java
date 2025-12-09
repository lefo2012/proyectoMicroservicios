package co.edu.unicauca.administracionDocumental_ms.infra.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class FacultadJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @OneToMany
    private List<DepartamentoJpa> departamentos;

    public FacultadJpa()
    {
        departamentos = new ArrayList<>();
    }
}
