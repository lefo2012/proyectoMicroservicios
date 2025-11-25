package co.edu.unicauca.administracionDocumental_ms.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorDto {
    private long id;
    private String nombre;
    private String correo;

    public ProfesorDto(long id, String nombre, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }
}
