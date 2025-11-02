package co.edu.unicauca.administracionDocumental_ms.infra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class FormatoARequest {
    private String titulo;
    private String objetivo;
    private String objetivoEspecifico;
    private String archivoAdjunto;
    private String correoEstudiante1;
    private String nombreEstudiante1;
    private String apellidoEstudiante;
    private String correoEstudiante2;
    private String nombreEstudiante2;
    private String apellidoEstudiante2;
    private String correoDirector;
    private List<String> correoCodirectores;


    public FormatoARequest()
    {
        correoCodirectores = new ArrayList<>();
    }
}
