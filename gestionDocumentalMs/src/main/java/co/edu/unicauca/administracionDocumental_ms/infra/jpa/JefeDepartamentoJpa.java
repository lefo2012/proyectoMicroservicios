package co.edu.unicauca.administracionDocumental_ms.infra.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class JefeDepartamentoJpa extends PersonaJpa{
    @OneToOne
    private DepartamentoJpa departamento;
    @OneToMany(mappedBy = "jefeDepartamento")
    private List<ProyectoDeGradoJpa> proyectosDeGrado;

}
