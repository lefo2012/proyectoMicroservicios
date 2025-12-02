package co.edu.unicauca.administracionDocumental_ms.infra.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class PersonaJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    String nombre;

    @Column
    String apellido;

    @Column
    String celular;

    @Column(unique = true)
    String correoElectronico;
}
