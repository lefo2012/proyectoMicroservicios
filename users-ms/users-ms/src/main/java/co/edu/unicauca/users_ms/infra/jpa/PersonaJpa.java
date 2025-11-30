package co.edu.unicauca.users_ms.infra.jpa;

import co.edu.unicauca.users_ms.entity.Persona;
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

    @Column(name = "correoElectronico",unique = true,nullable = false)
    private String correoElectronico;

    @Column(name="celular",nullable = true)
    private String celular;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;



}
