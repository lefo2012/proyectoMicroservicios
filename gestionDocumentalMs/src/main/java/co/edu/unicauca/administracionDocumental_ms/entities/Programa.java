package co.edu.unicauca.administracionDocumental_ms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @OneToMany
    List<Estudiante> estudiantes;

    @ManyToOne
    private Departamento departamento;

    public Programa() {
        estudiantes = new ArrayList<Estudiante>();
    }
    public boolean relacionarEstudiante(Estudiante estudiante){
        if(!estudiantes.contains(estudiante))
        {
            estudiantes.add(estudiante);
            estudiante.relacionarPrograma(this);
            return true;
        }
        return false;
    }

    public boolean relacionarDepartamento(Departamento departamento){
        if(this.departamento == null)
        {
            this.departamento = departamento;
            this.departamento.relacionarPrograma(this);
            return true;
        }
        return false;
    }
}
