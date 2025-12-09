package co.edu.unicauca.users_ms.infra.jpa;

import co.edu.unicauca.users_ms.entity.Persona;
import co.edu.unicauca.users_ms.entity.Profesor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProfesorJpa extends PersonaJpa {
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private DepartamentoJpa departamento;


}
