package co.edu.unicauca.infra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class PersonaDto {

    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String password;
    private String cargo;
    private int idDepartamento;
    private int idPrograma;


}
