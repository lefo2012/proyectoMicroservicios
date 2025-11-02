package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @OneToMany
    private List<Departamento> departamentos;

    public Facultad()
    {
        departamentos = new ArrayList<Departamento>();
    }

    public boolean relacionarDepartamento(Departamento departamento){
        if(!this.departamentos.contains(departamento)){
            departamentos.add(departamento);
            departamento.relacionarFacultad(this);
            return true;
        }
        return false;
    }
}
