package co.edu.unicauca.administracionDocumental_ms.builder;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Director {
    private Builder builder;

    public ProyectoDeGrado getProyectoDeGrado()
    {
        return builder.getFormatoA();
    }

    public void build(String titulo, String objetivo, String objetivoEspecifico, Date fecha, String archivoAdjuto ,
                      Estudiante estudiante1, Estudiante estudiante2 , Coordinador coordinador, Profesor director, List<Profesor> codirectores) throws Exception
    {
       builder.crearNuevoFormato();
       builder.setTituloProyecto(titulo);
       builder.setObjetivo(objetivo);
       builder.setObjetivoEspecifico(objetivoEspecifico);
       builder.setFecha(fecha);
       builder.setArchivoAdjunto(archivoAdjuto);
       builder.setEstudiantes(estudiante1, estudiante2);
       builder.setCoordinador(coordinador);
       builder.setDirector(director);
       builder.setCodirectores(codirectores);
    }

}
