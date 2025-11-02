package co.edu.unicauca.users_ms.infra.dto;

import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonaDto {
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private List<String> cargos = new ArrayList<>();
    private String nombreDepartamento;
    private int idDepartamento;
    private String nombreProgama;
    private int idPrograma;
}
