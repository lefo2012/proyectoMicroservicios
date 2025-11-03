package co.edu.unicauca.infra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PersonaDto {
    private long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private List<String> roles = new ArrayList<>();
    private String nombreDepartamento;
    private int idDepartamento;
    private String nombreProgama;
    private int idPrograma;
}
