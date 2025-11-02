package co.edu.unicauca.entities;

public class Profesor extends Persona{
    private Departamento departamento;
    public Profesor( Departamento departamento, String tipo, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(tipo, nombre, apellido, celular, correoElectronico, contrasenia);
        this.departamento = departamento;
    }
}
