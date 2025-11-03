package co.edu.unicauca.entities;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Persona {
    private long id;
    private String rol;
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String nombreDepartamento;
    private int idDepartamento;
    private String nombreProgama;
    private int idPrograma;

    public Persona(String nombre, String apellido, String celular, String correoElectronico) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correoElectronico = correoElectronico;

    }
    public Persona()
    {

    }
}
