package co.edu.unicauca.administracionDocumental_ms.infra.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormatoAJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ProyectoDeGradoJpa proyectoDeGrado;
}
