package co.edu.unicauca.service;

import co.edu.unicauca.entities.Programa;

import java.util.ArrayList;
import java.util.List;

public class ProgramaService {
    public static List<Programa> obtenerTodos()
    {
        List<Programa> programas = new ArrayList<>();
        programas.add(new Programa("Ingenieria de sistemas",1));
        return programas;
    }
}
