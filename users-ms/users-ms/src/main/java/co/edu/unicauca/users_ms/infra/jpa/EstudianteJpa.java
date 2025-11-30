package co.edu.unicauca.users_ms.infra.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EstudianteJpa extends PersonaJpa {

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private ProgramaJpa programa;
}
