package co.edu.unicauca.administracionDocumental_ms.builder;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import org.springframework.web.servlet.tags.form.LabelTag;

import java.util.Date;
import java.util.List;


public abstract class Builder {

        protected ProyectoDeGrado proyectoDeGrado;

        public ProyectoDeGrado getFormatoA() {
            return proyectoDeGrado;
        }

        public void setTituloProyecto(String tituloProyecto) {
            proyectoDeGrado.setTitulo(tituloProyecto);
        }

        public void setObjetivo(String objetivo)
        {
            proyectoDeGrado.setObjetivo(objetivo);
        }

        public void setObjetivoEspecifico(String objetivo)
        {
            proyectoDeGrado.setObjetivoEspecifico(objetivo);
        }

        public void setFecha(Date fecha) {
            proyectoDeGrado.setFechaSubida(fecha);}

        public void setArchivoAdjunto(String archivoAdjunto)
        {
            proyectoDeGrado.setArchivoAdjunto(archivoAdjunto);
        }

        public void setCoordinador(Coordinador coordinador){
            if (coordinador == null)
                throw new IllegalArgumentException("El coordinador es obligatorio");
            proyectoDeGrado.setCoordinador(coordinador);
        }
        public void setDirector(Profesor director) {
            if (director == null)
                throw new IllegalArgumentException("El director es obligatorio");
            proyectoDeGrado.setDirector(director);
        }

        public void setCodirectores(List<Profesor> codirectores) {
            proyectoDeGrado.setCodirectores(codirectores);
        }


        public abstract void crearNuevoFormato();
        public abstract void setEstudiantes(Estudiante est1, Estudiante est2)  throws Exception;

    }


