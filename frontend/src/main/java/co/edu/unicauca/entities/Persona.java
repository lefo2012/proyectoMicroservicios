package co.edu.unicauca.entities;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String tipo;
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private List<String> cargos = new ArrayList<>();
    private String nombreDepartamento;
    private int idDepartamento;
    private String nombreProgama;
    private int idPrograma;
    private String contrasenia;

    public Persona(String tipo,String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        this.tipo=tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }
}
