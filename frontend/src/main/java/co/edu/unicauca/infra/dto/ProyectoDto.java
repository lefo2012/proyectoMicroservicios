package co.edu.unicauca.infra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ProyectoDto {
    private long id;
    private String titulo;
    private String objetivo;
    private String objetivoEspecifico;
    private String archivoAdjunto;
    private String nombreEstudiante1;
    private String nombreEstudiante2;
    private String nombreDirector;
    private List<String> nombreCodirectores;
    private String nombreCoordinador;
    private Long idEstudiante1;
    private Long idEstudiante2;
    private Long direcretorId;
    private long coordinadorId;
    private List<Long> codirectoresIds;

    private String fechaSubida;
    private String fechaCalificacion;

    private String estado;

    private String tipo;

    public ProyectoDto() {
        nombreCodirectores = new ArrayList<>();
    }
    public void addCodirector(String nombreCodirector,long id)
    {
        nombreCodirectores.add(nombreCodirector);
        codirectoresIds.add(id);
    }
}
