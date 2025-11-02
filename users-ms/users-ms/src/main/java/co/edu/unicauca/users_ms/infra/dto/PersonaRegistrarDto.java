package co.edu.unicauca.users_ms.infra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonaRegistrarDto {
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String password;
    private String cargo;
    private String nombreDepartamento;
    private int idDepartamento;
    private String nombreProgama;
    private int idPrograma;
}
