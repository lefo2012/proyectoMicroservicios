package co.edu.unicauca.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignarEvaluadoresRequest {
    private long idProyecto;
    private String correoElectronicoEvaluador1;
    private String correoElectronicoEvaluador2;
}
