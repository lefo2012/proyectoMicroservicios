package co.edu.unicauca.administracionDocumental_ms.entities;

import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import co.edu.unicauca.administracionDocumental_ms.service.NotificationClient;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class Coordinador extends Persona{

    @OneToOne
    private Departamento departamento;

    @OneToMany
    private List<ProyectoDeGrado> proyectosDeGrado;

    @Transient
    private StateFactory stateFactory;

    @Transient
    @Autowired
    private NotificationClient notificationClient;

    public Coordinador()
    {
        proyectosDeGrado = new ArrayList<>();
    }

    public ProyectoDeGrado aprobarFormatoA(ProyectoDeGrado proyectoDeGrado)
    {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));

        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            enviarNotificacionCambioEstado(proyectoDeGrado, "Aprobado");
            proyectoDeGrado.aprobar();
            return proyectoDeGrado;
        }
        return proyectoDeGrado;
    }
    public ProyectoDeGrado rechazarFormatoA(ProyectoDeGrado proyectoDeGrado)
    {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));
        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            enviarNotificacionCambioEstado(proyectoDeGrado, "Rechazado");
            proyectoDeGrado.rechazar();
            return proyectoDeGrado;
        }
        return proyectoDeGrado;
    }
    public ProyectoDeGrado mandarACorregir(ProyectoDeGrado proyectoDeGrado)
    {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));

        if(proyectoDeGrado!=null && proyectoDeGrado.getEstadoProyecto().equals("REVISION"))
        {
            proyectoDeGrado.correccion();
            return proyectoDeGrado;
        }
        return proyectoDeGrado;
    }

    public boolean relacionarDepartamento(Departamento departamento)
    {
        if(this.departamento==null)
        {
            this.departamento=departamento;
            this.departamento.relacionarCoordinador(this);
        }

        return true;
    }
    public boolean addProyectoDeGrado(ProyectoDeGrado proyectoDeGrado)
    {
        if(!this.proyectosDeGrado.contains(proyectoDeGrado)){
            this.proyectosDeGrado.add(proyectoDeGrado);
            return true;
        }
        return false;
    }

    private void enviarNotificacionCambioEstado(ProyectoDeGrado proyecto, String nuevoEstado) {
        NotificationRequest notification = new NotificationRequest();
        List<String> emails = new ArrayList<>();

        Profesor director = proyecto.getDirector();
        if (director != null && director.getCorreoElectronico() != null)
            emails.add(director.getCorreoElectronico());

        Estudiante estudiante1 = proyecto.getEstudiante1();
        if (proyecto.getEstudiante1() != null && proyecto.getEstudiante1().getCorreoElectronico() != null)
            emails.add(proyecto.getEstudiante1().getCorreoElectronico());

        Estudiante estudiante2 = proyecto.getEstudiante2();
        if(estudiante2!=null){
            if (proyecto.getEstudiante2() != null && proyecto.getEstudiante2().getCorreoElectronico() != null)
                emails.add(proyecto.getEstudiante2().getCorreoElectronico());
        }


        List<Profesor> codirectores = proyecto.getCodirectores();
        if (codirectores!=null){
            if (proyecto.getCodirectores() != null) {
                for (Profesor codirector : proyecto.getCodirectores()) {
                    if (codirector.getCorreoElectronico() != null)
                        emails.add(codirector.getCorreoElectronico());
                }
            }
        }

        if (proyecto.getCoordinador() != null && proyecto.getCoordinador().getCorreoElectronico() != null)
            emails.add(proyecto.getCoordinador().getCorreoElectronico());

        notification.setEmail(emails);
        notification.setSubject("Proyecto " + nuevoEstado + ": " + proyecto.getTitulo());
        notification.setMessage(
                "El proyecto de grado titulado \"" + proyecto.getTitulo() + "\" ha sido " + nuevoEstado.toLowerCase() + ".\n\n" +
                        "Estado actual: " + nuevoEstado.toUpperCase() + "\n" +
                        "Director: " + director.getNombre() + " " + director.getApellido() + "\n\n" +
                        (codirectores != null && !codirectores.isEmpty() ?
                                "Codirectores: " + codirectores.stream().map(p -> p.getNombre() + " " + p.getApellido()).collect(Collectors.joining(", ")) + "\n"
                                :
                                "") +
                        (estudiante2!= null ?
                                "Estudiantes: " + estudiante1.getNombre() + " " + estudiante1.getApellido() + ", " + estudiante2.getNombre() + " " + estudiante2.getApellido() + "\n\n"
                                :
                                "Estudiante: " + estudiante1.getNombre() + " " + estudiante1.getApellido() + "\n\n") +
                        "Saludos,\nSistema de Proyectos"
        );

        try {
            notificationClient.sendNotification(notification);
        } catch (Exception e) {
            System.err.println(" Error al enviar notificación de cambio de estado: " + e.getMessage());
        }
    }

}
