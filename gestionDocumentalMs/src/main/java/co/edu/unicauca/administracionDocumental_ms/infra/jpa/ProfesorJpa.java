package co.edu.unicauca.administracionDocumental_ms.infra.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ProfesorJpa extends PersonaJpa{

    @OneToMany
    List<AnteProyectoJpa> anteProyectos;

    @OneToMany(mappedBy = "evaluador1")
    private List<AnteProyectoJpa> anteProyectosEvaluadosComo1 = new ArrayList<>();

    @OneToMany(mappedBy = "evaluador2")
    private List<AnteProyectoJpa> anteProyectosEvaluadosComo2 = new ArrayList<>();

    @OneToMany(mappedBy = "director")
    List<ProyectoDeGradoJpa> proyectosDeGradoDirigidos;

    @ManyToMany
    List<ProyectoDeGradoJpa> proyectosDeGradoCodirigidos;

    @ManyToOne
    DepartamentoJpa departamento;

    public ProfesorJpa()
    {
        this.anteProyectos=new ArrayList<>();
        this.proyectosDeGradoDirigidos=new ArrayList<>();
    }

}
