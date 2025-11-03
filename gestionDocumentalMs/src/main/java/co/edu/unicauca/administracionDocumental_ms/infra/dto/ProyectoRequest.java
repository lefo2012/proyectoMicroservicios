package co.edu.unicauca.administracionDocumental_ms.infra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProyectoRequest {
    private String titulo;
    private String objetivo;
    private String objetivoEspecifico;
    private String archivoAdjunto;
    private String correoEstudiante1;
    private String correoEstudiante2;
    private String correoDirector;
    private List<String> correoCodirectores;
    public ProyectoRequest()
    {
        correoCodirectores = new ArrayList<>();
    }
}
