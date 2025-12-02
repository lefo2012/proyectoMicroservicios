package co.edu.unicauca.administracionDocumental_ms.infra.jpa;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class EstudianteJpa extends PersonaJpa {

    @ManyToOne
    ProgramaJpa programa;
    @Column
    int cantidadIntentosInvestigacion;
    @Column
    int cantidadIntentosPracticaLaboral;

    @ManyToMany(mappedBy = "estudiantes")
    List<ProyectoDeGradoJpa> proyectosDeGrado;

}
