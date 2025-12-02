package co.edu.unicauca.administracionDocumental_ms.infra.jpa;


import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CoordinadorJpa extends PersonaJpa{
    @OneToOne
    private DepartamentoJpa departamento;

    @OneToMany(mappedBy = "coordinador")
    private List<ProyectoDeGradoJpa> proyectosDeGrado;

    @Transient
    private StateFactory stateFactory;


    public CoordinadorJpa()
    {
        proyectosDeGrado = new ArrayList<>();
    }


}
