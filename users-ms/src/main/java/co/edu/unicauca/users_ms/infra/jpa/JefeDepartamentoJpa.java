package co.edu.unicauca.users_ms.infra.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JefeDepartamentoJpa extends PersonaJpa {

    @OneToOne
    private DepartamentoJpa departamento;
}
