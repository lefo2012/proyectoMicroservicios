package co.edu.unicauca.users_ms.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "correoElectronico",unique = true,nullable = false)
    private String correoElectronico;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name="celular",nullable = true)
    private String celular;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

}
