package co.edu.unicauca.administracionDocumental_ms.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorDto {
    private long id;
    private String nombreCompleto;
    private String correo;

    public ProfesorDto(long id, String nombre, String apellido, String correoElectronico) {
        this.id = id;
        this.nombreCompleto = nombre+" "+apellido;
        this.correo = correoElectronico;
    }
}
