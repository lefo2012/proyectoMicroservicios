package co.edu.unicauca.users_ms.infra.jpa;

import co.edu.unicauca.users_ms.entity.Facultad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class DepartamentoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToOne
    private CoordinadorJpa coordinador;

    @OneToOne
    private JefeDepartamentoJpa jefeDepartamento;

    @OneToMany(mappedBy = "departamento")
    private List<ProfesorJpa> profesores;

    @ManyToOne
    @JoinColumn (name = "facultad_id")
    private FacultadJpa facultad;

    @OneToMany(mappedBy = "departamento")
    private List<ProgramaJpa> programas;

    public DepartamentoJpa()
    {
        profesores = new ArrayList<>();
        programas = new ArrayList<>();

    }

}
