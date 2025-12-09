package co.edu.unicauca.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaRegistrarDto {

    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String password;
    private String rol;
    private int idDepartamento;
    private int idPrograma;


}
