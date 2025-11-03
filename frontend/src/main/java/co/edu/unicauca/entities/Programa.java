package co.edu.unicauca.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Programa {
    private String nombre;
    private int id;
    public Programa(String nombre, int id)
    {
        this.nombre = nombre;
        this.id = id;
    }
}