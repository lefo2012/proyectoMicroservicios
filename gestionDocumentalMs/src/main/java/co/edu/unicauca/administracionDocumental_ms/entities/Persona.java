package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Persona {

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
