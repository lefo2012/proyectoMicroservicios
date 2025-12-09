package co.edu.unicauca.users_ms.infra.jpa;

import co.edu.unicauca.users_ms.entity.Departamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FacultadJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "facultad")
    private List<DepartamentoJpa> departamentos;
}
