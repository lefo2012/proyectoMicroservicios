package co.edu.unicauca.service;

import co.edu.unicauca.entities.Departamento;


import java.util.ArrayList;
import java.util.List;

public class DepartamentoService {
    public static List<Departamento> obtenerTodos()
    {
        List<Departamento> departamentos = new ArrayList<>();
        departamentos.add(new Departamento("Departamento de sistemas",1));
        return departamentos;
    }
}
